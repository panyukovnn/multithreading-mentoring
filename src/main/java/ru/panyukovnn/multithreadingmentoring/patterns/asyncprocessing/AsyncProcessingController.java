package ru.panyukovnn.multithreadingmentoring.patterns.asyncprocessing;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.panyukovnn.multithreadingmentoring.util.MentoringService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/async-processing")
public class AsyncProcessingController {

    private final MentoringService mentoringService;

    @PostMapping("/call-sync")
    public String postCallSync() {
        return mentoringService.executeLongOperation();
    }

    @PostMapping("/call-sync-and-change-thread")
    public CompletableFuture<String> postCallAsyncAndChangeThread() {
        return mentoringService.executeSyncLongOperationInDifferentThreadPool();
    }

    @PostMapping("/call-async")
    public String postCallAsync() {
        return mentoringService.executeAsyncLongOperation();
    }
}
