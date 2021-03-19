package br.com.matos.atm.services;

import br.com.matos.atm.controllers.config.Data;
import br.com.matos.atm.db.entities.ContaCorrenteEntity;
import br.com.matos.atm.db.entities.TransacaoEntity;
import br.com.matos.atm.db.entities.enums.TipoTransacaoEnum;
import br.com.matos.atm.db.repositories.ContaCorrenteRepository;
import br.com.matos.atm.db.repositories.TransacaoRepository;
import br.com.matos.atm.dto.TransacaoDto;
import br.com.matos.atm.services.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    private static final String VALOR_INVALIDO = "Valor Inválido";
    private static final String CONTA_NAO_ENCONTRADA = "Conta não encontrada";

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    public ResponseEntity<Data<TransacaoDto>> depositaDinheiro(TransacaoDto transacaoDto, Long idContaCorrente, HttpHeaders headers) {

        contaCorrenteRepository.findById(idContaCorrente)
                .orElseThrow(() -> new CustomException(
                        CONTA_NAO_ENCONTRADA,
                        HttpStatus.NOT_FOUND
                ));

        TransacaoEntity transacao = TransacaoEntity.builder()
                .valor(transacaoDto.getValor())
                .tipoTransacaoEnum(TipoTransacaoEnum.DEPOSITO)
                .dataHoraTransacao(transacaoDto.getDataHoraTransacao())
                .contaCorrenteEntity(ContaCorrenteEntity.builder().idContaCorrente(idContaCorrente).build())
                .build();

        transacaoRepository.save(transacao);

        atualizaSaldo(transacaoDto, idContaCorrente);

        transacaoDto.setIdContaCorrente(idContaCorrente);
        transacaoDto.setIdTransacao(transacao.getIdTransacao());
        transacaoDto.setTipoTransacaoEnum(TipoTransacaoEnum.DEPOSITO);
        transacaoDto.setDataHoraTransacao(transacao.getDataHoraTransacao());

        return new ResponseEntity<>(new Data<>(transacaoDto), HttpStatus.CREATED);
    }

    public void atualizaSaldo(TransacaoDto transacaoDto, Long idContaCorrente){

        ContaCorrenteEntity cc = contaCorrenteRepository.getOne(idContaCorrente);

        if (transacaoDto.getValor() <= 0) {
            throw new CustomException(
                    VALOR_INVALIDO,
                    HttpStatus.UNPROCESSABLE_ENTITY
            );
        } else {
            cc.setSaldo(cc.getSaldo() + transacaoDto.getValor());
        }

        contaCorrenteRepository.save(cc);
    }

}
