package com.associacao.votacao.controller;

import com.associacao.votacao.dto.UsuarioDTO;
import com.associacao.votacao.dto.UsuarioResponse;
import com.associacao.votacao.dto.UsuarioTrocaSenhaDTO;
import com.associacao.votacao.provider.UsuarioDataProvider;
import com.associacao.votacao.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private JacksonTester<UsuarioDTO> usuarioDTOJacksonTester;

    @Autowired
    private JacksonTester<UsuarioTrocaSenhaDTO> usuarioTrocaSenhaDTOJacksonTester;

    @Autowired
    private JacksonTester<UsuarioResponse> usuarioResponseJacksonTester;

    @InjectMocks
    private UsuarioController usuarioController;


    @Test
    @WithMockUser
    void deveCadastrarUsuarioComSucesso() throws Exception {
        var usuarioDTO = UsuarioDataProvider.criarUsuarioDTO();
        var usuarioResponse = UsuarioDataProvider.criarUsuarioResponse();


        when(usuarioService.cadastrar(usuarioDTO)).thenReturn(usuarioResponse);

        mockMvc.perform(post("/v1/usuarios/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioDTOJacksonTester.write(usuarioDTO).getJson()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.login").value("andre@email.com"));

        verify(usuarioService, times(1)).cadastrar(any(UsuarioDTO.class));
    }

    @Test
    @WithMockUser
    void deveTrocarSenhaComSucesso() throws Exception {
        var usuarioTrocaSenhaDTO = UsuarioDataProvider.criarUsuarioTrocaSenhaDTO();

        mockMvc.perform(put("/v1/usuarios/trocar-senha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioTrocaSenhaDTOJacksonTester.write(usuarioTrocaSenhaDTO).getJson()))
                .andExpect(status().isNoContent());

        verify(usuarioService, times(1)).trocarSenha(any(UsuarioTrocaSenhaDTO.class));
    }

    @Test
    @WithMockUser
    void deveBuscarUsuarioPorIdComSucesso() throws Exception {
        UsuarioResponse usuarioResponse = UsuarioDataProvider.criarUsuarioResponse();

        when(usuarioService.buscarPorId(1L)).thenReturn(usuarioResponse);

        mockMvc.perform(get("/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.login").value("andre@email.com"));

        verify(usuarioService, times(1)).buscarPorId(1L);
    }

    @Test
    @WithMockUser
    void deveApagarUsuarioPorIdComSucesso() throws Exception {
        mockMvc.perform(delete("/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(usuarioService, times(1)).apagarPorId(1L);
    }


}
