package br.com.matos.atm.dto;

import br.com.matos.atm.db.entities.TransacaoEntity;
import br.com.matos.atm.db.entities.enums.TipoTransacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TransacaoDto implements Serializable {
    private static final long serialVersionUID = -4025136855825175638L;

    private Long idTransacao;

    private Double valor;

    private TipoTransacaoEnum tipoTransacaoEnum;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss z")
    private ZonedDateTime dataHoraTransacao;

    private Long idContaCorrente;

    public TransacaoDto(TransacaoEntity transacaoEntity) {
        this.idTransacao = transacaoEntity.getIdTransacao();
        this.valor = transacaoEntity.getValor();
        this.tipoTransacaoEnum = transacaoEntity.getTipoTransacaoEnum();
        this.dataHoraTransacao = transacaoEntity.getDataHoraTransacao();
        this.idContaCorrente = transacaoEntity.getContaCorrenteEntity().getIdContaCorrente();
    }

}
