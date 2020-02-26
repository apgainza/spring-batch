package es.cic.viesgo.extracciones.configuracion;


import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


//@Configuration
//@EnableBatchProcessing
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

    // @Bean(name = JOB_EXTRACTOR)
    // public Job job() throws Exception
    // {
    //
    // JobBuilder jobBuilder = jobBuilderFactory.get(JOB_EXTRACTOR);
    // jobBuilder.incrementer(new RunIdIncrementer());
    // return
    // jobBuilder.start(bloqueo()).next(extractorMantenimientoPredictivo()).next(resumen()).build();
    // }
    //
    //
    // @Bean(name = STEP_NAME_BLOQUEO)
    // public Step bloqueo()
    // {
    //
    // return
    // stepBuilderFactory.get(STEP_NAME_BLOQUEO).tasklet(lockerTasklet()).build();
    // }
    //
    //
    // @Bean(name = STEP_NAME_INTEGRACION)
    // public Step extractorMantenimientoPredictivo()
    // {
    //
    // StepBuilder stepBuilder = stepBuilderFactory.get(STEP_NAME_INTEGRACION);
    // SimpleStepBuilder<Persona, PersonaDTO> ssb = stepBuilder.<Persona,
    // PersonaDTO> chunk(chunkSize);
    //
    // ssb.reader(reader());
    // ssb.processor(processor());
    // ssb.writer(writer());
    //
    // ssb = ssb.faultTolerant().skip(Exception.class).skipPolicy(new
    // AlwaysSkipItemSkipPolicy());
    // // ssb.taskExecutor(taskExecutor());
    // ssb.listener(listener());
    // return ssb.build();
    // }
    //
    //
    // @Bean(name = STEP_NAME_RESUMEN)
    // public Step resumen()
    // {
    //
    // return
    // stepBuilderFactory.get(STEP_NAME_RESUMEN).tasklet(resumenTasklet()).build();
    // }
    //
    //
    // private Tasklet resumenTasklet()
    // {
    //
    // return new IntegradorSGAResumen();
    // }
    //
    //
    // public ItemReader<IntegradorExtractorBean> reader()
    // {
    //
    // return new IntegradorExtractorReader();
    //
    // }
    //
    // // @Bean
    // // public FlatFileItemReader<Persona> reader()
    // // {
    // //
    // // return new
    // //
    // FlatFileItemReaderBuilder<Persona>().name("personItemReader").resource(new
    // //
    // ClassPathResource("personas-datos.csv")).delimited().names(Persona.personas).linesToSkip(1).fieldSetMapper(new
    // // BeanWrapperFieldSetMapper<Persona>()
    // // {
    // //
    // // {
    // // setTargetType(Persona.class);
    // // }
    // // }).build();
    // // }
    //
    //
    // @Bean
    // public ItemProcessor<Persona, PersonaDTO> processor()
    // {
    //
    // return new IntegradorSGAProcessor();
    // }
    //
    //
    // private ItemWriter<PersonaDTO> writer()
    // {
    //
    // return new IntegradorPersonaWriter();
    // }
    //
    //
    // @Bean
    // public Tasklet lockerTasklet()
    // {
    //
    // return new IntegradorLocker();
    // }
    //
    //
    // @Bean
    // public ItemProcessListener<Persona, Persona> listener()
    // {
    //
    // return new IntegradorSGAListener();
    // }

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
