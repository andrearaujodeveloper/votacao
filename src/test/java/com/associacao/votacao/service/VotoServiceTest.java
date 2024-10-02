package com.associacao.votacao.service;

import com.associacao.votacao.exception.DomainBusinessException;
import com.associacao.votacao.mapper.VotoMapper;
import com.associacao.votacao.provider.AssociadoDataProvider;
import com.associacao.votacao.provider.PautaDataProvider;
import com.associacao.votacao.provider.VotoDataProvider;
import com.associacao.votacao.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {
    @Mock
    private VotoRepository repository;
    @Mock
    private PautaService pautaService;
    @Mock
    private AssociadoService associadoService;
    @Mock
    private VotoMapper mapper;
    @InjectMocks
    private VotoService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void devevotarComSucesso() {
        var dto = VotoDataProvider.criarDTO();
        var voto = VotoDataProvider.criar();
        var votoResponse = VotoDataProvider.criarVotoResponse();
        var pauta = PautaDataProvider.criar();
        var associado = AssociadoDataProvider.criar();

        when(mapper.toEntity(dto, pauta, associado)).thenReturn(voto);
        when(mapper.toResponse(voto)).thenReturn(votoResponse);
        when(repository.findByIdPautaAndIdAssociado(dto.idPauta(), dto.idAssociado())).thenReturn(null);
        when(repository.save(voto)).thenReturn(voto);
        when(pautaService.buscarPautaAbertaPorId(dto.idPauta())).thenReturn(pauta);
        when(associadoService.buscarAssociadoPorId(dto.idAssociado())).thenReturn(associado);

        var response = service.votar(dto);

        assertEquals(response, votoResponse);
    }

    @Test
    void deveLancarDomainBusinessExceptionAoVotar() {
        var dto = VotoDataProvider.criarDTO();
        var voto = VotoDataProvider.criar();

        when(repository.findByIdPautaAndIdAssociado(dto.idPauta(), dto.idAssociado())).thenReturn(voto);

        assertThrows(DomainBusinessException.class, () -> service.votar(dto));
    }
}