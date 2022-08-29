package ro.dragomiralin.ecommerce.repository.product.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;
import ro.dragomiralin.ecommerce.repository.product.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductDOMapper {

    Product toProduct(ProductDO productDO);

    ProductDO toProductDO(Product product);

    PageDO<ProductDO> toPageDO(Page<Product> page);
}
