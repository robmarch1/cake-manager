package cake.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "cake.api.repository")
public class CakeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeApiApplication.class);
    }
}
