package es.cic.viesgo.extracciones.batch.sga;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.listener.ItemListenerSupport;
import es.cic.viesgo.extracciones.model.Persona;


public class IntegradorSGAListener extends ItemListenerSupport<Persona, Persona>
{

    private static final Logger log = LoggerFactory.getLogger(IntegradorSGAListener.class);

    @Override
    public void onProcessError(Persona item, Exception e)
    {

	log.error("Error procesando {}:", item, e);

	// item.setResultado(Resultado.ERROR_PROCESADO, e);
    }


    @Override
    public void onWriteError(Exception ex, List<? extends Persona> items)
    {

	log.error("Error persistiendo {}:", items, ex);

	for (Persona bean : items)
	{
	    // bean.setResultado(Resultado.ERROR_ESCRITURA, ex);
	}
    }
}
