package br.com.matos.atm.db.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "tb_cliente")
public class ClienteEntity implements Serializable {
    private static final long serialVersionUID = 5956547331682638221L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nomeCliente;

    private String cpfCliente;

    @OneToOne(targetEntity = ContaCorrenteEntity.class, cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    @JoinColumn(name="id_conta_corrente")
    private ContaCorrenteEntity contaCorrenteEntity;
}
