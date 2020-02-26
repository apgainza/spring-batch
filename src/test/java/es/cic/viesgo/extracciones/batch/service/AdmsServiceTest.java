package es.cic.viesgo.extracciones.batch.service;


import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import es.cic.viesgo.extracciones.batch.model.adms.Trafo;
import es.cic.viesgo.extracciones.batch.repository.AdmsRepository;


@DataJpaTest
public class AdmsServiceTest
{

    @Autowired
    private AdmsRepository admsRepository;

    @Test
    public void listAllTrafos()
    {

	List<Trafo> trafos = admsRepository.findAll();

	assertTrue(trafos.size() > 0);
    }
}
