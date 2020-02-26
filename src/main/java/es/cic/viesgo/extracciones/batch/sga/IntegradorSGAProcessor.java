package es.cic.viesgo.extracciones.batch.sga;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import es.cic.viesgo.extracciones.model.Persona;
import es.cic.viesgo.extracciones.model.PersonaDTO;


public class IntegradorSGAProcessor implements ItemProcessor<Persona, PersonaDTO>
{

    private static Logger LOG = LoggerFactory.getLogger(IntegradorSGAProcessor.class);

    @Override
    public PersonaDTO process(Persona p) throws Exception
    {

	try
	{
	    final PersonaDTO dto = new PersonaDTO(p.getNombre(), p.getApellidos(), Long.parseLong(p.getEdad()));
	    LOG.info(dto.toString());

	    return dto;
	}
	catch (Exception e)
	{
	    LOG.error("Error al procesar la persona {}", p);
	    throw new Exception("No se ha podido procesar la persona", e);
	}
    }

}
