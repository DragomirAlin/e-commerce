package ro.dragomiralin.ecommerce.boot.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.servers.ServerVariable;
import io.swagger.v3.oas.models.servers.ServerVariables;
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
                .description("Default server"));

        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce API")
                        .description("Spring E-Commerce API application")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")))
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
}
