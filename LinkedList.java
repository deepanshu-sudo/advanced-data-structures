/**
 * This class is used to create a linked list and contains all the methods to perform operations on the linked list.
 */

public class LinkedList {
    /** Data Members */
    Node first; // first node in the linked list
    int size; // number of elements in the linked list

    /** 
     * Constructor
     * Creates a linked list that is empty
     */
    public LinkedList() {
        first = new Node();
        size = 0;
    }

    /**
     * Method isEmpty
     * @return true if the linked list is empty
     *        false if the linked list is not empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method getSize
     * @return the number of elements in the linked list
     */
    public int getSize() {
        return size;
    }

    /**
     * Method checkIndex
     * Checks if the index is valid
     * @throws IndexOutOfBoundsException when index is not between 0 and size - 1
     */
    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Method get
     * @param index the index of the element to be returned
     * @return the element at the given index
     */
    public Object get(int index) {
        checkIndex(index);

        Node current = first;
        for(int i = 0; i < index; i++)
            current = current.next;

        return current.data;
    }

    /**
     * Method indexOf
     * @param data the data to be searched
     * @return the index of the data in the linked list
     *        -1 if the data is not found
     */
    public int indexOf(Object data) {
        Node current = first;
        for(int i = 0; i < size; i++) {
            if(current.data.equals(data))
                return i;
            current = current.next;
        }
        return -1;
    }

    /**
     * Method remove
     * @param index the index of the element to be removed
     * @return the element that is removed
     * @throws IndexOutOfBoundsException when index is not between 0 and size - 1
     */
    public Object remove(int index) {
        checkIndex(index);

        Object removedData = null;
        if(index == 0) {
            removedData = first.data;
            first = first.next;
        } else {
            Node current = first;
            for(int i = 0; i < index - 1; i++)
                current = current.next; // move to the predecessor of the desired node
            removedData = current.next.data;
            current.next = current.next.next; // remove the desired node
        }
        size--;
        return removedData;
    }

    /**
     * Method insert
     * @param index the index at which the data is to be inserted
     * @param data the data to be inserted
     * @throws IndexOutOfBoundsException when index is not between 0 and size
     * Inserts the data at the given index
     */
    public void insert(Object data, int index) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        
        if(index == 0) {
            first = new Node(data,first);
        } else {
            Node prev = first;
            for(int i = 0; i < index - 1; i++)
                prev = prev.next; // move to the predecessor of the desired node
            prev.next = new Node(data,prev.next); // insert the new node
        }
        size++;
    }

    /**
     * Method display
     * Displays the elements of the linked list in array form
     */
    public void display() {
        Node current = first;
        System.out.print("[");
        for(int i = 0; i < size-1; i++) {
            System.out.print(current.data.toString() + ", ");
            current = current.next;
        }
        System.out.print(current.data +"]");
        System.out.println();
    }

    /**
     * Main Method
     * Used to test the LinkedList class
     */
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        // Check deeply and thoroughly and with complex data
        list.insert(10,0);
        list.insert(20,0);
        list.insert(30,2);
        list.insert(40,3);
        list.insert(50,4);
        list.display();
        System.out.println("Size: " + list.getSize());
        System.out.println("Element at index 2: " + list.get(2));
        System.out.println("Index of 30: " + list.indexOf(30));
        System.out.println("Removed element at index 3: " + list.remove(3));
        list.display();
        System.out.println("Size: " + list.getSize());
        list.insert(100,2);
        list.display();
        System.out.println("Size: " + list.getSize());
    }
}
