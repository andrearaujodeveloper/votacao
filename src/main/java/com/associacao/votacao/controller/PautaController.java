package com.associacao.votacao.controller;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.service.IPautaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pautas")
public class PautaController {

    @Autowired
    private IPautaService pautaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<PautaResponse> cadastrar(@RequestBody @Valid PautaDTO pautaDTO) {
        return new ResponseEntity(pautaService.cadastrar(pautaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/liberar-votacao")
    public ResponseEntity<PautaResponse> liberarVotacao(@PathVariable Long id) {
        return new ResponseEntity<>(pautaService.liberarVotacao(id), HttpStatus.OK);
    }
    //TODO agendador para fechar as pautas
}
