package br.com.matos.atm.controllers;

import br.com.matos.atm.controllers.config.Data;
import br.com.matos.atm.dto.TransacaoDto;
import br.com.matos.atm.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atm/v1/transacao/conta")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping("/{id_conta_corrente}/deposito")
    public ResponseEntity<Data<TransacaoDto>> depositaDinheiro(
            @RequestBody TransacaoDto transacaoDto,
            @PathVariable("id_conta_corrente") Long idContaCorrente){

        service.depositaDinheiro(transacaoDto, idContaCorrente);

        return new ResponseEntity<>(new Data<>(transacaoDto), HttpStatus.CREATED);
    }

    @PostMapping("/{id_conta_corrente}/saque")
    public ResponseEntity<Data<TransacaoDto>> sacaDinheiro(
            @RequestBody TransacaoDto transacaoDto,
            @PathVariable("id_conta_corrente") Long idContaCorrente){

        service.sacaDinheiro(transacaoDto, idContaCorrente);

        return new ResponseEntity<>(new Data<>(transacaoDto), HttpStatus.CREATED);
    }
}
