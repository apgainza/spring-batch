package es.cic.viesgo.extracciones.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO
{

    private String nombre;
    private String apellidos;
    private Long edad;

}
