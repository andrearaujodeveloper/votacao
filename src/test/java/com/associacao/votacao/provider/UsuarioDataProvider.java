package com.associacao.votacao.provider;

import com.associacao.votacao.model.Usuario;

public class UsuarioDataProvider {

    public static Usuario criarUsuario() {
        return new Usuario(1l, "andre@email.com", "123");
    }
}
