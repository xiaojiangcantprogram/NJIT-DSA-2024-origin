package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {
    private Object[] elements;
    private int capacity;
    private int size = 0;
    private int headIndex = 0;
    private int tailIndex = -1;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Allocates a queue with a default capacity.
     *
     */
    public QueueImplementation() throws QueueAllocationException {
        capacity = DEFAULT_CAPACITY;
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * @param capacity The capacity of the queue.
     * @throws QueueAllocationException If you cannot allocate room for the internal array.
     */
    public QueueImplementation(int capacity) throws QueueAllocationException {
        if (capacity < 2) {
            throw new QueueAllocationException("Capacity is too small!");
        }
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot enqueue null element.");
        }
        if (size == capacity) {
            Object[] newArray = new Object[this.capacity * 2 + 1];
            int sourceIndex = headIndex;
            int destinationIndex = 0;
            int loopCount = size;
            while (loopCount-- > 0) {
                newArray[destinationIndex++] = elements[sourceIndex];
                sourceIndex = (sourceIndex + 1) % capacity;
            }
            headIndex = 0;
            tailIndex = destinationIndex - 1;
            elements = newArray;
            capacity = capacity * 2 + 1;
        }

        tailIndex = (tailIndex + 1) % capacity;
        elements[tailIndex] = element;
        size++;
    }

    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        E element = element();
        elements[headIndex] = null;
        headIndex = (headIndex + 1) % capacity;
        size--;
        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        return (E) elements[headIndex];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            elements[i] = null;
        }
        headIndex = 0;
        tailIndex = -1;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        int currentIndex = headIndex;
        int loopCount = size;
        while (loopCount-- > 0) {
            builder.append(elements[currentIndex].toString());
            currentIndex = (currentIndex + 1) % capacity;
            if (loopCount != 0) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
