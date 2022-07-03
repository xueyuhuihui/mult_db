package per.xueyuhuihui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MultDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultDbApplication.class, args);
    }
}
