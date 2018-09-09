package telerikacademy.extensionrepository.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestLoggingFilter implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public boolean preHandle(HttpServletRequest hsr,
                             HttpServletResponse hsr1, Object handler) throws Exception {
        log.info("Method: " + hsr.getMethod() + hsr.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1,
                           Object handler, ModelAndView mav) throws Exception {
        System.out.println("Response status: " + hsr1.getStatus());
    }
}
