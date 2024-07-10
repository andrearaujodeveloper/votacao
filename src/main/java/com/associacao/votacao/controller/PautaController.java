package com.associacao.votacao.controller;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pautas")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<PautaResponse> cadastrar(@RequestBody @Valid PautaDTO pautaDTO) {
        return new ResponseEntity(pautaService.cadastrar(pautaDTO), HttpStatus.CREATED);
    }
}
