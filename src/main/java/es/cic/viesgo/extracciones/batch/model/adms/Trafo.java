package es.cic.viesgo.extracciones.batch.model.adms;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Trafo
{

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TRAFO")
    private String trafo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

}
