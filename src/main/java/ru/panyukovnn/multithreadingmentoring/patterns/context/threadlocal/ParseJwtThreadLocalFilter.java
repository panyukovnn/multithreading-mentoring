package ru.panyukovnn.multithreadingmentoring.patterns.context.threadlocal;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import ru.panyukovnn.multithreadingmentoring.dto.JwtParams;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ParseJwtThreadLocalFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        JwtParams jwtParams = parseJwt(authorizationHeader);

        ThreadContextHolder.put("username", jwtParams.getUsername());

        chain.doFilter(request, response);
    }

    private JwtParams parseJwt(String authorizationHeader) {
        // здесь должен быть парсинг JWT

        return new JwtParams("John");
    }
}
