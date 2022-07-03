package per.xueyuhuihui.mult.db1.service;

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
public class TestAaaService {

    @Autowired//调用db1
    private TestAaaRepository testAaaRepository;
    @Autowired//调用db2
    private TestBbbRepository testBbbRepository;
    @Autowired//调用db1
    private TestBbbRepository1 testBbbRepository1;

    @Autowired//调用db1
    private JPAQueryFactory jpaQueryFactory;

    @Autowired//调用db2
    private JPAQueryFactory jpaQueryFactory2;

    public List<TestAaa> findAll() {
        QTestAaa qTestAaa = QTestAaa.testAaa;
        QTestBbb qTestBbb = QTestBbb.testBbb;

        List<TestAaa> aa = testAaaRepository.findAll();
        List<TestAaa> fetch = jpaQueryFactory.selectFrom(qTestAaa).fetch();
        //List<TestAaa> fetch1 = jpaQueryFactory2.selectFrom(qTestAaa).fetch();

        List<TestBbb> bb = testBbbRepository.findAll();
        List<TestBbb> bb1 = testBbbRepository1.findAll();
        List<TestBbb> ff = jpaQueryFactory.selectFrom(qTestBbb).fetch();
        List<TestBbb> ff2 = jpaQueryFactory2.selectFrom(qTestBbb).fetch();//和上面的结果一样
        log.info("aa:{}", aa);
        log.info("fetch:{}", fetch);
        log.info("bb:{}", bb);
        log.info("bb1:{}", bb1);
        log.info("ff:{}", ff);
        log.info("ff2:{}", ff2);
        return aa;
    }
}
