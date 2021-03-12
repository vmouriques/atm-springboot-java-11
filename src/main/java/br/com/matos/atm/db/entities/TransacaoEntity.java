package br.com.matos.atm.db.entities;

import br.com.matos.atm.db.entities.enums.TipoTransacaoEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

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

    private Instant dataHoraTransacao;

    @ManyToOne
    @JoinColumn(name = "id_conta_corrente")
    private ContaCorrenteEntity contaCorrenteEntity;
}
