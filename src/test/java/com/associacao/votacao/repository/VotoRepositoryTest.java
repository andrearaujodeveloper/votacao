package com.associacao.votacao.repository;

import com.associacao.votacao.model.Associado;
import com.associacao.votacao.model.Pauta;
import com.associacao.votacao.provider.AssociadoDataProvider;
import com.associacao.votacao.provider.PautaDataProvider;
import com.associacao.votacao.provider.VotoDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class VotoRepositoryTest {

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private TestEntityManager em;
    @Test
    void findByIdPautaAndIdAssociado() {
        var pauta = cadastrarPauta();
        var associado = cadastrarAssociado();
        cadastrarVoto(pauta,associado);

        var voto = votoRepository.findByIdPautaAndIdAssociado(pauta.getId(), associado.getId());

        assertEquals(voto.getPauta(), pauta);
        assertEquals(voto.getAssociado(), associado);

    }

    private Pauta cadastrarPauta() {
        var pauta = PautaDataProvider.criarSemId();
        em.persist(pauta);
        return pauta;
    }

    private Associado cadastrarAssociado() {
        var associado = AssociadoDataProvider.criarSemId();
        em.persist(associado);
        return associado;
    }

    private void  cadastrarVoto(Pauta pauta, Associado associado) {
        var voto = VotoDataProvider.criarVotoParaTesteRepository(pauta, associado);
        em.persist(voto);
    }
}