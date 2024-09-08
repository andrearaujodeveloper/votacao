package com.associacao.votacao.controller;

import com.associacao.votacao.dto.AssociadoDTO;
import com.associacao.votacao.dto.AssociadoResponse;
import com.associacao.votacao.mapper.AssociadoMapper;
import com.associacao.votacao.provider.AssociadoDataProvider;
import com.associacao.votacao.repository.AssociadoRepository;
import com.associacao.votacao.service.AssociadoService;
import org.junit.jupiter.api.Test;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AssociadoDTO> associadoDTOJacksonTester;

    @Autowired
    private JacksonTester<AssociadoResponse> associadoResponseJacksonTester;

    @MockBean
    private AssociadoService associadoService;

    @MockBean
    private AssociadoRepository associadoRepository;

    @Test
    @WithMockUser
    void deveCadastrarAssociado() throws Exception {

        var associadoDTO = AssociadoDataProvider.criarDTO();
        var associadoResponse = AssociadoDataProvider.criarResponse();

        when(associadoService.cadastrar(associadoDTO)).thenReturn(associadoResponse);
        var response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/associados/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(associadoDTOJacksonTester.write(associadoDTO).getJson()))
                .andReturn().getResponse();


        var esperado = associadoResponseJacksonTester.write(associadoResponse).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(esperado);
    }
}