package br.com.matos.atm.services;

import br.com.matos.atm.controllers.config.Data;
import br.com.matos.atm.db.entities.ClienteEntity;
import br.com.matos.atm.db.repositories.ClienteRepository;
import br.com.matos.atm.dto.ClienteDto;
import br.com.matos.atm.services.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private static final String CLIENTE_NAO_ENCONTRADO = "Não existe cliente com esse ID";

    @Autowired
    private ClienteRepository repository;

    public ResponseEntity<Data<ClienteDto>> cadastraCliente(ClienteDto clienteDto, HttpHeaders headers) {

        ClienteEntity gravarCliente = ClienteEntity.builder()
                .nomeCliente(clienteDto.getNomeCliente())
                .cpfCliente(clienteDto.getCpfCliente())
                .contaCorrenteEntity(clienteDto.getContaCorrenteEntity())
                .build();

        repository.save(gravarCliente);

        clienteDto.setIdCliente(gravarCliente.getIdCliente());

        return new ResponseEntity<>(new Data<>(clienteDto), HttpStatus.CREATED);
    }

    public ResponseEntity<Data<Object>> buscaClientePorId(Long idCliente) {

        ClienteDto clienteDto = new ClienteDto(repository.findById(idCliente)
                .orElseThrow(() -> new CustomException(
                        CLIENTE_NAO_ENCONTRADO,
                        HttpStatus.NOT_FOUND
                )));

        return new ResponseEntity<>(new Data<>(clienteDto), HttpStatus.OK);
    }

}
