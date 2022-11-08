package ro.dragomiralin.ecommerce.domain.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ro.dragomiralin.ecommerce.domain.category.domain.CategoryDO;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;
import ro.dragomiralin.ecommerce.domain.product.impl.ProductServiceImpl;
import ro.dragomiralin.ecommerce.domain.product.port.ProductPort;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProductServiceImpl.class)
public class ProductUnitTests {
    @MockBean
    private ProductPort productPort;
    @Autowired
    private ProductServiceImpl classUnderTest;

    @Test
    public void add_product() {
        var product = ProductDO.builder()
                .id(1L)
                .name("Product")
                .description("This is a test product")
                .price(BigDecimal.ONE)
                .categories(List.of(CategoryDO.builder()
                        .id(1L)
                        .name("Category")
                        .build()))
                .build();



        when(productPort.save(product)).thenReturn(product);

        var createdProduct = classUnderTest.add(product);
        assertEquals(1, createdProduct.getId());
    }

}
