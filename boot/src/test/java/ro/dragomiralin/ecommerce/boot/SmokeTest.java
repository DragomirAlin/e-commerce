package ro.dragomiralin.ecommerce.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.dragomiralin.ecommerce.controller.ProductController;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private ProductController productController;

    @Test
    public void contextLoads() {
        assertThat(productController).isNotNull();
    }
}
