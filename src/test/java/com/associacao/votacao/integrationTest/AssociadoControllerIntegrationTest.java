package com.associacao.votacao.integrationTest;

import com.associacao.votacao.provider.AssociadoDataProvider;
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
public class AssociadoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "usuario", roles = {"USER"})
    @Sql(scripts = "/sql/limpaAssociado.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deveCadastrarAssociado() throws Exception {
        var associadoDTO = AssociadoDataProvider.criarDTO();

        mockMvc.perform(post("/v1/associados/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(associadoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1l))
                .andExpect(jsonPath("$.nome").value("Associado de Teste"))
                .andExpect(jsonPath("$.cpf").value("01234567891"))
                .andExpect(jsonPath("$.email").value("associadoTeste@email.com"));
    }

    @Test
    @WithMockUser(username = "usuario", roles = {"USER"})
    @Sql(scripts = "/sql/insertAssociado.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/sql/limpaAssociado.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void deveApagarLogicamenteAssociado() throws Exception {
        var associadoDTO = AssociadoDataProvider.criarDTO();

        mockMvc.perform(delete("/v1/associados/2/apagar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(associadoDTO)))
                .andExpect(status().isNoContent());
    }
}

