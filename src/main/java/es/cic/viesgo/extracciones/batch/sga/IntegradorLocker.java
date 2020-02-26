package es.cic.viesgo.extracciones.batch.sga;


import java.io.File;
import java.io.RandomAccessFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;


public class IntegradorLocker implements Tasklet
{

    private static final Logger log = LoggerFactory.getLogger(IntegradorLocker.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {

	File fileLock = new File("C:\\fileLocks\\test\\fileLock.txt");
	fileLock.getParentFile().mkdirs();
	fileLock.createNewFile();

	RandomAccessFile randomAccessFile = new RandomAccessFile(fileLock, "rw");
	if(randomAccessFile.getChannel().tryLock() == null)
	{
	    log.warn("Proceso integrador spot - sap no ha podido ejecutarse, está bloqueado por otra ejecución.");
	    throw new JobInterruptedException("Proceso integrador spot - sap bloqueado");
	}
	log.info("Bloqueando proceso integrador spot - sap...");

	return RepeatStatus.FINISHED;
    }

}
