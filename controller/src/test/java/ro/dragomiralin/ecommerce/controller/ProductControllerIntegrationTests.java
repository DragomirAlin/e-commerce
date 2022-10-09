package ro.dragomiralin.ecommerce.controller;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import ro.dragomiralin.ecommerce.controller.setup.BaseIntegrationTest;
import ro.dragomiralin.ecommerce.controller.setup.PostgresqlSingletonContainer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerIntegrationTests extends BaseIntegrationTest{
    @Container
    private static final PostgreSQLContainer DB = PostgresqlSingletonContainer.INSTANCE.getContainer();

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "USER")
    public void list_products() throws Exception {
        mockMvc
                .perform(
                        get("/product"))
                .andExpect(status().isOk());

    }

}
