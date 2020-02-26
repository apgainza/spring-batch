package es.cic.viesgo.extracciones.batch.service;


import java.util.Set;
import es.cic.viesgo.extracciones.batch.model.adms.Trafo;


public interface AdmsService
{

    public Set<Trafo> listTrafos();
}
