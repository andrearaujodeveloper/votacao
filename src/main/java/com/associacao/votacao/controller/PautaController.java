package com.associacao.votacao.controller;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.dto.PautaResultadoProjection;
import com.associacao.votacao.service.IPautaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pautas")
@SecurityRequirement(name = "bearer-key")
public class PautaController {

    @Autowired
    private IPautaService pautaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<PautaResponse> cadastrar(@RequestBody @Valid PautaDTO pautaDTO) {
        return new ResponseEntity(pautaService.cadastrar(pautaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/liberar-votacao")
    public ResponseEntity<HttpStatus> liberarVotacao(@PathVariable Long id) {
        pautaService.liberarVotacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/resultado")
    public ResponseEntity<PautaResultadoProjection> contarVotos(@PathVariable Long id) {
        return ResponseEntity.ok(pautaService.contarVotos(id));
    }


}
