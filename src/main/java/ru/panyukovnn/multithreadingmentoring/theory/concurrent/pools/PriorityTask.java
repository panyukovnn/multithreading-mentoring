package ru.panyukovnn.multithreadingmentoring.theory.concurrent.pools;

public class PriorityTask implements Runnable, Comparable<PriorityTask> {

    private final int priority;

    public PriorityTask(int priority) {
        this.priority = priority;
    }

    @Override
    public void run() {
        System.out.println("Запущена задача с приоритетом: " + priority);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compareTo(PriorityTask anotherTask) {
        return Integer.compare(anotherTask.priority, this.priority);
    }
}