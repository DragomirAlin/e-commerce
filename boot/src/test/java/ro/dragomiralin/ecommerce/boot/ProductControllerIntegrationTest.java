package ro.dragomiralin.ecommerce.boot;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import ro.dragomiralin.ecommerce.boot.setup.BaseIntegrationTest;
import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
import ro.dragomiralin.ecommerce.controller.request.ProductCreateReq;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ro.dragomiralin.ecommerce.boot.util.JsonUtil.asJsonString;
import static ro.dragomiralin.ecommerce.boot.util.JsonUtil.getMvcResult;

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
    public void create_product_when_isAdmin_and_get() throws Exception {
        var product = ProductCreateReq.builder()
                .name("apple")
                .description("test")
                .price(BigDecimal.TEN)
                .categories(new ArrayList<>())
                .build();

        var result = mockMvc
                .perform(
                        post("/admin/product")
                                .contentType(APPLICATION_JSON)
                                .content(asJsonString(product))
                                .with(csrf().asHeader()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andReturn();

        var response = getMvcResult(result, new TypeReference<CustomResponse<Long>>() {});

        var id = response.getData();
        mockMvc
                .perform(
                        get("/product/{id}", id)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", equalTo(id.intValue())));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void create_product_when_isUser() throws Exception {
        var product = ProductCreateReq.builder()
                .name("apple2")
                .description("test")
                .price(BigDecimal.TEN)
                .categories(new ArrayList<>())
                .build();
        mockMvc
                .perform(
                        post("/admin/product")
                                .contentType(APPLICATION_JSON)
                                .content(asJsonString(product)))
                .andExpect(status().isForbidden());
    }

}
