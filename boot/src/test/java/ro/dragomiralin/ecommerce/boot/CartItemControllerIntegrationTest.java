//package ro.dragomiralin.ecommerce.boot;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.annotation.DirtiesContext;
//import ro.dragomiralin.ecommerce.boot.setup.BaseIntegrationTest;
//import ro.dragomiralin.ecommerce.controller.dto.ShoppingCartItemDTO;
//import ro.dragomiralin.ecommerce.controller.request.CreateShoppingCartItem;
//import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
//import ro.dragomiralin.ecommerce.domain.product.ProductService;
//import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.notNullValue;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static ro.dragomiralin.ecommerce.boot.util.JsonUtil.asJsonString;
//import static ro.dragomiralin.ecommerce.boot.util.JsonUtil.getMvcResult;
//
//public class CartItemControllerIntegrationTest extends BaseIntegrationTest {
//    @Autowired
//    private ProductService productService;
//    private static final String USER = "user";
//
//    @Test
//    @WithMockUser(username = "user", password = "user", roles = "USER")
//    public void create_cartItem_and_then_get() throws Exception {
//        var productDO = productService.add(ProductDO.builder()
//                .name("apple")
//                .description("this is an apple")
//                .categories(new ArrayList<>())
//                .price(BigDecimal.TEN)
//                .build());
//
//        var cartItem = CreateShoppingCartItem.builder()
//                .productId(productDO.getId())
//                .quantity(1)
//                .build();
//
//        var result = mockMvc
//                .perform(
//                        post("/cart")
//                                .contentType(APPLICATION_JSON)
//                                .content(asJsonString(cartItem))
//                                .with(csrf().asHeader()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data", notNullValue()))
//                .andReturn();
//
//        var response = getMvcResult(result, new TypeReference<CustomResponse<ShoppingCartItemDTO>>() {
//        });
//
//        var id = response.getData().getId();
//        mockMvc
//                .perform(
//                        get("/cart/{id}", id)
//                                .contentType(APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$.data", notNullValue()));
//    }
//
//    @Test
//    @WithMockUser(username = USER, password = "user", roles = "USER")
//    public void checkout_product() throws Exception {
//        var product = givenProduct();
//
//        var request = CreateShoppingCartItem.builder()
//                .productId(product.getId())
//                .quantity(2)
//                .build();
//        var itemAddedResult = mockMvc
//                .perform(
//                        post("/cart")
//                                .contentType(APPLICATION_JSON)
//                                .content(asJsonString(request)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$.data", notNullValue()))
//                .andReturn();
//
//        var response = getMvcResult(itemAddedResult, new TypeReference<CustomResponse<ShoppingCartItemDTO>>() {
//        });
//
//        var id = response.getData().getId();
//        mockMvc
//                .perform(
//                        get("/cart/{id}", id)
//                                .contentType(APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$.data", notNullValue()))
//                .andExpect(jsonPath("$.data.id", notNullValue()))
//                .andExpect(jsonPath("$.data.userId", notNullValue()))
//                .andExpect(jsonPath("$.data.quantity", equalTo(request.getQuantity())))
//                .andReturn();
//
//        mockMvc
//                .perform(
//                        post("/cart/checkout")
//                                .contentType(APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc
//                .perform(
//                        get("/cart/{id}", id)
//                                .contentType(APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//
//
//    }
//}
