package com.associacao.votacao.controller;

import com.associacao.votacao.dto.VotoDTO;
import com.associacao.votacao.dto.VotoResponse;
import com.associacao.votacao.service.IvotoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/votar")
public class VotoController {

    @Autowired
    private IvotoService votoService;
    @PostMapping()
    public ResponseEntity<VotoResponse> votar(@RequestBody @Valid VotoDTO votoDTO) {

        return new ResponseEntity(votoService.votar(votoDTO), HttpStatus.CREATED);

    }
}
