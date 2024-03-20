/**
 * Queue is a data structure that that follows the First In First Out (FIFO) principle.
 * It has restricted insertion and deletion operations, they are allowed at two different ends of the structure, which are called the front and rear.
 * The insertion is performed at the rear end and the deletion is performed at the front end.
 * This class implements a queue using an linked list.
 */
public class LinkedQueue<T> {
    /**
     * Data Members
     */
    LinkedList queue;

    /** Constructor */
    public LinkedQueue() {
        queue = new LinkedList();
    }

    /** @return true iff queue is empty */
    public boolean isEmpty() { return queue.isEmpty(); }

    /** @return the number of elements in the queue */
    public int size() { return queue.getSize(); }

    /**
     * inserts an element at the rear of the queue
     */
    public void insert(T element) {
        queue.insert(element, queue.getSize());
    }

    /**
     * removes the front element from the queue and returns it
     */
    @SuppressWarnings("unchecked")
    public T remove() {
        return (T) queue.remove(0);
    }

    /**
     * displays the elements of the queue in a nice format
     */
    public void display() {
        queue.display();
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue<Integer>();
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        queue.display();
        System.out.println("Removed: " + queue.remove());
        queue.display();
    }
}
