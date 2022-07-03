package per.xueyuhuihui.mult.db1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import per.xueyuhuihui.mult.db1.model.TestAaa;

public interface TestAaaRepository extends JpaRepository<TestAaa, Long>, QuerydslPredicateExecutor<TestAaa> {
}
