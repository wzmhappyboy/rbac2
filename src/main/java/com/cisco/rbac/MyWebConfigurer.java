package com.cisco.rbac;

import com.cisco.rbac.interceptor.JwtInterceptor;
import com.cisco.rbac.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
        registry.addInterceptor(jwtInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(securityInterceptor()).excludePathPatterns("/static/*")
                .addPathPatterns("/getrolesbyuserid").addPathPatterns("/uroles").addPathPatterns("/showusers").addPathPatterns("/showroles").addPathPatterns("/showps");
        super.addInterceptors(registry);
    }
}

