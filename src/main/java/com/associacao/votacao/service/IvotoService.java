package com.associacao.votacao.service;

import com.associacao.votacao.dto.VotoDTO;
import com.associacao.votacao.dto.VotoResponse;
import org.springframework.stereotype.Component;

@Component
public interface IvotoService {

    VotoResponse votar(VotoDTO voto);

}
