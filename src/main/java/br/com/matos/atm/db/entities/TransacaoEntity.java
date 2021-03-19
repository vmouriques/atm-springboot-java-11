package br.com.matos.atm.db.entities;

import br.com.matos.atm.db.entities.enums.TipoTransacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "tb_transacao")
public class TransacaoEntity implements Serializable {
    private static final long serialVersionUID = -5170877600826578021L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;

    private Double valor;

    @Enumerated
    private TipoTransacaoEnum tipoTransacaoEnum;

    private ZonedDateTime dataHoraTransacao;

    @ManyToOne
    @JoinColumn(name = "id_conta_corrente")
    private ContaCorrenteEntity contaCorrenteEntity;

    @PrePersist
    @PreUpdate
    void dataHoraTransacao() {
        this.dataHoraTransacao = Instant.now().atZone(ZoneId.of("America/Sao_Paulo"));
    }
}
