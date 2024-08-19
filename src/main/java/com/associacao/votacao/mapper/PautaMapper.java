package com.associacao.votacao.mapper;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.model.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    Pauta toEntity(PautaDTO pautaDTO);


    PautaResponse toResponse(Pauta pauta);
}
