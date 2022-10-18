package ro.dragomiralin.ecommerce.repository.cart.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.cart.port.ShoppingCartPort;
import ro.dragomiralin.ecommerce.domain.common.error.ShoppingCartItemException;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.repository.cart.mapper.ShoppingCartItemDOMapper;
import ro.dragomiralin.ecommerce.repository.cart.repository.ShoppingCartItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartItemAdapter implements ShoppingCartPort {
    private final ShoppingCartItemDOMapper mapper;
    private final ShoppingCartItemRepository shoppingCartRepository;

    @Override
    public Long save(ShoppingCartItemDO shoppingCartItemDO) {
        var shoppingCartItem = mapper.toShoppingCartItem(shoppingCartItemDO);
        return shoppingCartRepository.save(shoppingCartItem).getId();
    }

    @Override
    public Optional<ShoppingCartItemDO> findById(Long id) {
        return shoppingCartRepository.findById(id)
                .map(mapper::toShoppingCartItemDO);
    }

    @Override
    public Optional<ShoppingCartItemDO> findByIdAndUserId(Long id, Long userId) {
        return shoppingCartRepository.findByIdAndUserId(id, userId)
                .map(mapper::toShoppingCartItemDO);
    }

    @Override
    public void delete(Long id) {
        var cartItem = shoppingCartRepository.findById(id);

        cartItem.ifPresentOrElse(shoppingCartRepository::delete, () -> {
            throw new ShoppingCartItemException("Cart item not found");
        });
    }

    @Override
    public PageDO<ShoppingCartItemDO> list(Long userId, int page, int size) {
        var pageResult = shoppingCartRepository.findAllByUserId(userId, PageRequest.of(page, size));

        return mapper.toPageDO(pageResult);
    }

    @Override
    public List<ShoppingCartItemDO> list(Long userId) {
        return shoppingCartRepository.findAllByUserId(userId)
                .stream()
                .map(mapper::toShoppingCartItemDO)
                .toList();
    }
}
