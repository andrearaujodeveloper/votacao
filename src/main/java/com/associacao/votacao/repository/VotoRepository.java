package com.associacao.votacao.repository;

import com.associacao.votacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    @Query(value = "select * from VOTOS v where v.ID_PAUTA= :idPauta and v.ID_ASSOCIADO= :idAssociado", nativeQuery = true)
    Voto findByIdPautaAndIdAssociado(Long idPauta, Long idAssociado);
}
