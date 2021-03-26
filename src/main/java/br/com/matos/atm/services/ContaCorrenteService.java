package br.com.matos.atm.services;

import br.com.matos.atm.db.entities.ContaCorrenteEntity;
import br.com.matos.atm.db.entities.enums.TipoTransacaoEnum;
import br.com.matos.atm.db.repositories.ContaCorrenteRepository;
import br.com.matos.atm.dto.TransacaoDto;
import br.com.matos.atm.services.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ContaCorrenteService {

    private static final String DEPOSITO_INVALIDO = "Você não pode depositar um valor negativo";
    private static final String SAQUE_INVALIDO = "Verifique seu saldo, só é possível sacar um valor múltiplo de 10";
    private static final String CONTA_NAO_ENCONTRADA = "Conta não encontrada";

    @Autowired
    private ContaCorrenteRepository repository;

    public void atualizaSaldo(TransacaoDto transacaoDto, Long idContaCorrente){

        ContaCorrenteEntity cc = repository.getOne(idContaCorrente);

        if (transacaoDto.getTipoTransacaoEnum() == TipoTransacaoEnum.DEPOSITO){
            if (transacaoDto.getValor() <= 0) {
                throw new CustomException(
                        DEPOSITO_INVALIDO,
                        HttpStatus.UNPROCESSABLE_ENTITY
                );
            } else {
                cc.setSaldo(cc.getSaldo() + transacaoDto.getValor());
            }
        }

        if (transacaoDto.getTipoTransacaoEnum() == TipoTransacaoEnum.SAQUE){
            if (transacaoDto.getValor() <= 0 || transacaoDto.getValor() > cc.getSaldo() || transacaoDto.getValor() % 10 != 0) {
                throw new CustomException(
                        SAQUE_INVALIDO,
                        HttpStatus.UNPROCESSABLE_ENTITY
                );
            } else {
                cc.setSaldo(cc.getSaldo() - transacaoDto.getValor());
            }
        }

        repository.save(cc);
    }

    public void validaId(Long idContaCorrente){

        repository.findById(idContaCorrente)
                .orElseThrow(() -> new CustomException(
                        CONTA_NAO_ENCONTRADA,
                        HttpStatus.NOT_FOUND
                ));

    }
}
