package ro.dragomiralin.ecommerce.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableCaching
@ComponentScan(basePackages = {"ro.dragomiralin.ecommerce.*"})
@EnableJpaRepositories(basePackages = "ro.dragomiralin.ecommerce.repository.*")
@EntityScan(basePackages ={"ro.dragomiralin.ecommerce.repository.*"})
@SpringBootApplication
@EnableTransactionManagement
public class ECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }
}
