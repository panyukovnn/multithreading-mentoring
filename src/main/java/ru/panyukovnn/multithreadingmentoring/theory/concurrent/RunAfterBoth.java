package ru.panyukovnn.multithreadingmentoring.theory.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RunAfterBoth {

    /*
        Результат:
        bothCompleted
        Если раскомментировать последнюю строку, то результат:
        bothCompleted
        eitherCompleted

        Вывод: если .complete происходит после вызова runAfterBoth(), то он сработает
    */

    public static void main(String[] args) {
        CompletableFuture<String> futureNoCompleted = new CompletableFuture<>();

        CompletableFuture<String> futureCompleted1 = CompletableFuture
                .supplyAsync(() -> "futureCompleted1");

        CompletableFuture<String> futureCompleted2 = CompletableFuture
                .supplyAsync(() -> "futureCompleted2");

        CompletableFuture<Void> eitherCompleted = futureNoCompleted
                .runAfterBoth(futureCompleted1, () -> {
                    System.out.println("eitherCompleted");
                });

        CompletableFuture<Void> bothCompleted = futureCompleted1
                .runAfterBoth(futureCompleted2, () -> {
                    System.out.println("bothCompleted");
                });

        futureNoCompleted.complete("futureNoCompleted");
    }
}
