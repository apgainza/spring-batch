package es.cic.viesgo.extracciones.batch.sga;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import es.cic.viesgo.extracciones.model.PersonaDTO;


public class IntegradorPersonaWriter implements ItemWriter<PersonaDTO>
{

    private static Logger LOG = LoggerFactory.getLogger(IntegradorPersonaWriter.class);

    @Override
    public void write(List<? extends PersonaDTO> personas) throws Exception
    {

	LOG.info("{}", personas.size());
	personas.stream().forEach(System.out::println);

    }

}
