package per.xueyuhuihui.mult.db1.repo2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import per.xueyuhuihui.mult.db2.model.TestBbb;

public interface TestBbbRepository1 extends JpaRepository<TestBbb, Long>, QuerydslPredicateExecutor<TestBbb> {
}
