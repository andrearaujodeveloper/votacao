package com.associacao.votacao.controller;

import com.associacao.votacao.dto.AssociadoDTO;
import com.associacao.votacao.dto.AssociadoResponse;
import com.associacao.votacao.service.IAssociadoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/associados")
@SecurityRequirement(name = "bearer-key")
public class AssociadoController {

    @Autowired
    private IAssociadoService associadoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<AssociadoResponse> cadastrar(@RequestBody @Valid AssociadoDTO associadoDTO) {
        return new ResponseEntity(associadoService.cadastrar(associadoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/apagar")
    public ResponseEntity<HttpStatus> apagarLogicamentePorId(@PathVariable Long id) {
        associadoService.apagarLogicamente(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
