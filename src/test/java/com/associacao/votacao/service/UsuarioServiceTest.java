package com.associacao.votacao.service;

import com.associacao.votacao.mapper.UsuarioMapper;
import com.associacao.votacao.provider.UsuarioDataProvider;
import com.associacao.votacao.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    UsuarioRepository repository;

    @Mock
    UsuarioMapper mapper;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    UsuarioService service;


    @Test
    void deveCadatrarUsuario() {
        var login = "andre@email.com";
        var senha = "123";
        var usuario = UsuarioDataProvider.criarUsuario();
        when(mapper.toEntity(login,senha)).thenReturn(usuario);
        when(encoder.encode(senha)).thenReturn(senha);
        service.cadastrarUsuario(login, senha);

        verify(repository).save(usuario);
    }
}