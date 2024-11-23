package ru.panyukovnn.multithreadingmentoring.theory.basics.deadlock;

@SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
public class DeadlockSolution {

    private static final Object TIE_LOCK = new Object();

    private static void transfer(Account from, Account to, long amount) {
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if (fromHash < toHash) {
            synchronized(from) {
                synchronized(to) {
                    from.decrement(amount);
                    to.decrement(amount);
                }
            }
        } else if (fromHash > toHash) {
            synchronized(to) {
                synchronized(from) {
                    from.decrement(amount);
                    to.decrement(amount);
                }
            }
        } else {
            synchronized(TIE_LOCK) {
                synchronized(from) {
                    synchronized(to) {
                        from.decrement(amount);
                        to.decrement(amount);
                    }
                }
            }
        }
    }
}
