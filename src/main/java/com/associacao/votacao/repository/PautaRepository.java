package com.associacao.votacao.repository;

import com.associacao.votacao.dto.PautaResultadoProjection;
import com.associacao.votacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    boolean existsByTituloAndDescricao(String titulo, String descricao);

    List<Pauta> findAllByAbertaVotacaoTrue();

    @Query(value = "select p.titulo, p.descricao," +
            "COALESCE(sum(case when v.VALOR_VOTO = 'sim' then 1 else 0 end), 0) as votosPositivos, " +
            "COALESCE(sum(case when v.VALOR_VOTO = 'nao' then 1 else 0 end), 0) as votosNegativos " +
            "from PAUTAS p left join VOTOS v on v.ID_PAUTA = p.ID where p.ID = :id", nativeQuery = true)
    PautaResultadoProjection contarVotos(Long id);
}
