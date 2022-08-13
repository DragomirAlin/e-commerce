package ro.dragomiralin.ecommerce.infra.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.dragomiralin.ecommerce.domain.service.ProductService;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
}
