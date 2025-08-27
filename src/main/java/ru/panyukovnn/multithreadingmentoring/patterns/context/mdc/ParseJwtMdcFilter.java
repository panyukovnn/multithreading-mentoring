package ru.panyukovnn.multithreadingmentoring.patterns.context.mdc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import ru.panyukovnn.multithreadingmentoring.dto.JwtParams;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ParseJwtMdcFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        log.info("Значение из контекста до его добавления: " + MDC.get("username"));

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
