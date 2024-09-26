package ru.panyukovnn.multithreadingmentoring.patterns.context.mdc;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import ru.panyukovnn.multithreadingmentoring.dto.JwtParams;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ParseJwtMdcFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        JwtParams jwtParams = parseJwt(authorizationHeader);

        MDC.put("username", jwtParams.getUsername());

        chain.doFilter(request, response);
    }

    private JwtParams parseJwt(String authorizationHeader) {
        // здесь должен быть парсинг JWT

        return new JwtParams("Jack");
    }
}
