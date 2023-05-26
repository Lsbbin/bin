package com.project.bin.cmmn.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
public class ApiInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle (
            HttpServletRequest request
            , HttpServletResponse response
            , Object handler ) throws Exception {

        if(request.getRequestURI().equals("/")) {
            response.sendRedirect("/swagger-ui/index.html");
            return false;
        }

        log.info("====================================================================");
        log.info("======================= Interceptor START ==========================");
        log.info(" Class       \t:  " + handler.getClass());

        Enumeration<String> paramNames = request.getParameterNames();

        log.info("# Request URI: " + request.getRequestURI());

        if (request.getRequestURI().contains("login") || request.getRequestURI().contains("signup")) {
            return true;
        }

        while (paramNames.hasMoreElements()) {
            String key = paramNames.nextElement();
            String value = request.getParameter(key);
            log.info("# RequestParameter: " + key + "=" + value + "");
        }

        return true;

    }

    @Override
    public void postHandle (
            HttpServletRequest request
            , HttpServletResponse response
            , Object handler
            , ModelAndView modelAndView ) throws Exception {

        log.info("======================= Interceptor END ============================");
        log.info("====================================================================");
    }

}
