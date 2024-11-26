package ru.panyukovnn.multithreadingmentoring.theory.executors;

import java.util.concurrent.*;

public class ExecutorsExample {

    // ------------- можно использовать в коммерческом проекте -------------

    private final ExecutorService fixedExecutor = Executors.newFixedThreadPool(8);
    private final ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
    private final ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(8);

    public void exampleScheduleTask() {
        scheduledExecutor.schedule(() -> {}, 1, TimeUnit.SECONDS);
    }

    // ------------- не стоит использовать в коммерческом проекте -------------

    private final ExecutorService forkJoinPool = Executors.newWorkStealingPool();
    private final ExecutorService cachedExecutor = Executors.newCachedThreadPool();
//    private final ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();

    // ------------- правильное создание эластичного пула потоков -------------

    public ThreadPoolExecutor createElasticExecutor(int threads, int queueCapacity) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(queueCapacity);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            threads, threads,
            60L, TimeUnit.SECONDS,
            queue, new ThreadPoolExecutor.AbortPolicy());

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        return threadPoolExecutor;
    }
}
