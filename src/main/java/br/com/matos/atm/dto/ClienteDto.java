package br.com.matos.atm.dto;

import br.com.matos.atm.db.entities.ClienteEntity;
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
public class ClienteDto implements Serializable {
    private static final long serialVersionUID = 3503036411324334842L;

    private Long idCliente;

    @NotEmpty
    private String nomeCliente;

    @NotEmpty
    private String cpfCliente;

    @NotNull
    private ContaCorrenteEntity contaCorrenteEntity;

    public ClienteDto(ClienteEntity clienteEntity) {
        this.idCliente = clienteEntity.getIdCliente();
        this.nomeCliente = clienteEntity.getNomeCliente();
        this.cpfCliente = clienteEntity.getCpfCliente();
        this.contaCorrenteEntity = clienteEntity.getContaCorrenteEntity();
    }
}
