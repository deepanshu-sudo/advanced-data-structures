import java.util.EmptyStackException;

/**
 * Stack is a data structure that follows the Last In First Out (LIFO) principle.
 * It has restricted insertion and deletion operations, they are allowed only at one end of the structure, which is called the top.
 * The insertion and deletion operations are called push and pop, respectively.
 * This class implements a stack using an array.
 */
public class ArrayStack {
    /**
     * Data Members
     */
    private Object[] stack;
    private int top;
    private int length;

    /** Constructor */
    public ArrayStack(int length) {
        if(length < 1)
            throw new IllegalArgumentException("Length of the stack should be greater than 0");

        this.length = length;
        stack = new Object[length];
        top = -1;
    }

    /** @return true iff stack is empty */
    public boolean isEmpty() { return top == -1; }

    /** @return the number of elements in the stack */
    public int size() { return top + 1; }

    /** 
     * pushes an element onto the top of the stack
     * @param element the element to be pushed onto the stack
     * @throws StackOverflowError if the stack is full
     */
    public void push(Object element) {
        if(size() == length)
            throw new StackOverflowError("Stack is full");

        stack[++top] = element;
    }

    /**
     * removes the top element from the stack and returns it
     * @return the top element of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public Object pop() {
        if(isEmpty())
            throw new EmptyStackException();

        return stack[top--];
    }

    /**
     * access index-th element from the top of the stack
     * for example: peep(0) returns the top element of the stack
     * if the elements of stack are [1, 2, 3, 4, 5], then peep(0) returns 5, peep(1) returns 4, and so on
     * the index calculates is as follows:
     * index = 0, returns the top element(5) by subtracting 0 from 4
     * index = 1, returns the second element(4) by subtracting 1 from 4
     * index = 2, returns the third element(3) by subtracting 2 from 4
     * and so on
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public Object peep(int index) {
        if(index < 0 || index > top)
            throw new IndexOutOfBoundsException("Index is out of range");

        return stack[top - index];
    }

    /**
     * displays the elements of the stack in a nice format
     * from top to bottom
     */
    public void display() {
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

        System.out.print("Stack: ");
        for(int i = top; i >= 0; i--)
            System.out.print(stack[i] + " ");
        System.out.println();
    }

    // Main Method for Testing
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.display();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.display();

        System.out.println("Peep(0): " + stack.peep(0));
        System.out.println("Peep(1): " + stack.peep(1));
        System.out.println("Peep(2): " + stack.peep(2));

        System.out.println("Pop: " + stack.pop());
        stack.display();

        stack.push(4);
        stack.push(5);
        stack.display();

        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
        stack.display();

        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
        stack.display();
    }
}