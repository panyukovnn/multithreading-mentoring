package ru.panyukovnn.multithreadingmentoring.theory.basics.deadlock;

import static ru.panyukovnn.multithreadingmentoring.util.MentoringUtil.sleep;

/**
 * Задача с собеседования
 */
@SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
public class DeadlockExample {

    public void transfer(Account from, Account to, long amount) {
        synchronized (from) {
            sleep(1000); // Для воспроизведения deadlock'а

            synchronized (to) {
                from.decrement(amount);
                to.decrement(amount);
            }
        }
    }
}
