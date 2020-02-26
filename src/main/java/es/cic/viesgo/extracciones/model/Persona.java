package es.cic.viesgo.extracciones.model;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
// @NoArgsConstructor
public class Persona
{

    public static final String[] personas = new String[]
    { "Nombre", "Apellidos", "Edad" };
    private String nombre;
    private String apellidos;
    private String edad;

    public Persona()
    {

	super();
    }

}
