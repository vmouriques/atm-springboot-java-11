package br.com.matos.atm.controllers;

import br.com.matos.atm.controllers.config.Data;
import br.com.matos.atm.dto.TransacaoDto;
import br.com.matos.atm.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atm/v1/transacao/conta")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping("/{id_conta_corrente}/deposito")
    public ResponseEntity<Data<TransacaoDto>> depositaDinheiro(
            @RequestHeader HttpHeaders headers,
            @RequestBody TransacaoDto transacaoDto,
            @PathVariable("id_conta_corrente") Long idContaCorrente){

        return service.depositaDinheiro(transacaoDto, idContaCorrente, headers);
    }
}
