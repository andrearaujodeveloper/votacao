package com.associacao.votacao.service;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.mapper.PautaMapper;
import com.associacao.votacao.model.Pauta;
import com.associacao.votacao.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PautaServiceTest {

    @Mock
    private PautaRepository repository;

    @Mock
    private PautaMapper mapper;

    @InjectMocks
    private PautaService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrar() {
        var dto = mockPautaDTO();
        var pauta = mockPauta();
        var pautaResponse = mockPautaResponse();

        when(repository.existsByTituloAndDescricao(dto.getTitulo(), dto.getDescricao())).thenReturn(false);
        when(mapper.toEntity(dto)).thenReturn(pauta);
        when(repository.save(pauta)).thenReturn(pauta);
        when(mapper.toResponse(pauta)).thenReturn(pautaResponse);

        var response = service.cadastrar(dto);


        verify(repository).save(pauta);
    }

    private Pauta mockPauta() {
        var pauta = new Pauta();
        pauta.setId(1l);
        pauta.setTitulo("titulo teste");
        pauta.setDescricao("descricao teste");
        pauta.setDataCriacao(LocalDateTime.now());
        return pauta;
    }

    private PautaDTO mockPautaDTO() {
        return new PautaDTO("titulo teste", "descricao teste", 5);
    }

    private PautaResponse mockPautaResponse() {
        var response = new PautaResponse();
        response.setId(1l);
        response.setTitulo("titulo teste");
        response.setDescricao("descricao teste");
        response.setDataCriacao(LocalDateTime.now());
        return response;
    }


}