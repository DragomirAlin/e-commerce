package ro.dragomiralin.ecommerce.domain.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ro.dragomiralin.ecommerce.domain.category.domain.CategoryDO;
import ro.dragomiralin.ecommerce.domain.category.impl.CategoryServiceImpl;
import ro.dragomiralin.ecommerce.domain.category.port.CategoryPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CategoryServiceImpl.class)
public class CategoryUnitTests {
    @MockBean
    private CategoryPort categoryPort;
    @Autowired
    private CategoryServiceImpl classUnderTest;

    @Test
    public void add_category() {
        var category = CategoryDO.builder()
                .name("Category")
                .build();
        when(categoryPort.save(category)).thenReturn(1L);

        var id = classUnderTest.add(category);
        assertEquals(1L, id);
    }
}
