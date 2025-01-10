package com.transaction_microservice.transaction.infrastructure.feign.interceptor;

import com.transaction_microservice.transaction.domain.util.Util;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;


public class JwtRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String jwt = request.getHeader(Util.AUTH_HEADER);
        requestTemplate.header(Util.AUTH_HEADER,  jwt);
    }
}
