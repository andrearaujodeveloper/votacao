package com.associacao.votacao.provider;

import com.associacao.votacao.dto.UsuarioDTO;
import com.associacao.votacao.dto.UsuarioResponse;
import com.associacao.votacao.dto.UsuarioTrocaSenhaDTO;
import com.associacao.votacao.model.Usuario;

public class UsuarioDataProvider {

    public static Usuario criarUsuario() {
        return new Usuario(1l, "andre@email.com", "123");
    }

    public static UsuarioDTO criarUsuarioDTO() {
        return new UsuarioDTO("andre@email.com");
    }

    public static UsuarioTrocaSenhaDTO criarUsuarioTrocaSenhaDTO() {
        return new UsuarioTrocaSenhaDTO(1l, "andre@email.com", "novaSenha123", "novaSenha123");
    }
    public static UsuarioTrocaSenhaDTO criarUsuarioTrocaSenhaDTOComSenhaDiferente() {
        return new UsuarioTrocaSenhaDTO(1l, "andre@email.com", "novaSenha123", "senhaDIferente123");
    }

    public static UsuarioResponse criarUsuarioResponse() {
        return new UsuarioResponse(1l, "andre@email.com");
    }
}
