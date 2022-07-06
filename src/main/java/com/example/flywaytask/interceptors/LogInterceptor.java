package com.example.flywaytask.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = System.currentTimeMillis();
        String info = String.format("[pre-handle][%s][%s][%s]",
                request.getMethod(),
                request.getRequestURI(),
                new Timestamp(startTime));

        log.info(info);

        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        String info = String.format("[post-handle][status: %s][%s]",
                response.getStatus(),
                response.getContentType());

        log.info(info);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        String info = String.format("[after-completion][time-spent: %d]", (endTime - startTime));

        log.info(info);
    }
}
