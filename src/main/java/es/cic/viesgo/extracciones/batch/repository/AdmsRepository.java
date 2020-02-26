package es.cic.viesgo.extracciones.batch.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.cic.viesgo.extracciones.batch.model.adms.Trafo;


@Repository
public interface AdmsRepository extends JpaRepository<Trafo, Long>
{

}
