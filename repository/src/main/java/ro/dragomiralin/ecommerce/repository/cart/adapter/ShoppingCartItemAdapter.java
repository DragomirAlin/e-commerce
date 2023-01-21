package ro.dragomiralin.ecommerce.repository.cart.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.cart.port.ShoppingCartPort;
import ro.dragomiralin.ecommerce.domain.common.error.ShoppingCartItemException;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;
import ro.dragomiralin.ecommerce.repository.cart.mapper.ShoppingCartItemDOMapper;
import ro.dragomiralin.ecommerce.repository.cart.repository.ShoppingCartItemRepository;
import ro.dragomiralin.ecommerce.repository.user.mapper.UserDOMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartItemAdapter implements ShoppingCartPort {
    private final ShoppingCartItemDOMapper mapper;
    private final UserDOMapper userMapper;
    private final ShoppingCartItemRepository shoppingCartRepository;

    @Override
    public ShoppingCartItemDO save(ShoppingCartItemDO shoppingCartItemDO) {
        var shoppingCartItem = mapper.toShoppingCartItem(shoppingCartItemDO);
        var createdShoppingCartItem = shoppingCartRepository.save(shoppingCartItem);
        return mapper.toShoppingCartItemDO(createdShoppingCartItem);
    }

    @Override
    public Optional<ShoppingCartItemDO> findById(long id) {
        return shoppingCartRepository.findById(id)
                .map(mapper::toShoppingCartItemDO);
    }

    @Override
    public Optional<ShoppingCartItemDO> findByIdAndUser(UserDO userDO, long id) {
        var user = userMapper.toUser(userDO);
        return shoppingCartRepository.findByIdAndUser(user, id)
                .map(mapper::toShoppingCartItemDO);
    }

    @Override
    public void delete(long id) {
        var cartItem = shoppingCartRepository.findById(id);

        cartItem.ifPresentOrElse(shoppingCartRepository::delete, () -> {
            throw new ShoppingCartItemException("Cart item not found");
        });
    }

    @Override
    public PageDO<ShoppingCartItemDO> list(UserDO userDO, int page, int size) {
        var user = userMapper.toUser(userDO);

        var pageResult = shoppingCartRepository.findAllByUser(user, PageRequest.of(page, size));

        return mapper.toPageDO(pageResult);
    }

    @Override
    public List<ShoppingCartItemDO> list(UserDO userDO) {
        var user = userMapper.toUser(userDO);

        return shoppingCartRepository.findAllByUser(user)
                .stream()
                .map(mapper::toShoppingCartItemDO)
                .toList();
    }
}
