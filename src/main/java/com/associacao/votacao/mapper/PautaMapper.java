package com.associacao.votacao.mapper;

import com.associacao.votacao.dto.PautaDTO;
import com.associacao.votacao.dto.PautaResponse;
import com.associacao.votacao.model.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import java.time.LocalDateTime;

@Mapper(imports = {LocalDateTime.class, java.util.ArrayList.class})
public interface PautaMapper {

    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);


    Pauta toEntity(PautaDTO pautaDTO);


    PautaResponse toResponse(Pauta pauta);
}
