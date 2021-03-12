package br.com.matos.atm.dto;

import br.com.matos.atm.db.entities.ContaCorrenteEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ContaCorrenteDto implements Serializable {
    private static final long serialVersionUID = -3192048569937543405L;

    private Long idContaCorrente;

    @NotEmpty
    private String agencia;

    @NotEmpty
    private String conta;

    @NotNull
    private Double saldo;

    public ContaCorrenteDto(ContaCorrenteEntity contaCorrenteEntity) {
        this.idContaCorrente = contaCorrenteEntity.getIdContaCorrente();
        this.agencia = contaCorrenteEntity.getAgencia();
        this.conta = contaCorrenteEntity.getConta();
        this.saldo = contaCorrenteEntity.getSaldo();
    }
}
