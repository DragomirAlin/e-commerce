package ro.dragomiralin.ecommerce.domain.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ro.dragomiralin.ecommerce.domain.category.domain.CategoryDO;
import ro.dragomiralin.ecommerce.domain.category.error.CategoryNotFoundException;
import ro.dragomiralin.ecommerce.domain.category.impl.CategoryServiceImpl;
import ro.dragomiralin.ecommerce.domain.category.port.CategoryPort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CategoryServiceImpl.class)
public class CategoryUnitTests {
    @MockBean
    private CategoryPort categoryPort;
    @Autowired
    private CategoryServiceImpl classUnderTest;

    @Test
    public void testAddCategory() {
        var category = CategoryDO.builder()
                .id(1L)
                .name("Category")
                .build();
        when(categoryPort.save(category)).thenReturn(category);

        var createdCategory = classUnderTest.add(category);
        assertEquals(1L, createdCategory.getId());
    }

    @Test
    public void testGetCategoryNotFound() {
        when(categoryPort.get(anyLong())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> {
            classUnderTest.get(1);
        });
    }

    @Test
    public void testDelete() {
        var id = 1L;
        var category = CategoryDO.builder()
                .id(id)
                .name("Category")
                .build();

        when(categoryPort.get(id)).thenReturn(Optional.of(category));

        classUnderTest.delete(id);
        verify(categoryPort, times(1)).delete(id);
    }

}
