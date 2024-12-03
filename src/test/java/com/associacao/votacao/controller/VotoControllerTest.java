package com.associacao.votacao.controller;

import com.associacao.votacao.dto.VotoDTO;
import com.associacao.votacao.dto.VotoResponse;
import com.associacao.votacao.provider.VotoDataProvider;
import com.associacao.votacao.repository.VotoRepository;
import com.associacao.votacao.service.VotoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class VotoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<VotoDTO> votoDTOJacksonTester;

    @Autowired
    private JacksonTester<VotoResponse> votoResponseJacksonTester;

    @MockBean

    private VotoService votoService;

    @MockBean
    private VotoRepository votoRepository;

    @Test
    void votar() throws Exception {
        var votoDto = VotoDataProvider.criarDTO();
        var votoResponse = VotoDataProvider.criarVotoResponse();

        when(votoService.votar(votoDto)).thenReturn(votoResponse);

        var response = mockMvc.perform(
                post("/v1/votar")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(votoDTOJacksonTester.write(votoDto).getJson()))
                .andReturn().getResponse();

        var esperado = votoResponseJacksonTester.write(votoResponse).getJson();

        assertThat(response.getContentAsString()).isEqualTo(esperado);
    }
}