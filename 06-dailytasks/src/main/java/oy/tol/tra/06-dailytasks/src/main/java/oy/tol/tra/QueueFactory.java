package oy.tol.tra;

public class QueueFactory {

    private QueueFactory() {
    }

    public static QueueInterface<Integer> createIntegerQueue(int capacity) {
        return new QueueImplementation<>(capacity);
    }
}
