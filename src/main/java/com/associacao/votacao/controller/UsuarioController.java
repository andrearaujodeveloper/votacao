package com.associacao.votacao.controller;

import com.associacao.votacao.dto.*;
import com.associacao.votacao.service.IAssociadoService;
import com.associacao.votacao.service.IUsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return new ResponseEntity(usuarioService.cadastrar(usuarioDTO), HttpStatus.CREATED);
    }

    @PutMapping("/trocar-senha")
    public ResponseEntity<HttpStatus> trocarSenha(@RequestBody @Valid UsuarioTrocaSenhaDTO usuarioTrocaSenhaDTO) {
        usuarioService.trocarSenha(usuarioTrocaSenhaDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<UsuarioResponse>(usuarioService.buscarPorId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> apagarPorId(@PathVariable Long id) {
        usuarioService.apagarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
