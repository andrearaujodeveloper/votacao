package com.associacao.votacao.dto;

import java.time.LocalDateTime;

public record VotoResponse(Long id, String valorVoto, LocalDateTime dataVoto) {}
