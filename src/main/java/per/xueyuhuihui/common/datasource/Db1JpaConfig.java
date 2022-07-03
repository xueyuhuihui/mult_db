package per.xueyuhuihui.common.datasource;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "entityManagerFactory1",
        transactionManagerRef = "transactionManager1",
        value = "per.xueyuhuihui.mult.db1"//扫描的包好像不能重复？
)
@ComponentScan({"per.xueyuhuihui.mult"})
public class Db1JpaConfig {

    @Resource
    private JpaProperties jpaProperties;
    @Resource
    private HibernateProperties hibernateProperties;

    @Resource
    @Qualifier(value = "db1DataSource")
    private DataSource dataSource1;

    private Map<String, Object> getVendorProperties(){
        /*Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        properties.put("hibernate.connection.charSet", "utf-8");
        properties.put("hibernate.show_sql", "false");*/
        Map<String, String> properties = jpaProperties.getProperties();
        return hibernateProperties.determineHibernateProperties(
                properties, new HibernateSettings()
        );
    }

    @Primary
    @Bean(name = "entityManagerFactory1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory1(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(dataSource1)
                .properties(jpaProperties.getProperties())
                .properties(getVendorProperties())
                .packages("per.xueyuhuihui.mult")
                .persistenceUnit("persistenceUnit1")
                .build();
    }

    /**
     * EntityManager是JPA中用于增删改查的接口，它的作用相当于一座桥梁，连接内存中的java对象和数据库的数据存储。
     * 容器托管的EntityManager对象最为简单，编程人员不需要考虑EntityManger的连接，释放以及复杂的事务问题等等，所有这些都交给容器来完成。
     *
     * @param builder
     * @return
     */
    @Primary
    @Bean(name = "entityManager1")
    public EntityManager entityManager1(EntityManagerFactoryBuilder builder){
        return Objects.requireNonNull(entityManagerFactory1(builder).getObject()).createEntityManager();
    }

    @Primary
    @Bean(name = "transactionManager1")
    public PlatformTransactionManager transactionManager1(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory1(builder).getObject()));
    }


}
