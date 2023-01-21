package ro.dragomiralin.ecommerce.domain.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.cart.impl.ShoppingCartItemServiceImpl;
import ro.dragomiralin.ecommerce.domain.cart.port.ShoppingCartPort;
import ro.dragomiralin.ecommerce.domain.common.error.ShoppingCartItemException;
import ro.dragomiralin.ecommerce.domain.order.OrderService;
import ro.dragomiralin.ecommerce.domain.product.ProductService;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ShoppingCartItemServiceImpl.class)
public class ShoppingCartUnitTests {
    @MockBean
    private ShoppingCartPort shoppingCartPort;
    @MockBean
    private ProductService productService;
    @MockBean
    private OrderService orderService;
    @Autowired
    private ShoppingCartItemServiceImpl classUnderTest;

    @Test
    public void testCreatePort() {
        var userId = 1L;
        var productId = 2L;
        var quantity = 3;
        var product = ProductDO.builder()
                .id(productId)
                .name("Product")
                .build();
        var shoppingCartItemDO = ShoppingCartItemDO.builder()
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .build();

        when(productService.get(productId)).thenReturn(product);
        when(shoppingCartPort.save(any(ShoppingCartItemDO.class))).thenReturn(shoppingCartItemDO);

        var result = classUnderTest.create(userId, shoppingCartItemDO);

        verify(productService, times(1)).get(productId);
        verify(shoppingCartPort, times(1)).save(shoppingCartItemDO);
        assertEquals(shoppingCartItemDO, result);
    }


    @Test
    public void testGetShoppingCartNotFound() {
        when(shoppingCartPort.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ShoppingCartItemException.class, () -> {
            classUnderTest.get(1);
        });
    }

    @Test
    public void testGetShoppingCartByUserIdNotFound() {
        when(shoppingCartPort.findByIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.empty());

        assertThrows(ShoppingCartItemException.class, () -> {
            classUnderTest.get(1, 1);
        });
    }

    @Test
    public void testUpdate() {
        var id = 1L;
        var shoppingCartItemDO = new ShoppingCartItemDO(id, 1L, 2L, 3);

        when(shoppingCartPort.findById(id)).thenReturn(Optional.of(shoppingCartItemDO));
        when(shoppingCartPort.save(shoppingCartItemDO)).thenReturn(shoppingCartItemDO);

        var result = classUnderTest.update(shoppingCartItemDO);

        verify(shoppingCartPort, times(1)).findById(id);
        verify(shoppingCartPort, times(1)).save(shoppingCartItemDO);
        assertEquals(shoppingCartItemDO, result);
    }

    @Test
    public void testDelete() {
        var id = 1L;
        var userId = 2L;
        var shoppingCartItem = ShoppingCartItemDO.builder()
                .id(id)
                .userId(userId)
                .build();

        when(shoppingCartPort.findByIdAndUserId(id, userId)).thenReturn(Optional.of(shoppingCartItem));
        when(shoppingCartPort.findById(id)).thenReturn(Optional.of(shoppingCartItem));
        classUnderTest.delete(userId, id);
        verify(shoppingCartPort, times(1)).delete(id);
    }

    @Test
    public void testDeleteShoppingCartNotFound() {
        var id = 1L;
        var userId = 2L;
        when(shoppingCartPort.findById(id)).thenReturn(Optional.empty());
        assertThrows(ShoppingCartItemException.class, () -> {
            classUnderTest.delete(userId, id);
        });
        verify(shoppingCartPort, never()).delete(id);
    }

    @Test
    public void testCheckout() {
        var userId = 1L;
        var shoppingCartItems = List.of(
                ShoppingCartItemDO.builder()
                        .id(1L)
                        .userId(userId)
                        .productId(2L)
                        .quantity(3)
                        .build(),
                ShoppingCartItemDO.builder()
                        .id(2L)
                        .userId(userId)
                        .productId(3L)
                        .quantity(4)
                        .build()
        );

        when(shoppingCartPort.list(userId)).thenReturn(shoppingCartItems);

        classUnderTest.checkout(userId);

        verify(orderService, times(1)).checkout(userId, shoppingCartItems);
        shoppingCartItems.forEach(item -> verify(shoppingCartPort, times(1)).delete(item.getId()));
    }
}
