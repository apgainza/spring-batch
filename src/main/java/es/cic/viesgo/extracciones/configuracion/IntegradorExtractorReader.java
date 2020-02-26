package es.cic.viesgo.extracciones.configuracion;


import java.util.Set;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import es.cic.viesgo.extracciones.batch.model.adms.Trafo;
import es.cic.viesgo.extracciones.batch.service.AdmsService;


public class IntegradorExtractorReader implements ItemReader<IntegradorExtractorBean>
{

    @Autowired
    private AdmsService admsService;

    @Override
    public IntegradorExtractorBean read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException
    {

	IntegradorExtractorBean name = new IntegradorExtractorBean();

	// Trafos - ADMS
	Set<Trafo> trafosAdms = trafosAdms();

	// Señales - ARM
	senalesArm();

	return name;
    }


    private void senalesArm()
    {

	// TODO Auto-generated method stub

    }


    /**
     * Obtenemos los trafos de la ADMS
     * 
     * @return
     */
    private Set<Trafo> trafosAdms()
    {

	return admsService.listTrafos();
    }

}
