package ro.dragomiralin.controller.middleware;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ro.dragomiralin.ecommerce.infra.persistence.entity.User;

@RequiredArgsConstructor
public class UserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final ApplicationContext context;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        var resolver = context.getBean(UserResolver.class);
        return resolver.getUser();
    }
}
