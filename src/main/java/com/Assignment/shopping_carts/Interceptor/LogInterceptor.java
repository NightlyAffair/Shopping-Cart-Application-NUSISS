package com.Assignment.shopping_carts.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Enumeration;


/**
 * Log in status check interceptor
 * Author: Zhou Jayson
 * Date: 2025-10-02
 * Participants:
 * Modified by: Jayson
 * Last Modified: 2025-10-07 11:00
 */


@Component
public class LogInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(LogInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException {
        LOGGER.info("LoggingInterceptor preHandle()");
        LOGGER.info("Request URL: {}", request.getRequestURL());
        HttpSession session = request.getSession();
        Enumeration<String> paramNames =
                request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            LOGGER.info("Request param: {} = {}",
                    paramName, request.getParameter(paramName));
        }
        Boolean loginStatus = (Boolean) session.getAttribute("login_status");
        if (loginStatus == null || !loginStatus) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
}

}

