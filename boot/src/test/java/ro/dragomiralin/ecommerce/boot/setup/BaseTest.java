package ro.dragomiralin.ecommerce.boot.setup;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.dragomiralin.ecommerce.domain.product.ProductService;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BaseTest {
    @Autowired
    private ProductService productService;

    public List<ProductDO> generateProducts(int numberOfProducts) {
        List<ProductDO> products = new ArrayList<>();
        for (int i = 0; i < numberOfProducts; i++) {
            products.add(givenProduct());
        }
        return products;
    }

    public ProductDO givenProduct() {
        var faker = new Faker();

        var fruit = faker.food().fruit();
        return productService.add(ProductDO.builder()
                .name(fruit)
                .categories(new ArrayList<>())
                .description(String.format("This is a %s", fruit))
                .price(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)))
                .build());
    }

}
