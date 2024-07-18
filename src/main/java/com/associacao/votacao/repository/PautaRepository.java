package com.associacao.votacao.repository;

import com.associacao.votacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    boolean existsByTituloAndDescricao(String titulo, String descricao);

    List<Pauta> findAllByAbertaVotacaoTrue();
}
