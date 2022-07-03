package per.xueyuhuihui.common.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory2",
        transactionManagerRef = "transactionManager2",
        value = "per.xueyuhuihui.mult.db2"
)

public class Db2JpaConfig {

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;

    @Resource
    @Qualifier(value = "db2DataSource")
    private DataSource dataSource;

    private Map<String, Object> getVendorProperties(){
        return hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings()
        );
    }


    @Bean(name = "entityManagerFactory2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory2(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(dataSource)
                .properties(jpaProperties.getProperties())
                .properties(getVendorProperties())
                .packages("per.xueyuhuihui.mult.db2")
                .persistenceUnit("persistenceUnit2")
                .build();
    }

    @Bean(name = "entityManager2")
    public EntityManager entityManager2(EntityManagerFactoryBuilder builder){
        return Objects.requireNonNull(entityManagerFactory2(builder).getObject()).createEntityManager();
    }

    @Bean(name = "transactionManager2")
    public PlatformTransactionManager transactionManager2(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory2(builder).getObject()));
    }


}
