package com.associacao.votacao.mapper;

import com.associacao.votacao.dto.ValorVotoEnum;
import com.associacao.votacao.dto.VotoDTO;
import com.associacao.votacao.model.Associado;
import com.associacao.votacao.model.Pauta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VotoMapperTest {

    VotoMapper mapper = Mappers.getMapper(VotoMapper.class);


    @Test
    void toEntity() {

        var dto = new VotoDTO(ValorVotoEnum.SIM, 1l, 1l);
        var pauta = mockPauta();
        var associado = mockAssociado();
        var voto = mapper.toEntity(dto, pauta, associado);

        assertTrue(voto != null);
    }

    private Pauta mockPauta() {
        var pauta = new Pauta();
        pauta.setId(1l);
        pauta.setTitulo("titulo teste");
        pauta.setDescricao("descricao teste");
        pauta.setDataCriacao(LocalDateTime.now());
        return pauta;
    }

    private Associado mockAssociado() {
        var associado = new Associado();
        associado.setId(1l);
        associado.setNome("associado teste");
        associado.setCpf("0123456789");
        return associado;
    }
}