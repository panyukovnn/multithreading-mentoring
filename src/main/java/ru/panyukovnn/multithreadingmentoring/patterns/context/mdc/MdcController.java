package ru.panyukovnn.multithreadingmentoring.patterns.context.mdc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/mdc-context")
public class MdcController {

    @GetMapping
    public String getUsername() {
        return "Имя пользователя из контекста потока: " + MDC.get("username");
    }
}
