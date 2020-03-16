package com.cisco.rbac.common.config;

import com.cisco.rbac.common.interceptor.JwtInterceptor;
import com.cisco.rbac.common.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * mvc 配置
 * Created by Hilox on 2018/11/15 0015.
 */
@Configuration
public class MyWebConfigurer extends WebMvcConfigurerAdapter {

    // 这里这么做是为了提前加载, 防止过滤器中@AutoWired注入为空
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

    @Bean
    public SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }

    // 自定义过滤规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("拦截器已启动");
        registry.addInterceptor(jwtInterceptor()).addPathPatterns("/controller/").excludePathPatterns("/**");
        registry.addInterceptor(securityInterceptor())
                .addPathPatterns("/userlist").addPathPatterns("/permissions").addPathPatterns("/showusers").addPathPatterns("/showroles").addPathPatterns("/showps");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 这里之所以多了一"/",是为了解决打war时访问不到问题
        registry.addResourceHandler("/**").addResourceLocations("/template", "classpath:/template");
    }

}