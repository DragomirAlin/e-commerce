package ro.dragomiralin.ecommerce.repository.cart.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartDO;
import ro.dragomiralin.ecommerce.domain.cart.port.ShoppingCartPort;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.repository.cart.mapper.ShoppingCartDOMapper;
import ro.dragomiralin.ecommerce.repository.cart.repository.ShoppingCartRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartAdapter implements ShoppingCartPort {
    private final ShoppingCartDOMapper mapper;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public long save(ShoppingCartDO shoppingCartDO) {
        return 0;
    }

    @Override
    public Optional<ShoppingCartDO> findById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public PageDO<ShoppingCartDO> list(int page, int size) {
        return null;
    }

    @Override
    public List<ShoppingCartDO> list() {
        return null;
    }
}
