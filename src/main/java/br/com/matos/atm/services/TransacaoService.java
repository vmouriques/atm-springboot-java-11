package br.com.matos.atm.services;

import br.com.matos.atm.db.entities.ContaCorrenteEntity;
import br.com.matos.atm.db.entities.TransacaoEntity;
import br.com.matos.atm.db.entities.enums.TipoCedulaEnum;
import br.com.matos.atm.db.entities.enums.TipoTransacaoEnum;
import br.com.matos.atm.db.repositories.TransacaoRepository;
import br.com.matos.atm.dto.TransacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransacaoService {

    private static final String VALOR_INVALIDO = "Valor inválido. Saque apenas um valor múltiplo de 10";

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    public void depositaDinheiro(TransacaoDto transacaoDto, Long idContaCorrente) {

        contaCorrenteService.validaId(idContaCorrente);

        TransacaoEntity transacao = TransacaoEntity.builder()
                .valor(transacaoDto.getValor())
                .tipoTransacaoEnum(TipoTransacaoEnum.DEPOSITO)
                .dataHoraTransacao(transacaoDto.getDataHoraTransacao())
                .contaCorrenteEntity(ContaCorrenteEntity.builder().idContaCorrente(idContaCorrente).build())
                .build();

        repository.save(transacao);

        transacaoDto.setIdContaCorrente(idContaCorrente);
        transacaoDto.setIdTransacao(transacao.getIdTransacao());
        transacaoDto.setTipoTransacaoEnum(TipoTransacaoEnum.DEPOSITO);
        transacaoDto.setDataHoraTransacao(transacao.getDataHoraTransacao());

        contaCorrenteService.atualizaSaldo(transacaoDto, idContaCorrente);

    }

    public void sacaDinheiro(TransacaoDto transacaoDto, Long idContaCorrente) {

        contaCorrenteService.validaId(idContaCorrente);

        TransacaoEntity transacao = TransacaoEntity.builder()
                .valor(transacaoDto.getValor())
                .tipoTransacaoEnum(TipoTransacaoEnum.SAQUE)
                .dataHoraTransacao(transacaoDto.getDataHoraTransacao())
                .contaCorrenteEntity(ContaCorrenteEntity.builder().idContaCorrente(idContaCorrente).build())
                .build();

        repository.save(transacao);

        transacaoDto.setIdContaCorrente(idContaCorrente);
        transacaoDto.setIdTransacao(transacao.getIdTransacao());
        transacaoDto.setTipoTransacaoEnum(TipoTransacaoEnum.SAQUE);
        transacaoDto.setDataHoraTransacao(transacao.getDataHoraTransacao());

        respostaSaque(transacaoDto);

        contaCorrenteService.atualizaSaldo(transacaoDto, idContaCorrente);

    }

    public void respostaSaque(TransacaoDto transacaoDto){

        List<Integer> cedulas = Stream.of(TipoCedulaEnum.values()).map(TipoCedulaEnum::getValorCedula).collect(Collectors.toList());
        List<Object> total = new ArrayList<>();
        Integer saque = transacaoDto.getValor().intValue();

        for (Integer cedula : cedulas) {
            while (cedula <= saque && saque > 0){
                total.add(cedula);
                saque -= cedula;
            }
        }

        List<String> quantidadeCedulas = new ArrayList<>();

        int nota100 = Collections.frequency(total, 100);
        if (nota100 != 0){
            quantidadeCedulas.add("Quantidade de notas de 100: " + nota100);
        }
        int nota50 = Collections.frequency(total, 50);
        if (nota50 != 0){
            quantidadeCedulas.add("Quantidade de notas de 50: " + nota50);
        }
        int nota20 = Collections.frequency(total, 20);
        if (nota20 != 0){
            quantidadeCedulas.add("Quantidade de notas de 20: " + nota20);
        }
        int nota10 = Collections.frequency(total, 10);
        if (nota10 != 0){
            quantidadeCedulas.add("Quantidade de notas de 10: " + nota10);
        }

        transacaoDto.setQuantidadeCedulas(quantidadeCedulas);
    }
}
