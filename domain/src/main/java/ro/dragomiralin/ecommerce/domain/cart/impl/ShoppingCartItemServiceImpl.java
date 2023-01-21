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
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {
    private final OrderService orderService;
    private final ShoppingCartPort shoppingCartPort;
    private final ProductService productService;

    @Override
    public ShoppingCartItemDO create(UserDO userDO, ShoppingCartItemDO shoppingCartItemDO) {
        var product = productService.get(shoppingCartItemDO.getProductId());

        var req = ShoppingCartItemDO.builder()
                .userDO(userDO)
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
    public ShoppingCartItemDO get(UserDO userDO, long id) {
        return shoppingCartPort.findByIdAndUser(userDO, id)
                .orElseThrow(() -> new ShoppingCartItemException("Shopping cart not found"));
    }

    @Override
    public ShoppingCartItemDO update(ShoppingCartItemDO shoppingCartItemDO) {
        return shoppingCartPort.findById(shoppingCartItemDO.getId())
                .map(shoppingCartItem -> shoppingCartPort.save(shoppingCartItemDO))
                .orElseThrow(() -> new ShoppingCartItemException("Shopping cart not found"));
    }

    @Override
    public PageDO<ShoppingCartItemDO> list(UserDO userDO, int page, int size) {
        return shoppingCartPort.list(userDO, page, size);
    }

    @Override
    public List<ShoppingCartItemDO> list(UserDO userDO) {
        return shoppingCartPort.list(userDO);
    }

    @Override
    public void delete(UserDO userDO, long id) {
        shoppingCartPort.findById(id)
                .ifPresentOrElse(shoppingCartItem -> shoppingCartPort.delete(id),
                        () -> {
                            throw new ShoppingCartItemException("Shopping cart not found");
                        });
    }

    @Override
    public void checkout(UserDO userDO) {
        var shoppingCartItems = list(userDO);
        orderService.checkout(userDO.getId(), shoppingCartItems);
        shoppingCartItems
                .forEach(shoppingCartItem -> shoppingCartPort.delete(shoppingCartItem.getId()));
    }
}
