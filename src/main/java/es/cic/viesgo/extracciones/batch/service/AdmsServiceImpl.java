package es.cic.viesgo.extracciones.batch.service;


import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.cic.viesgo.extracciones.batch.model.adms.Trafo;
import es.cic.viesgo.extracciones.batch.repository.AdmsRepository;


@Service
public class AdmsServiceImpl implements AdmsService
{

    @Autowired
    private AdmsRepository admsRepository;

    @Override
    public Set<Trafo> listTrafos()
    {

	return new HashSet<>(admsRepository.findAll());
    }

}
