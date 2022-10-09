package ro.dragomiralin.ecommerce.domain.cart.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.cart.ShoppingCartService;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.cart.port.ShoppingCartPort;
import ro.dragomiralin.ecommerce.domain.common.error.ShoppingCartItemException;
import ro.dragomiralin.ecommerce.domain.order.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final OrderService orderService;
    private final ShoppingCartPort shoppingCartPort;

    @Override
    public long create(ShoppingCartItemDO shoppingCartItemDO) {
        return shoppingCartPort.save(shoppingCartItemDO);
    }

    @Override
    public ShoppingCartItemDO get(long id) {
        return shoppingCartPort.findById(id)
                .orElseThrow(() -> new ShoppingCartItemException("Shopping cart not found"));
    }

    @Override
    public ShoppingCartItemDO get(long userId, long id) {
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
        orderService.checkout(shoppingCartPort.list(userId));
    }
}
