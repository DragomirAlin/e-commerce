package ro.dragomiralin.ecommerce.domain.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ro.dragomiralin.ecommerce.domain.common.error.OrderException;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDOStatus;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderItemDO;
import ro.dragomiralin.ecommerce.domain.order.impl.OrderServiceImpl;
import ro.dragomiralin.ecommerce.domain.order.port.OrderPort;
import ro.dragomiralin.ecommerce.domain.product.ProductService;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = OrderServiceImpl.class)
public class OrderUnitTests {
    @MockBean
    private OrderPort orderPort;
    @MockBean
    private ProductService productService;
    @Autowired
    private OrderServiceImpl classUnderTest;

    @Test
    public void testCreate() {
        var userId = 1L;
        var orderItems = List.of(OrderItemDO.builder()
                .id(1L)
                .product(ProductDO.builder()
                        .id(1L)
                        .name("Product")
                        .build())
                .build());
        var customerComments = "Test order";
        var orderDO = OrderDO.builder()
                .userId(userId)
                .orderItems(orderItems)
                .customerComments(customerComments)
                .build();

        var req = OrderDO.builder()
                .userId(userId)
                .status(OrderDOStatus.PENDING)
                .orderItems(orderItems)
                .customerComments(customerComments)
                .orderedDate(new Date())
                .build();

        when(orderPort.save(any())).thenReturn(req);

        var result = classUnderTest.create(userId, orderDO);

        verify(orderPort, times(1)).save(any());
        assertEquals(req, result);
    }

    @Test
    public void testGetOrderNotFound() {
        when(orderPort.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OrderException.class, () -> {
            classUnderTest.get(1);
        });
    }

    @Test
    public void testGetOrderNotFoundByUserId() {
        var userId = 1L;
        var orderId = 2L;
        when(orderPort.findById(userId, orderId)).thenReturn(Optional.empty());

        assertThrows(OrderException.class, () -> {
            classUnderTest.get(userId, orderId);
        });
    }


}
