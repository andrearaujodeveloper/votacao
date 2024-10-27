package com.associacao.votacao.integrationTest;

import com.associacao.votacao.provider.UsuarioDataProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integrationTest")
public class UsuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "usuario", roles = {"USER"})
    @Sql(scripts = "/sql/limpaUsuario.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deveCadastrarUsuario() throws Exception {
        var usuarioDTO = UsuarioDataProvider.criarUsuarioDTO();

        mockMvc.perform(post("/v1/usuarios/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1l))
                .andExpect(jsonPath("$.login").value("andre@email.com"));
    }

    @Test
    @WithMockUser(username = "usuario", roles = {"USER"})
    @Sql(scripts = "/sql/insertUsuario.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/sql/limpaUsuario.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deveTrocarSenhaDoUsuario() throws Exception {
        var usuarioTrocaSenha = UsuarioDataProvider.criarUsuarioTrocaSenhaDTO();

        mockMvc.perform(put("/v1/usuarios/trocar-senha")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioTrocaSenha)))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "usuario", roles = {"USER"})
    @Sql(scripts = "/sql/insertUsuario.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/sql/limpaUsuario.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deveBuscarUsuarioPorId() throws Exception {

        mockMvc.perform(get("/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1l))
                .andExpect(jsonPath("$.login").value("andre@email.com"));
    }

    @Test
    @WithMockUser(username = "usuario", roles = {"USER"})
    @Sql(scripts = "/sql/insertUsuario.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/sql/limpaUsuario.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deveApagarUsuarioPorId() throws Exception {

        mockMvc.perform(delete("/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}

