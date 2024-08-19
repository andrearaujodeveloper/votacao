package com.associacao.votacao.service;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.exception.DomainBusinessException;
import com.associacao.votacao.exception.NotFoundException;
import com.associacao.votacao.mapper.PautaMapper;
import com.associacao.votacao.model.Pauta;
import com.associacao.votacao.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.associacao.votacao.util.Mensagens.LIBERADA_PARA_VOTACAO;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
    void deveCadastrarPautaParaVotacao() {
        var dto = mockPautaDTO();
        var pauta = mockPauta();
        var pautaResponse = mockPautaResponse();
        when(mapper.toEntity(dto)).thenReturn(pauta);
        when(mapper.toResponse(pauta)).thenReturn(pautaResponse);
        when(repository.existsByTituloAndDescricao(dto.titulo(), dto.descricao())).thenReturn(false);
        when(repository.save(pauta)).thenReturn(pauta);

        var response = service.cadastrar(dto);

        assertEquals(response, pautaResponse);

    }

    @Test
    void deveLancarDomainBusinessExceptionAoCadastrarPautaDuplicada(){
        var dto = mockPautaDTO();

        when(repository.existsByTituloAndDescricao(dto.titulo(), dto.descricao())).thenReturn(true);

        assertThrows(DomainBusinessException.class, () -> service.cadastrar(dto));

    }

    @Test
    void deveLiberarPautaParaVotacao() {

        var pauta = mockPauta();

        when(repository.findByIdAndAbertaVotacaoTrue(pauta.getId())).thenReturn(pauta);

       service.liberarVotacao(pauta.getId());

        verify(repository,times(1)).save(pauta);
    }

    @Test
    void deveLancarNotFountExceptionAoliberarVotacao() {

        var pauta = mockPauta();

        when(repository.findByIdAndAbertaVotacaoTrue(pauta.getId())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> service.liberarVotacao(pauta.getId()));
    }

    @Test
    void deveLancarDomainsBusinessExceptionAoliberarVotacao() {

        var pauta = mockPauta();
        pauta.setAbertaVotacao(true);

        when(repository.findByIdAndAbertaVotacaoTrue(pauta.getId())).thenReturn(pauta);

        assertThrows(DomainBusinessException.class, () -> service.liberarVotacao(pauta.getId()));
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
       return new PautaResponse(1l, "titulo teste", "descricao teste", LocalDateTime.now());

    }


}