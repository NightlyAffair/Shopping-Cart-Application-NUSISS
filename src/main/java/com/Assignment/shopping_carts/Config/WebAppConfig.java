package com.Assignment.shopping_carts.Config;

import com.Assignment.shopping_carts.Interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


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
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/shoppingCartDetail/**","/api/**").excludePathPatterns("/Log/**","/api/register/**","api/register/**");
    }
}