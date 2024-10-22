package com.associacao.votacao.service;

import com.associacao.votacao.dto.*;
import com.associacao.votacao.model.Associado;
import com.associacao.votacao.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IUsuarioService {

    UsuarioResponse cadastrar(UsuarioDTO usuarioDTO);

    void trocarSenha(UsuarioTrocaSenhaDTO usuarioTrocaSenhaDTO);

    UsuarioResponse buscarPorId(Long id);

    void apagarPorId(Long id);

}
