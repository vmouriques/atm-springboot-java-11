package br.com.matos.atm.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "tb_conta_corrente")
public class ContaCorrenteEntity implements Serializable {
    private static final long serialVersionUID = 1952169683674917567L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContaCorrente;

    private String agencia;

    private String conta;

    @PositiveOrZero
    private Double saldo;

    @JsonIgnore
    @OneToOne(mappedBy = "contaCorrenteEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ClienteEntity clienteEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "contaCorrenteEntity", targetEntity = TransacaoEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TransacaoEntity> transacaoEntityList;
}
