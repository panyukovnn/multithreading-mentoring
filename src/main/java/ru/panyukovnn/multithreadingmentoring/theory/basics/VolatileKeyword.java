package ru.panyukovnn.multithreadingmentoring.theory.basics;

public class VolatileKeyword {

    private volatile boolean isAllowed = false;

    public boolean tryToDoSomething() {
        if (!isAllowed) {
            // action is not allowed
            return false;
        }

        // doSomething();

        return true;
    }

    public void allowAction() {
        isAllowed = true;
    }
}
