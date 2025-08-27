package ru.panyukovnn.multithreadingmentoring.patterns.runasynctask;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.util.MentoringUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class RunAsyncTaskService {

    @Qualifier("runAsyncTasksElasticExecutor")
    private final ExecutorService runAsyncTasksElasticExecutor;

    public void runWithStraightExecuteMethod() {
        runAsyncTasksElasticExecutor.execute(() -> {
            executeLongOperation();
        });
    }

    @Async("runAsyncTasksElasticExecutor")
    public void runWithAnnotation() {
        executeLongOperation();
    }

    public void runWithCompletableFuture() {
        CompletableFuture.runAsync(() -> {
                executeLongOperation();
            },
            runAsyncTasksElasticExecutor);
    }

    private void executeLongOperation() {
        MentoringUtil.sleep(3000);
    }
}
