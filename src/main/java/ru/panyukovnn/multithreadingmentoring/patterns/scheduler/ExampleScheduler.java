package ru.panyukovnn.multithreadingmentoring.patterns.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.panyukovnn.multithreadingmentoring.util.MentoringService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExampleScheduler {

    private final MentoringService mentoringService;

    // Проблема 1. Конкурирующие шедулеры

//    @Async("simpleSingleExecutor")
//    @Scheduled(cron = "*/2 * * * * *")
    public void refreshCache() {
        log.info("Кеш обновлен");
    }

//    @Async("simpleSingleExecutor2")
//    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void prepareAnalytics() {
        log.info("Начинаю формировать аналитический отчёт");

        mentoringService.executeLongOperation();

        log.info("Формирование аналитического отчёта закончено");
    }

    // -------------------------------------------

    // Проблема 2. Запуск джобы проиходит чаще чем ее завершение

//    @Async("correctSingleExecutor")
//    @Scheduled(fixedDelay = 1000)
    public void sendRequestsTooOften() {
        log.info("Начинаю отправлять данные");

        mentoringService.executeLongOperation();

        log.info("Отправка данных закончена");
    }
}
