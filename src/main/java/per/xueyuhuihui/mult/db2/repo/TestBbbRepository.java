package per.xueyuhuihui.mult.db2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import per.xueyuhuihui.mult.db2.model.TestBbb;

public interface TestBbbRepository extends JpaRepository<TestBbb, Long>, QuerydslPredicateExecutor<TestBbb> {
}
