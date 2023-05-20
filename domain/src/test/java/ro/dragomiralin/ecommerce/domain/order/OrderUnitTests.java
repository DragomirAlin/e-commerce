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
import ro.dragomiralin.ecommerce.domain.payment.PaymentService;
import ro.dragomiralin.ecommerce.domain.product.ProductService;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.time.Instant;
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
    @MockBean
    private PaymentService paymentService;
    @Autowired
    private OrderServiceImpl classUnderTest;

    @Test
    public void testCreate() {
        var userId = 1L;
        var userDO = UserDO.builder()
                .id(userId)
                .build();
        var orderItems = List.of(OrderItemDO.builder()
                .id(1L)
                .productDO(ProductDO.builder()
                        .id(1L)
                        .name("Product")
                        .build())
                .build());
        var customerComments = "Test order";
        var orderDO = OrderDO.builder()
                .userDO(userDO)
                .orderItems(orderItems)
                .customerComments(customerComments)
                .build();

        var req = OrderDO.builder()
                .userDO(userDO)
                .status(OrderDOStatus.PENDING)
                .customerComments(customerComments)
                .orderedDate(Instant.now())
                .build();

        when(orderPort.save(any())).thenReturn(req);

        var result = classUnderTest.create(userDO, orderDO);

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
        var userDO = UserDO.builder()
                .id(userId)
                .build();
        var orderId = 2L;
        when(orderPort.findById(userId, orderId)).thenReturn(Optional.empty());

        assertThrows(OrderException.class, () -> {
            classUnderTest.get(userDO, orderId);
        });
    }


}
