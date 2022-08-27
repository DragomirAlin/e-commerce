package ro.dragomiralin.ecommerce.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductCreateReq {
    private String name;
    private String description;
    private BigDecimal price;
    private List<Long> categories;
}
