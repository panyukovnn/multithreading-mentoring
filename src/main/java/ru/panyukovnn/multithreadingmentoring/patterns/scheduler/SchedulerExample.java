package ru.panyukovnn.multithreadingmentoring.patterns.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.util.MentoringUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerExample {

    private final MentoringUtil mentoringUtil;

    /**
     * Проблема 1. Конкурирующие запланированные задачи
     */

//    @Scheduled(cron = "*/2 * * * * *")
    public void refreshCache() {
        // Запрашиваем кеш из внешнего сервиса

        log.info("Кеш обновлен");
    }

//    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void prepareAnalytics() {
        log.info("Начинаю формировать аналитический отчёт");

        mentoringUtil.executeLongOperation();

        log.info("Формирование аналитического отчёта закончено");
    }

    /**
     * Решение Проблемы 1.
     */

    @Async("refreshCacheSingleExecutor")
//    @Scheduled(cron = "*/2 * * * * *")
    public void refreshCacheSolved() {
        log.info("Кеш обновлен");
    }

    // -------------------------------------------

    @Async("analyticsSingleExecutor")
//    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void prepareAnalyticsSolved() {
        log.info("Начинаю формировать аналитический отчёт");

        mentoringUtil.executeLongOperation();

        log.info("Формирование аналитического отчёта закончено");
    }

    // -------------------------------------------

    /**
     * Проблема 2. Запуск джобы проиходит чаще чем ее завершение.
     * При использовании Executors.newSingleThreadExecutor(), будет медленно утекать память, т.к. он использует неограниченную очередь задач
     */

    @Async("simpleSingleExecutor")
//    @Scheduled(fixedDelay = 1)
    public void sendRequestsTooOften() {
        log.info("Начинаю отправлять данные");

        mentoringUtil.executeLongOperation();

        log.info("Отправка данных закончена");
    }

    // -------------------------------------------

    /**
     * Решение Проблемы 2.
     */

    @Async("correctSingleExecutor")
//    @Scheduled(fixedDelay = 1000)
    public void sendRequestsTooOftenSolved() {
        log.info("Начинаю отправлять данные");

        mentoringUtil.executeLongOperation();

        log.info("Отправка данных закончена");
    }

}
