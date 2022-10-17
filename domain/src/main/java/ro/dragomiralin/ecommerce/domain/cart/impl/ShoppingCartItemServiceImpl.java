package ro.dragomiralin.ecommerce.domain.cart.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.cart.ShoppingCartItemService;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.cart.port.ShoppingCartPort;
import ro.dragomiralin.ecommerce.domain.common.error.ShoppingCartItemException;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.OrderService;
import ro.dragomiralin.ecommerce.domain.product.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {
    private final OrderService orderService;
    private final ShoppingCartPort shoppingCartPort;
    private final ProductService productService;

    @Override
    public long create(long userId, ShoppingCartItemDO shoppingCartItemDO) {
        var product = productService.get(shoppingCartItemDO.getProductId());

        var req = ShoppingCartItemDO.builder()
                .userId(userId)
                .productId(product.getId())
                .quantity(shoppingCartItemDO.getQuantity())
                .build();

        return shoppingCartPort.save(req);
    }

    @Override
    public ShoppingCartItemDO get(long id) {
        return shoppingCartPort.findById(id)
                .orElseThrow(() -> new ShoppingCartItemException("Shopping cart not found"));
    }

    @Override
    public ShoppingCartItemDO get(long id, long userId) {
        return shoppingCartPort.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ShoppingCartItemException("Shopping cart not found"));
    }

    @Override
    public ShoppingCartItemDO update(ShoppingCartItemDO shoppingCartItemDO) {
        return shoppingCartPort.findById(shoppingCartItemDO.getId())
                .map(shoppingCartItem -> shoppingCartPort.save(shoppingCartItemDO))
                .map(shoppingCartPort::findById)
                .orElseThrow(() -> new ShoppingCartItemException("Shopping cart not found"))
                .get();
    }

    @Override
    public PageDO<ShoppingCartItemDO> list(long userId, int page, int size) {
        return shoppingCartPort.list(userId, page, size);
    }

    @Override
    public List<ShoppingCartItemDO> list(long userId) {
        return shoppingCartPort.list(userId);
    }

    @Override
    public void delete(long userId, long id) {
        shoppingCartPort.findById(id)
                .ifPresentOrElse(shoppingCartItem -> shoppingCartPort.delete(id),
                        () -> {
                            throw new ShoppingCartItemException("Shopping cart not found");
                        });
    }

    @Override
    public void checkout(long userId) {
        var shoppingCartItems = list(userId);
        orderService.checkout(userId, shoppingCartItems);
        shoppingCartItems
                .forEach(shoppingCartItem -> shoppingCartPort.delete(shoppingCartItem.getId()));
    }
}
