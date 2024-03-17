/**
 * Stack is a data structure that follows the Last In First Out (LIFO) principle.
 * It has restricted insertion and deletion operations, they are allowed only at one end of the structure, which is called the top.
 * The insertion and deletion operations are called push and pop, respectively.
 * This class implements a stack using an linked list.
 */

public class LinkedStack {
    /**
     * Data Members
     */
    LinkedList stack;
    
    /** Constructor */
    public LinkedStack() {
        stack = new LinkedList();
    }

    /** @return true iff stack is empty */
    public boolean isEmpty() { return stack.isEmpty(); }

    /** @return the number of elements in the stack */
    public int size() { return stack.getSize(); }

    /**
     * pushes an element onto the top of the stack
     */
    public void push(Object element) {
        stack.insert(element, 0);
    }

    /**
     * removes the top element from the stack and returns it
     */
    public Object pop() {
        return stack.remove(0);
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
        if(index < 0 || index >= stack.getSize()) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        return stack.get(stack.getSize() - index - 1);
    }
    
    /**
     * displays the elements of the stack in a nice format
     */
    public void display() {
        if(size() > 0) {
            stack.display();
        } else {
            System.out.println("Stack is empty");
        }
    }

    // Main Method for Testing
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
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
