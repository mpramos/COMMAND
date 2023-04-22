package edu.dharbor.bootcamp.rickandmorty.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static edu.dharbor.bootcamp.rickandmorty.util.constant.CommonConstant.STRING_SPACE;

@Slf4j
@Component
public class ClientHandlerInterceptor implements HandlerInterceptor {
    private long startTime;
    private String serviceName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Request received from client: " + request.getRemoteAddr());
        this.startTime = System.currentTimeMillis();
        this.serviceName = request.getRequestURI();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info(
                "Service: ".concat(this.serviceName)
                        .concat(STRING_SPACE)
                        .concat("execution time: ")
                        .concat(String.valueOf(System.currentTimeMillis() - this.startTime))
                        .concat(" ms")
        );
    }
}
