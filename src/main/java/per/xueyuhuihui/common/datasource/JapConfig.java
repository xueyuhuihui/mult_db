package per.xueyuhuihui.common.datasource;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

@Component
public class JapConfig {

    @Resource
    @Qualifier(value = "entityManager1")
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }

    @Resource
    @Qualifier(value = "entityManager2")
    private EntityManager entityManager2;

    @Bean
    public JPAQueryFactory jpaQueryFactory2(){
        return new JPAQueryFactory(entityManager2);
    }
}
