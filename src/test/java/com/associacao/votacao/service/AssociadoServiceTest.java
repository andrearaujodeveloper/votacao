package com.associacao.votacao.service;

import com.associacao.votacao.exception.NotFoundException;
import com.associacao.votacao.provider.AssociadoDataProvider;
import com.associacao.votacao.exception.DomainBusinessException;
import com.associacao.votacao.mapper.AssociadoMapper;
import com.associacao.votacao.provider.UsuarioDataProvider;
import com.associacao.votacao.repository.AssociadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociadoServiceTest {

    @InjectMocks
    AssociadoService service;
    @Mock
    AssociadoRepository repository;
    @Mock
    AssociadoMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarAssociado() {
        var dto = AssociadoDataProvider.criarDTO();
        var associado = AssociadoDataProvider.criar();
        var associadoResponse = AssociadoDataProvider.criarResponse();

        when(mapper.toEntity(dto)).thenReturn(associado);
        when(mapper.toResponse(associado)).thenReturn(associadoResponse);
        when(repository.existsByCpf(dto.cpf())).thenReturn(false);
        when(repository.existsByEmail(dto.email())).thenReturn(false);
        when(repository.save(associado)).thenReturn(associado);

        var response = service.cadastrar(dto);

        assertEquals(response, associadoResponse);
    }

    @Test
    void deveLancarDomainBusinessExceptionAoCadastrarAssociadoComEmailJaCadastrado() {
        var dto = AssociadoDataProvider.criarDTO();

        when(repository.existsByEmail(dto.email())).thenReturn(true);

        assertThrows(DomainBusinessException.class, ()-> service.cadastrar(dto));
    }

    @Test
    void deveLancarDomainBusinessExceptionAoCadastrarAssociadoComCPFJaCadastrado() {
        var dto = AssociadoDataProvider.criarDTO();

        when(repository.existsByCpf(dto.cpf())).thenReturn(true);
        when(repository.existsByEmail(dto.email())).thenReturn(false);

        assertThrows(DomainBusinessException.class, ()-> service.cadastrar(dto));
    }

    @Test
    void deveApagarLogicamenteRegistroDeUsuario(){
        var associado = AssociadoDataProvider.criar();

        when(repository.findByIdAndAtivoTrue(anyLong())).thenReturn(associado);

        service.apagarLogicamente(anyLong());

        assertFalse(associado.getAtivo());
        verify(repository, times(1)).save(associado);
    }

    @Test
    void deveLancarNotFoundExceptionAoApagarLogicamenteRegistroDeUsuario() {
        when(repository.findByIdAndAtivoTrue(anyLong())).thenReturn(null);

        assertThrows(NotFoundException.class, ()-> service.apagarLogicamente(anyLong()));
    }
}