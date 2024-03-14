/**
 * CircularLinkedList
 * This class is used to create a circular linked list and contains all the methods to perform operations on the circular linked list.
 */

public class CircularLinkedList {
    /** Data Members */
    Node first; // first node in the circular linked list
    int size; // number of elements in the circular linked list

    /** 
     * Constructor
     * Creates a circular linked list that is empty
     */
    public CircularLinkedList() {
        first = new Node();
        size = 0;
    }

    /**
     * Method isEmpty
     * @return true if the circular linked list is empty
     *        false if the circular linked list is not empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method getSize
     * @return the number of elements in the circular linked list
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
     *       -1 if the data is not found
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

        Node current = first;
        for(int i = 0; i < index; i++)
            current = current.next;

        Object removedElement = current.next.data;
        current.next = current.next.next;
        size--;
        return removedElement;
    }
    
    /**
     * Method insert
     * @param index the index at which the data is to be inserted
     * @param data the data to be inserted
     * @throws IndexOutOfBoundsException when index is not between 0 and size
     */
    public void insert(Object data, int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node newNode = new Node(data);
        if(index == 0) {
            if(size == 0) {
                first = newNode;
                first.next = first;
            } else {
                newNode.next = first;
                Node last = first;
                for(int i = 0; i < size-1; i++) {
                    last = last.next;
                }
                last.next = newNode;
                first = newNode;
            }
        } else {
            Node current = first;
            for(int i = 0; i < index-1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    /**
     * Method display
     * Displays the elements in the circular linked list
     */
    public void display() {
        Node current = first;
        System.out.print("[");
        for(int i = 0; i < size-1; i++) {
            System.out.print(current.data + ", ");
            current = current.next;
        }
        System.out.print(current.data +"]");
    }

    /**
     * Method Main
     * The main method is used to test the CircularLinkedList class
     */
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        list.insert(1, 0);
        list.insert(2, 1);
        list.insert(3, 2);
        list.insert(4, 3);
        list.insert(5, 4);
        list.display(); // [1, 2, 3, 4, 5]
        System.out.println();
        list.remove(2); // remove 3
        list.display(); // [1, 2, 4, 5]
        System.out.println(); 
        list.insert(6, 2); // insert 6 at index 2
        list.display(); // [1, 2, 6, 4, 5]
        System.out.println(); 
        System.out.println(list.indexOf(6)); // 2
        System.out.println(list.get(2)); // 6
    }
}
