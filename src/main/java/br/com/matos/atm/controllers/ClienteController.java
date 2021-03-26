package br.com.matos.atm.controllers;

import br.com.matos.atm.controllers.config.Data;
import br.com.matos.atm.dto.ClienteDto;
import br.com.matos.atm.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atm/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Data<ClienteDto>> cadastraCliente(
            @RequestBody ClienteDto clienteDto){

        service.cadastraCliente(clienteDto);

        return new ResponseEntity<>(new Data<>(clienteDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<Data<ClienteDto>> buscaClientePorId(
            @PathVariable("id_cliente") Long idCliente) {

        ClienteDto clienteDto = service.buscaClientePorId(idCliente);

        return new ResponseEntity<>(new Data<>(clienteDto), HttpStatus.OK);
    }
}
