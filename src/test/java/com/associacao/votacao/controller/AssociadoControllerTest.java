package com.associacao.votacao.controller;

import com.associacao.votacao.mapper.AssociadoMapper;
import com.associacao.votacao.provider.AssociadoDataProvider;
import com.associacao.votacao.repository.AssociadoRepository;
import com.associacao.votacao.service.AssociadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class AssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociadoService service;

    @MockBean
    private AssociadoRepository repository;

    @MockBean
    private AssociadoMapper mapper;

    @Test
    void deveCadastrarAssociado() throws Exception {

        var dto = AssociadoDataProvider.criarDTO();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/associados/cadastrar")
                        .contentType("application/json")
                        .content(String.valueOf(dto)))
                .andExpect(status().isCreated());
    }
}