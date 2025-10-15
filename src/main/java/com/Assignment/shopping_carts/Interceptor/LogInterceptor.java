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
 * Last Modified: 2025-10-13 21:00
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
        HttpSession session = request.getSession(false);


        boolean notLoggedIn = (session == null
                || session.getAttribute("login_status") == null
                || !(Boolean.TRUE.equals(session.getAttribute("login_status")))
                || session.getAttribute("customerId") == null);


        if (notLoggedIn) {

            String originalURL = request.getRequestURL().toString();
            String queryString = request.getQueryString();
            if (queryString != null) {
                originalURL += "?" + queryString;
            }

            //judge method
            String uri = request.getRequestURI();
            String method = request.getMethod();

            session = request.getSession(true);
            session.setAttribute("redirectAfterLogin", originalURL);

            // save post info if about favourites or adding to cart
            if (uri.equals("/products/cart/add") && method.equalsIgnoreCase("POST")) {
                session.setAttribute("pendingActionType", "cart");
                session.setAttribute("pendingProductId", request.getParameter("productId"));
                session.setAttribute("pendingQuantity", request.getParameter("quantity"));
                LOGGER.info("[Interceptor] Captured pending CART add: productId={}, qty={}",
                        request.getParameter("productId"), request.getParameter("quantity"));
            }
            //favourites part
            else if
            (uri.equals("/favourites/save") && method.equalsIgnoreCase("POST"))
            {
                session.setAttribute("pendingActionType", "favourite");
                session.setAttribute("pendingProductId", request.getParameter("productId"));
                LOGGER.info("[Interceptor] Captured pending FAVOURITE add: productId={}",
                        request.getParameter("productId"));
            }



            LOGGER.info("[LogInterceptor] Saved redirectAfterLogin: {}", originalURL);
            response.sendRedirect("/login");
            return false;
        }


        LOGGER.info("[LogInterceptor] User is logged in. Session ID: {}", session.getId());
        return true;



    }

}



