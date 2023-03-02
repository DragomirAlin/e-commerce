package ro.dragomiralin.ecommerce.boot;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import ro.dragomiralin.ecommerce.boot.setup.BaseIntegrationTest;
import ro.dragomiralin.ecommerce.controller.request.CategoryCreateReq;

import java.util.UUID;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ro.dragomiralin.ecommerce.boot.util.JsonUtil.asJsonString;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryControllerIntegrationTest extends BaseIntegrationTest {

        @Test
        @WithMockUser(username = "user", password = "user", roles = "USER")
        public void list_categories() throws Exception {
            mockMvc.perform(get("/category"))
                    .andExpect(status().isOk())
                    .andReturn();
        }

        @Test
        @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
        public void create_category_when_isAdmin() throws Exception {

            var category = CategoryCreateReq.builder()
                    .name(UUID.randomUUID().toString())
                    .build();
            mockMvc
                    .perform(
                            post("/admin/category")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(asJsonString(category))
                                    .with(csrf().asHeader()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data", notNullValue()));
        }

        @Test
        @WithMockUser(username = "user", password = "user", roles = "USER")
        public void create_category_when_isUser() throws Exception {
            var category = CategoryCreateReq.builder()
                    .name(UUID.randomUUID().toString())
                    .build();
            mockMvc
                    .perform(
                            post("/admin/category")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(asJsonString(category))
                                    .with(csrf().asHeader()))
                    .andExpect(status().isForbidden());
        }
}
