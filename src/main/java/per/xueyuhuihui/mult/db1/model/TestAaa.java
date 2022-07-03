package per.xueyuhuihui.mult.db1.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "TEST_AAA")
public class TestAaa {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
