import java.util.Arrays;

/**
 * Let a stack and circular queue is represented by an array [0:2m-1] for m > 0. Where first half array is used for stack and 2nd half array is circular queue. Assume that stack grow from beginning to mid and queue from end to mid. Write algorithms for push(x) operation of the stack and insert(x) and del() operations of the circular queue.
 */
public class ArrayStackQueue {
    /** Data Members */
    private int[] arr;
    private int top, front, rear;
    private int m;

    /** Constructor */
    public ArrayStackQueue(int m) {
        arr = new int[2 * m];
        top = -1;
        rear = 2*m;
        front = 2*m;
        this.m = m;
    }

    /**
     * Push:
     * Pushes an element x into the stack.
     * The size of the stack is m.
     * 
     * Algorithm:
     * 1. If top = m - 1, then
     *    1.1. Print "Stack Overflow"
     *    1.2. Exit
     * 2. top = top + 1
     * 3. arr[top] = x
     * 4. Exit
     */
    public void push(int x) {
        if(top == m - 1) {
            throw new StackOverflowError("Stack Overflow");
        }
        arr[++top] = x;
    }

    /**
     * Insert:
     * Inserts an element x into the circular queue implemeted from right to mid.
     * 
     * Algorithm:
     * 1. If (2 * m - 1) - (2 * m - rear) % m = front, then
     *      1.1. Print "Queue Overflow"
     *      1.2. Exit
     * 2. If rear = 2 * m, then
     *      2.1. rear = 2 * m - 1
     *      2.2. front = 2 * m - 1
     * 3. Else,
     *      3.1. rear = (2 * m - 1) - (2 * m - rear) % m
     * 4. arr[rear] = x
     * 5. Exit
     */
    public void insert(int x) {
        if((2 * m - 1) - (2 * m - rear) % m == front) {
            throw new StackOverflowError("Queue Overflow");
        }

        if(rear == 2 * m) {
            rear = 2 * m - 1;
            front = 2 * m - 1;
        } else {
            rear = (2 * m - 1) - (2 * m - rear) % m;
        }

        arr[rear] = x;
    }

    /**
     * Delete:
     * Deletes an element from the circular queue implemented from right to mid.
     * 
     * Algorithm:
     * 1. If front = 2 * m, then
     *      1.1. Print "Queue is empty."
     *      1.2. Exit
     * 2. x = arr[front]
     * 3. If front = rear, then
     *      3.1. front = 2 * m
     *      3.2. rear = 2 * m
     * 4. Else,
     *      4.1. front = (2 * m - 1) - (2 * m - front) % m
     * 5. Exit
     */
    public int del() {
        if(front == 2 * m) {
            throw new StackOverflowError("Queue is empty.");
        }

        int x = arr[front];
        arr[front] = 0;

        if(front == rear) {
            front = 2 * m;
            rear = 2 * m;
        } else {
            front = (2 * m - 1) - (2 * m - front) % m;
        }

        return x;
    }

    public static void main(String[] args) {
        ArrayStackQueue asq = new ArrayStackQueue(5);
        asq.push(1);
        asq.push(2);
        asq.push(3);
        asq.push(4);
        asq.push(5);
        System.out.println(Arrays.toString(asq.arr));

        asq.insert(6);
        asq.insert(7);
        asq.insert(8);
        asq.insert(9);
        asq.insert(10);
        System.out.println(Arrays.toString(asq.arr));

        System.out.println(asq.del());
        System.out.println(asq.del());
        System.out.println(Arrays.toString(asq.arr));

        asq.insert(11);
        asq.insert(12);
        System.out.println(Arrays.toString(asq.arr));

        System.out.println(asq.del());
        System.out.println(asq.del());
        System.out.println(asq.del());
        
        System.out.println(Arrays.toString(asq.arr));
    }
}
