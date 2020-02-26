package es.cic.viesgo.extracciones;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ExtraccionesApplication
{

    private static Logger LOG = LoggerFactory.getLogger(ExtraccionesApplication.class);

    public static void main(String[] args)
    {

	LOG.info("---- Inicio proceso Extracciones para mantenimiento predictivo​ ----");

	SpringApplication.run(ExtraccionesApplication.class, args);

	LOG.info("---- Fin proceso Extracciones para mantenimiento predictivo​ ----");
    }

}
