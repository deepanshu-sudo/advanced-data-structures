/**
 * CircularQueue.java
 * The Problem with ArrayQueue and LinkedQueue was that they were not able to reuse the space freed after deletion of elements.
 * To fix this problem we use CircularQueue.
 * A Circular Queue is a data structure that follows the First In First Out (FIFO) principle.
 * It has restricted insertion and deletion operations, they are allowed at two different ends of the structure, which are called the front and rear.
 * The insertion is performed at the rear end and the deletion is performed at the front end.
 * This class implements a queue using an array.
 */
public class CircularQueue<T> {
    /**
     * Data Members
     */
    T[] queue;
    int front;
    int rear;
    int length;

    /** Constructor */
    @SuppressWarnings("unchecked")
    public CircularQueue(int length) {
        if(length < 1)
            throw new IllegalArgumentException("Length of the queue should be greater than 0");

        this.length = length;
        queue = (T[]) new Object[length];
        front = -1;
        rear = -1;
    }

    /** @return true iff queue is empty */
    public boolean isEmpty() { return rear == -1; }

    /** 
     * @return the number of elements in the queue
     * How does this work?
     * If rear is -1, then the queue is empty, so we return 0.
     * If rear is greater than front, then the number of elements in the queue is rear - front + 1. For example: if rear is 3 and front is 0, and our queue goes like: [1, 2, 3, 4] then the number of elements in the queue is 3 - 0 + 1 = 4.
     * If rear is less than front, then the number of elements in the queue is length - front + rear + 1. For example: if rear is 2 and front is 3, and our queue goes like: [4, 5, 1, 2] then the number of elements in the queue is 4 - 3 + 2 + 1 = 4. The other formula on contrary will give us 2 - 3 + 1 = 0 which doesn't work because it doesn't take into account the elements from 0 to rear.
     */
    public int size() {
        if(rear == -1)
            return 0;
        else if(rear > front)
            return (rear - front + 1);
        else
            return (length - front + rear + 1);
    }

    /**
     * inserts an element at the rear of the queue
     * @param element the element to be inserted into the queue
     * @throws IllegalStateException if the queue is full
     */
    public void insert(T element) {
        if((rear + 1) % length == front)
            throw new IllegalStateException("Queue is Full");
        
        if(rear == -1) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear+1)%length;
        }

        queue[rear] = element;
    }

    /**
     * removes the front element from the queue and returns it
     * @return the front element of the queue
     * @throws IllegalStateException if the queue is empty
     */
    public T remove() {
        if(isEmpty())
            throw new IllegalStateException("Queue is Empty");

        T element = queue[front];

        if(front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front+1)%length;
        }

        return element;
    }

    /**
     * returns the front element of the queue without removing it
     */
    public T peek() {
        if(isEmpty())
            throw new IllegalStateException("Queue is Empty");

        return queue[front];
    }

    /**
     * Displays the elements of the queue
     */
    public void display() {
        if(isEmpty()) {
            System.out.println("Queue is Empty");
            return;
        }

        int i = front;
        System.out.println("Queue: ");
        while(i != rear) {
            System.out.print(queue[i] + " ");
            i = (i+1)%length;
        }

        System.out.println(queue[rear]);
    }

    // Main method for testing
    public static void main(String[] args) {
        CircularQueue<Integer> queue = new CircularQueue<>(5);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        queue.display();
        queue.remove();
        queue.remove();
        queue.display();
        queue.insert(6);
        queue.insert(7);
        queue.display();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.display();
    }
}
