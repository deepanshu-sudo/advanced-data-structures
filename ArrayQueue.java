/**
 * Queue is a data structure that that follows the First In First Out (FIFO) principle.
 * It has restricted insertion and deletion operations, they are allowed at two different ends of the structure, which are called the front and rear.
 * The insertion is performed at the rear end and the deletion is performed at the front end.
 * This class implements a queue using an array.
 */
public class ArrayQueue<T> {
    /**
     * Data Members
     */
    T[] queue;
    int front;
    int rear;
    int length;

    /** Constructor */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int length) {
        if(length < 1)
            throw new IllegalArgumentException("Length of the queue should be greater than 0");

        this.length = length;
        queue = (T[]) new Object[length];
        front = -1;
        rear = -1;
    }

    /** @return true iff queue is empty */
    public boolean isEmpty() { return rear == -1; }

    /** @return the number of elements in the queue */
    public int size() {
        if(rear == -1)
            return 0;

        return (rear - front + 1);
    }

    /**
     * inserts an element at the rear end of the queue
     * @param element the element to be inserted into the queue
     * @throws IllegalStateException if the queue is full
     */
    public void insert(T element) {
        if(rear == length - 1)
            throw new IllegalStateException("Queue is Full");

        if(front == -1)
            front = 0;

        queue[++rear] = element;
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
            front++;
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

        System.out.print("Queue: ");        
        for(int i = front; i <= rear; i++)
            System.out.print(queue[i] + " ");
        System.out.println();
    }

    // Main method for testing
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        queue.display();

        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        queue.display();

        queue.remove();
        queue.display();

        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();
        queue.display();
    }
}