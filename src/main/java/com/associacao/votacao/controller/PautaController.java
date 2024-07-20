package com.associacao.votacao.controller;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.dto.PautaResultadoResponse;
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
        return new ResponseEntity<PautaResponse>(pautaService.cadastrar(pautaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/liberar-votacao")
    public ResponseEntity<PautaResponse> liberarVotacao(@PathVariable Long id) {
        return new ResponseEntity<PautaResponse>(pautaService.liberarVotacao(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/resultado")
    public ResponseEntity<PautaResultadoResponse> contarVotos(@PathVariable Long id) {
        return new ResponseEntity<PautaResultadoResponse>(pautaService.contarVotos(id), HttpStatus.OK);
    }

    //TODO tratamento de exceções
    //TODO Testes
}
