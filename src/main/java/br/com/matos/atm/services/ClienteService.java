package br.com.matos.atm.services;

import br.com.matos.atm.db.entities.ClienteEntity;
import br.com.matos.atm.db.repositories.ClienteRepository;
import br.com.matos.atm.dto.ClienteDto;
import br.com.matos.atm.services.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private static final String CLIENTE_NAO_ENCONTRADO = "Não existe cliente com esse ID";

    @Autowired
    private ClienteRepository repository;

    public void cadastraCliente(ClienteDto clienteDto) {

        ClienteEntity gravarCliente = ClienteEntity.builder()
                .nomeCliente(clienteDto.getNomeCliente())
                .cpfCliente(clienteDto.getCpfCliente())
                .contaCorrenteEntity(clienteDto.getContaCorrenteEntity())
                .build();

        repository.save(gravarCliente);

        clienteDto.setIdCliente(gravarCliente.getIdCliente());

    }

    public ClienteDto buscaClientePorId(Long idCliente) {

        return new ClienteDto(repository.findById(idCliente)
                .orElseThrow(() -> new CustomException(
                        CLIENTE_NAO_ENCONTRADO,
                        HttpStatus.NOT_FOUND
                )));
    }

}
