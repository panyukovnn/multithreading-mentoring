package ru.panyukovnn.multithreadingmentoring.patterns.context.threadlocal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/thread-context")
public class ThreadContextController {

    @GetMapping
    public String getUsername() {
        return "Имя пользователя из контекста потока: " + ThreadContextHolder.get("username");
    }
}
