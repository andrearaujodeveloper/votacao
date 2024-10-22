package com.associacao.votacao.service;

import com.associacao.votacao.exception.DomainBusinessException;
import com.associacao.votacao.exception.NotFoundException;
import com.associacao.votacao.mapper.UsuarioMapper;
import com.associacao.votacao.model.Usuario;
import com.associacao.votacao.provider.UsuarioDataProvider;
import com.associacao.votacao.repository.UsuarioRepository;
import com.associacao.votacao.util.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    UsuarioRepository repository;

    @Mock
    UsuarioMapper mapper;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private EmailService email;

    @InjectMocks
    UsuarioService service;


    @Test
    void deveCadastrarUsuario() {
        var usuarioDTO =UsuarioDataProvider.criarUsuarioDTO();
        var usuario = UsuarioDataProvider.criarUsuario();
        var senha = "123";
        when(repository.existsByLogin(usuarioDTO.login())).thenReturn(false);
        when(mapper.toEntity(usuarioDTO.login(),senha)).thenReturn(usuario);
        when(encoder.encode(anyString())).thenReturn(senha);
        service.cadastrar(usuarioDTO);

        verify(repository).save(usuario);
    }
    @Test
    void deveLancarDomainBusinessExceptionAoCadatrarUsuario() {
        var usuarioDTO =UsuarioDataProvider.criarUsuarioDTO();

        when(repository.existsByLogin(usuarioDTO.login())).thenReturn(true);

        assertThrows(DomainBusinessException.class, () -> service.cadastrar(usuarioDTO));
    }

    @Test
    void deveTrocarSenhaComSucesso() {
        var dto = UsuarioDataProvider.criarUsuarioTrocaSenhaDTO();
        var usuario = UsuarioDataProvider.criarUsuario();

        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        when(repository.existsByLogin(dto.login())).thenReturn(true);

        service.trocarSenha(dto);

        verify(repository, times(1)).save(usuario);
        assertEquals("novaSenha123", usuario.getSenha());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoForEncontrado() {
        var dto = UsuarioDataProvider.criarUsuarioTrocaSenhaDTO();

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.trocarSenha(dto);
        });

        verify(repository, never()).save(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoSenhasNaoForemIguais() {
        var dto = UsuarioDataProvider.criarUsuarioTrocaSenhaDTOComSenhaDiferente();
        var usuario = UsuarioDataProvider.criarUsuario();
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        assertThrows(DomainBusinessException.class, () -> {
            service.trocarSenha(dto);
        });


        verify(repository, never()).save(any(Usuario.class));
    }

    @Test
    void deveBuscarUsuarioPorId() {
        var usuario = UsuarioDataProvider.criarUsuario();
        var usuarioResponse = UsuarioDataProvider.criarUsuarioResponse();
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        when(mapper.toResponse(usuario)).thenReturn(usuarioResponse);

        var response = service.buscarPorId(usuario.getId());

        assertEquals(usuarioResponse, response);
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarUsuario() {

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.buscarPorId(1l));
    }

    @Test
    void deveAPagarUsuario() {
        when(repository.existsById(1l)).thenReturn(true);

        service.apagarPorId(1l);

        verify(repository).deleteById(1l);
    }

    @Test
    void deveLancarExcecaoAoNaoEncontrarUsuario() {
        when(repository.existsById(1l)).thenReturn(false);

        assertThrows(NotFoundException.class, ()-> service.apagarPorId(1l));

        verify(repository, never()).deleteById(1l);
    }
}