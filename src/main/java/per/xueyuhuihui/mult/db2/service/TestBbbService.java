package per.xueyuhuihui.mult.db2.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.xueyuhuihui.mult.db1.model.QTestAaa;
import per.xueyuhuihui.mult.db1.model.TestAaa;
import per.xueyuhuihui.mult.db1.repo.TestAaaRepository;
import per.xueyuhuihui.mult.db1.repo2.TestBbbRepository1;
import per.xueyuhuihui.mult.db2.model.QTestBbb;
import per.xueyuhuihui.mult.db2.model.TestBbb;
import per.xueyuhuihui.mult.db2.repo.TestBbbRepository;

import java.util.List;

@Slf4j
@Service
public class TestBbbService {

    @Autowired
    private TestBbbRepository testBbbRepository;
    @Autowired
    private TestBbbRepository1 testBbbRepository1;

    @Autowired
    private TestAaaRepository testAaaRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private JPAQueryFactory jpaQueryFactory2;

    public List<TestBbb> findAll() {
        QTestAaa qTestAaa = QTestAaa.testAaa;
        QTestBbb qTestBbb = QTestBbb.testBbb;

        List<TestAaa> testAaas = testAaaRepository.findAll();//1
        List<TestAaa> fetch = jpaQueryFactory.selectFrom(qTestAaa).fetch();//2 1=2
        //List<TestAaa> fetch1 = jpaQueryFactory2.selectFrom(qTestAaa).fetch();

        List<TestBbb> all_1 = testBbbRepository1.findAll();
        List<TestBbb> all_2 = testBbbRepository.findAll();
        List<TestBbb> fetch_1 = jpaQueryFactory.selectFrom(qTestBbb).fetch();
        List<TestBbb> fetch_2 = jpaQueryFactory2.selectFrom(qTestBbb).fetch();
        log.info("testAaas:{}", testAaas);
        log.info("fetch:{}", fetch);
        log.info("all_1:{}", all_1);
        log.info("all_2:{}", all_2);
        log.info("fetch_1:{}", fetch_1);
        log.info("fetch_2:{}", fetch_2);
        return all_2;
    }
}
