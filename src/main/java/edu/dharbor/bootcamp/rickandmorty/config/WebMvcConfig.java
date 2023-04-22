package edu.dharbor.bootcamp.rickandmorty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final ClientHandlerInterceptor clientHandlerInterceptor;

    @Autowired
    public WebMvcConfig(ClientHandlerInterceptor clientHandlerInterceptor) {
        this.clientHandlerInterceptor = clientHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientHandlerInterceptor);
    }
}
