package ro.dragomiralin.ecommerce.boot.setup;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import ro.dragomiralin.ecommerce.boot.ECommerceApplication;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.controller.middleware.UserResolver;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest(classes = {ECommerceApplication.class})
@AutoConfigureMockMvc
public class BaseIntegrationTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    protected UserResolver userResolver;

    @Container
    protected static final PostgreSQLContainer DB = PostgresqlSingletonContainer.INSTANCE.getContainer();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Before
    public void mockUserResolver() {
        when(userResolver.getUser()).thenReturn(UserDTO.builder()
                .id(1L)
                .build());
    }

}
