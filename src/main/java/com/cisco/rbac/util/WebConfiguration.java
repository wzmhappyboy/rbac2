package com.cisco.rbac.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: ARong
 * @Date: 2019/1/19 15:04
 * @Description: JWT 的拦截器配置
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    //spring拦截器加载在springcontentText之前，所以这里用@Bean提前加载。否则会导致过滤器中的@AutoWired注入为空
    @Bean
    JWTInterceptor jwtInterceptor(){
        return new JWTInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        System.out.println("JWT拦截器启动");
        registry.addInterceptor(jwtInterceptor())
                .excludePathPatterns("/login")
                .addPathPatterns();

    }
}


