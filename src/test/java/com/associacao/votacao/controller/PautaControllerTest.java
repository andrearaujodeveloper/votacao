package com.associacao.votacao.controller;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.dto.PautaResultadoProjection;
import com.associacao.votacao.provider.PautaDataProvider;
import com.associacao.votacao.provider.PautaResultadoProjectionDataProvider;
import com.associacao.votacao.repository.PautaRepository;
import com.associacao.votacao.service.PautaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<PautaDTO> pautaDTOJacksonTester;

    @Autowired
    private JacksonTester<PautaResponse> pautaResponseJacksonTester;

    @Autowired
    private JacksonTester<PautaResultadoProjection> pautaResultadoProjectionJacksonTester;

    @MockBean
    private PautaService pautaService;

    @MockBean
    private PautaRepository pautaRepository;

    @Test
    @WithMockUser
    void cadastrar() throws Exception {
        var pautaDto = PautaDataProvider.criarDTO();
        var pautaResponse = PautaDataProvider.criarResponse();

        when(pautaService.cadastrar(pautaDto)).thenReturn(pautaResponse);

        var response = mockMvc.perform(
                        post("/v1/pautas/cadastrar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(pautaDTOJacksonTester.write(pautaDto).getJson()))
                .andReturn().getResponse();

        var esperado = pautaResponseJacksonTester.write(pautaResponse).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(esperado);
    }

    @Test
    @WithMockUser
    void liberarVotacao() throws Exception {
        var response = mockMvc.perform(
                        put("/v1/pautas/1/liberar-votacao"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @WithMockUser
    void contarVotos() throws Exception {
        var pautaResultadoProjection = PautaResultadoProjectionDataProvider.criar();
        when(pautaService.contarVotos(1l)).thenReturn(pautaResultadoProjection);
        var response = mockMvc.perform(
                        get("/v1/pautas/1/resultado"))
                .andReturn().getResponse();

        var esperado = pautaResultadoProjectionJacksonTester.write(pautaResultadoProjection).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(esperado);
    }
}