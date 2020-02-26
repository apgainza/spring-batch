package es.cic.viesgo.extracciones.configuracion;


import java.util.Properties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@ComponentScan(basePackages =
{ "es.cic", "es.viesgo" })
@PropertySource(
{ "classpath:integrador.properties" })
@EnableJpaRepositories(basePackages = "es.cic.viesgo.extracciones.batch.repository")
public class PersistenceConfiguration
{

    public static final String ENTITY_MANAGER_NAME = "entityManagerFactory";
    public static final String TRANSACTION_MANAGER_NAME = "transactionManager";
    public static final String PERSITENCE_UNIT_NAME = "persistence-unit";

    private @Value("${extractor.datasource.url}") String datasourceUrl;
    private @Value("${extractor.datasource.username}") String username;
    private @Value("${extractor.datasource.password}") String password;
    private @Value("${extractor.datasource.driverClassName}") String driverClassName;
    private @Value("${extractor.datasource.dialect}") String dialect;
    // private @Value("${extractor.datasource.max-active}") Integer maxActive;
    // private @Value("${extractor.datasource.max-idle}") Integer maxIdle;
    // private @Value("${extractor.datasource.min-idle}") Integer minIdle;
    // private @Value("${extractor.datasource.max-wait}") Integer maxWait;
    // private @Value("${extractor.datasource.initial-size}") Integer
    // initialSize;
    // private @Value("${extractor.datasource.schema}") String defaultSchema;

    // @Bean
    public DataSource spotDataSource()
    {

	PoolProperties properties = new PoolProperties();
	properties.setUrl(datasourceUrl);
	properties.setUsername(username);
	properties.setPassword(password);
	properties.setDriverClassName(driverClassName);
	// properties.setInitialSize(initialSize);
	// properties.setMaxActive(maxActive);
	// properties.setMaxIdle(maxIdle);
	// properties.setMaxWait(maxWait);
	// properties.setMinIdle(minIdle);

	return new DataSource(properties);

    }


    // @Bean
    public LocalContainerEntityManagerFactoryBean spotEntityManagerFactory()
    {

	Properties properties = new Properties();
	properties.setProperty("hibernate.cache.use_second_level_cache", "false");
	properties.setProperty("hibernate.generate_statistics", "false");
	properties.setProperty("hibernate.hbm2ddl.auto", "none");
	// properties.setProperty("hibernate.default_schema", defaultSchema);
	properties.setProperty("hibernate.dialect", dialect);

	LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
	entityManager.setPackagesToScan("es.cic.viesgo.extracciones");
	entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	entityManager.setPersistenceUnitName(PERSITENCE_UNIT_NAME);
	entityManager.setJpaProperties(properties);
	entityManager.setDataSource(spotDataSource());

	return entityManager;
    }


    // @Bean
    // @Primary
    public PlatformTransactionManager transactionManager()
    {

	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(spotEntityManagerFactory().getObject());
	return transactionManager;
    }

}
