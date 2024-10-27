package com.associacao.votacao.integrationTest;

import com.associacao.votacao.provider.PautaDataProvider;
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
public class PautaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "usuario", roles = {"USER"})
    @Sql(scripts = "/sql/limpaPauta.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deveCadastrarPauta() throws Exception {
        var pautaDTO = PautaDataProvider.criarDTO();

        mockMvc.perform(post("/v1/pautas/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pautaDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1l))
                .andExpect(jsonPath("$.titulo").value("titulo teste"))
                .andExpect(jsonPath("$.descricao").value("descricao teste"));
    }

    @Test
    @WithMockUser(username = "usuario", roles = {"USER"})
    @Sql(scripts = "/sql/insertDadosParaContagemVotos.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/sql/limpaDadosPosContagemVotos.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deveRetornaResultadoDaVotacao() throws Exception {

        mockMvc.perform(get("/v1/pautas/1/resultado")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("titulo teste"))
                .andExpect(jsonPath("$.descricao").value("descricao teste"))
                .andExpect(jsonPath("$.votosPositivos").value(2))
                .andExpect(jsonPath("$.votosNegativos").value(1));
    }

    @Test
    @WithMockUser(username = "usuario", roles = {"USER"})
    @Sql(scripts = "/sql/insertPauta.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/sql/limpaPauta.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void develiberarPautaParaVotacao() throws Exception {

        mockMvc.perform(put("/v1/pautas/1/liberar-votacao")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

