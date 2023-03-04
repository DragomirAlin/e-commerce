package ro.dragomiralin.ecommerce.boot.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.servers.ServerVariable;
import io.swagger.v3.oas.models.servers.ServerVariables;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI springShopOpenAPI(SecurityScheme bearerSecurityScheme, SecurityScheme basicSecurityScheme) {
        List<SecurityRequirement> securityRequirements = List.of(new SecurityRequirement().addList(basicSecurityScheme.getName()), new SecurityRequirement().addList(basicSecurityScheme.getName()));
        Components availableAuthTypes = new Components()
                .addSecuritySchemes(bearerSecurityScheme.getName(), bearerSecurityScheme)
                .addSecuritySchemes(basicSecurityScheme.getName(), basicSecurityScheme);
        List<Server> servers = List.of(new Server().url("http://localhost:8080")
                        .variables(new ServerVariables()
                                .addServerVariable("port", new ServerVariable()
                                        .addEnumItem("8080")))
                        .description("Dev server"),
                new Server().url("https://{env}.dragomiralin.ro")
                        .variables(new ServerVariables()
                                .addServerVariable("env", new ServerVariable()
                                        .addEnumItem("prod")
                                        .addEnumItem("uat")))
                        .description("Deployed server"));

        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce API")
                        .description("Spring E-Commerce API application")
                        .version("v0.0.1")
                        .summary("Spring E-Commerce API application")
                        .contact(new Contact()
                                .name("Dragomir Alin")
                                .url("https://github.com/DragomirAlin")
                                .email("dragomirdanielalin@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")))
                .tags(List.of(
                        new Tag().name("Product").description("Product API"),
                        new Tag().name("Category").description("Category API"),
                        new Tag().name("Order").description("Order API"),
                        new Tag().name("User").description("User API")
                ))
                .openapi("3.0.0")
                .components(availableAuthTypes)
                .security(securityRequirements)
                .servers(servers)
                .externalDocs(new ExternalDocumentation()
                        .description("E-Commerce Wiki Documentation")
                        .url("https://github.com/DragomirAlin/e-commerce"));
    }


    @Bean
    public SecurityScheme bearerSecurityScheme() {
        return new SecurityScheme()
                .name("bearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }

    @Bean
    public SecurityScheme basicSecurityScheme() {
        return new SecurityScheme()
                .name("basicAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic")
                .bearerFormat("basic");
    }

    @Bean
    public GroupedOpenApi ecommerceApi() {
        return GroupedOpenApi.builder()
                .displayName("test")
                .group("ecommerce")
                .pathsToMatch("/api/v1/**", "/v1/**")
                .packagesToScan("ro.dragomiralin.ecommerce.controller")
                .build();
    }

}
