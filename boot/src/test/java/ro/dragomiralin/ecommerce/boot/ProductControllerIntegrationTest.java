package ro.dragomiralin.ecommerce.boot;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import ro.dragomiralin.ecommerce.boot.setup.BaseIntegrationTest;
import ro.dragomiralin.ecommerce.controller.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ro.dragomiralin.ecommerce.boot.util.JsonUtil.asJsonString;

public class ProductControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void list_products() throws Exception {
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void create_product_when_isAdmin() throws Exception {
        var product = ProductDTO.builder()
                .name("apple")
                .description("test")
                .price(BigDecimal.TEN)
                .categories(new ArrayList<>())
                .build();
        mockMvc
                .perform(
                        post("/admin/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(product))
                                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void create_product_when_isUser() throws Exception {
        var product = ProductDTO.builder()
                .name("apple2")
                .description("test")
                .price(BigDecimal.TEN)
                .categories(new ArrayList<>())
                .build();
        mockMvc
                .perform(
                        post("/admin/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(product)))
                .andExpect(status().isForbidden());
    }

}
