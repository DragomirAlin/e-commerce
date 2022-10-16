package ro.dragomiralin.ecommerce.boot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.dragomiralin.ecommerce.controller.middleware.UserMethodArgumentResolver;
import ro.dragomiralin.ecommerce.controller.middleware.UserResolver;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final UserResolver userResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserMethodArgumentResolver(userResolver));
    }
}
