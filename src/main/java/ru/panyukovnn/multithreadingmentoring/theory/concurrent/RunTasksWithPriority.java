package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.TaskThread;
import ru.panyukovnn.multithreadingmentoring.theory.concurrent.pools.PriorityTask;

import java.util.Comparator;
import java.util.concurrent.*;

@Slf4j
public class RunTasksWithPriority {

    private ExecutorService priorityExecutor = new ThreadPoolExecutor(
        1, 1,
        0, TimeUnit.SECONDS,
        new PriorityBlockingQueue<>()
    );

    public static void main(String[] args) throws InterruptedException {
        RunTasksWithPriority instance = new RunTasksWithPriority();
        instance.run100TasksSimultaneously();

        instance.priorityExecutor.shutdown();
    }

    public void run100TasksSimultaneously() {
        for (int i = 0; i < 100; i++) {
            priorityExecutor.execute(new PriorityTask(i));
        }
    }


}
