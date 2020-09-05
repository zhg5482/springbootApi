package com.example.demo.config;

import com.example.demo.handler.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor initAuthInterceptor(){
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(initAuthInterceptor()).addPathPatterns("/test/**").excludePathPatterns("/login/**");
        registry.addInterceptor(initAuthInterceptor()).addPathPatterns("/**");
    }

}