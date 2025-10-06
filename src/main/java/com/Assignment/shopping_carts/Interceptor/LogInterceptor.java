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
        if(session==null){             /// have not logged in situation 1;
            response.sendRedirect("/login");
            return false;
        }
        boolean login_status = (boolean) session.getAttribute("login_status");
        if (login_status == false){
            response.sendRedirect("/login");
            return false;
        }
        return true;
}

}

