package per.xueyuhuihui.mult.db1.ctrl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import per.xueyuhuihui.mult.db1.model.TestAaa;
import per.xueyuhuihui.mult.db1.service.TestAaaService;
import per.xueyuhuihui.mult.db2.model.TestBbb;
import per.xueyuhuihui.mult.db2.service.TestBbbService;

import java.util.List;

@Slf4j
@RestController
public class TestAaaCtrl {

    @Autowired
    private TestAaaService testAaaService;
    @Autowired
    private TestBbbService testBbbService;


    @GetMapping(value = "/testaa")
    public Object getList(){

        List<TestAaa> testAaaList = testAaaService.findAll();
        return testAaaList;
    }
    @GetMapping(value = "/testbb")
    public Object getBbbList(){

        List<TestBbb> testBbbList = testBbbService.findAll();
        return testBbbList;
    }
}
