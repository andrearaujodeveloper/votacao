package com.associacao.votacao.service;

import com.associacao.votacao.mapper.UsuarioMapper;
import com.associacao.votacao.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private UsuarioMapper mapper;

    private final PasswordEncoder encoder;

    public void cadastrarUsuario(String login, String senha) {
        var senhaEncript = encoder.encode(senha);
        var user = mapper.toEntity(login, senhaEncript);
        usuarioRepository.save(user);
    }

}
