package es.cic.viesgo.extracciones.configuracion;


import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import es.cic.viesgo.extracciones.batch.IntegradorExtractorListener;
import es.cic.viesgo.extracciones.batch.IntegradorExtractorProcessor;
import es.cic.viesgo.extracciones.batch.IntegradorExtractorReader;
import es.cic.viesgo.extracciones.batch.IntegradorExtractorResumen;
import es.cic.viesgo.extracciones.batch.IntegradorLocker;
import es.cic.viesgo.extracciones.batch.IntegradorPersonaWriter;
import es.cic.viesgo.extracciones.model.IntegradorExtractorBean;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration
{

    private static final String JOB_EXTRACTOR = "integradorExtractor";
    private static final String STEP_NAME_INTEGRACION = "integracion";
    private static final String STEP_NAME_RESUMEN = "resumen";
    private static final String STEP_NAME_BLOQUEO = "bloqueo";

    // private @Value("${spring.batch.table-prefix}") String springTablePrefix;
    // private @Value("${isolation-level-create}") String isolationLevel;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("${integracionAvisos.chunksize}")
    private Integer chunkSize;
    //
    // @Value("${integracionAvisos.threads}")
    // private Integer threads;

    @Bean(name = JOB_EXTRACTOR)
    public Job job() throws Exception
    {

	JobBuilder jobBuilder = jobBuilderFactory.get(JOB_EXTRACTOR);
	jobBuilder.incrementer(new RunIdIncrementer());
	return jobBuilder.start(bloqueo()).next(extractorMantenimientoPredictivo()).next(resumen()).build();
    }


    @Bean(name = STEP_NAME_BLOQUEO)
    public Step bloqueo()
    {

	return stepBuilderFactory.get(STEP_NAME_BLOQUEO).tasklet(lockerTasklet()).build();
    }


    @Bean(name = STEP_NAME_INTEGRACION)
    public Step extractorMantenimientoPredictivo()
    {

	StepBuilder stepBuilder = stepBuilderFactory.get(STEP_NAME_INTEGRACION);
	SimpleStepBuilder<IntegradorExtractorBean, IntegradorExtractorBean> ssb = stepBuilder.<IntegradorExtractorBean, IntegradorExtractorBean> chunk(chunkSize);

	ssb.reader(reader());
	ssb.processor(processor());
	ssb.writer(writer());

	ssb = ssb.faultTolerant().skip(Exception.class).skipPolicy(new AlwaysSkipItemSkipPolicy());
	// ssb.taskExecutor(taskExecutor());
	ssb.listener(listener());
	return ssb.build();
    }


    @Bean(name = STEP_NAME_RESUMEN)
    public Step resumen()
    {

	return stepBuilderFactory.get(STEP_NAME_RESUMEN).tasklet(resumenTasklet()).build();
    }


    private Tasklet resumenTasklet()
    {

	return new IntegradorExtractorResumen();
    }


    public ItemReader<IntegradorExtractorBean> reader()
    {

	return new IntegradorExtractorReader();

    }


    @Bean
    public ItemProcessor<IntegradorExtractorBean, IntegradorExtractorBean> processor()
    {

	return new IntegradorExtractorProcessor();
    }


    private ItemWriter<IntegradorExtractorBean> writer()
    {

	return new IntegradorPersonaWriter();
    }


    @Bean
    public Tasklet lockerTasklet()
    {

	return new IntegradorLocker();
    }


    @Bean
    public ItemProcessListener<IntegradorExtractorBean, IntegradorExtractorBean> listener()
    {

	return new IntegradorExtractorListener();
    }

    // @Bean
    // public TaskExecutor taskExecutor()
    // {
    //
    // ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    // executor.setCorePoolSize(threads);
    // executor.setMaxPoolSize(threads);
    // executor.setDaemon(true);
    // return executor;
    // }

}
