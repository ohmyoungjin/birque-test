package biz.birque.coding.test.config;

import biz.birque.coding.test.three.repository.QueryRepository;
import biz.birque.coding.test.three.repository.QueryRepositoryQueryDsl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class TestJpaConfig {
    @PersistenceContext
    private EntityManager entityManager;
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
    @Bean
    public JpaResultMapper jpaResultMapper() {
        return new JpaResultMapper();
    }
    @Bean
    public QueryRepository queryRepository() {
        return new QueryRepositoryQueryDsl(jpaQueryFactory());
    }
}