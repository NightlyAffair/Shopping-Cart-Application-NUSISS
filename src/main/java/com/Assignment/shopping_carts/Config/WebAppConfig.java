package com.Assignment.shopping_carts.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.Assignment.shopping_carts.Interceptor.LogInterceptor;


/**
 * WebAppConfig
 * Author: Zhou Jason
 * Date: 2025-10-02
 * Participants: Jason
 * Modified by: Jason
 * Last Modified: 2025-10-07 11:00
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Autowired
    LogInterceptor loggingInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/shoppingCartDetail/**").excludePathPatterns("/Log/**","/api/register/**");
    }
}