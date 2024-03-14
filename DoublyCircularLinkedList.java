/**
 * DoublyLinkedList
 * This class is used to create a doubly circular linked list and contains all the methods to perform operations on the doubly circular linked list.
 */
public class DoublyCircularLinkedList {
    /** Data Members */
    DoublyNode first; // first node in the doubly circular linked list
    int size; // number of elements in the doubly circular linked list

    /** Constructor */
    public DoublyCircularLinkedList() {
        first = new DoublyNode();
        size = 0;
    }

    /** 
     * Method isEmpty
     * @return true if the doubly circular linked list is empty
     *       false if the doubly circular linked list is not empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method getSize
     * @return the number of elements in the doubly circular linked list
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
     * @return the element at the specified index
     */
    public Object get(int index) {
        checkIndex(index);

        DoublyNode current = first;
        for(int i = 0; i <= index; i++)
            current = current.rlink;

        return current.data;
    }

    /**
     * Method indexOf
     * @param element the element to be searched
     * @return the index of the first occurrence of the element in the doubly circular linked list
     */
    public int indexOf(Object element) {
        DoublyNode current = first;
        for(int i = 0; i < size; i++) {
            if(current.data.equals(element))
                return i;
            current = current.rlink;
        }
        return -1;
    }

    /**
     * Method remove
     * Removes the element at the specified index
     * @param index the index of the element to be removed
     * @return the element that was removed
     * @throws IndexOutOfBoundsException when index is not between 0 and size - 1
     */
    public Object remove(int index) {
        checkIndex(index);

        DoublyNode current = first;
        for(int i = 0; i < index; i++)
            current = current.rlink;

        Object removedElement = current.rlink.data;
        current.rlink = current.rlink.rlink;
        current.rlink.llink = current;
        size--;
        return removedElement;
    }

    /**
     * Method insert
     * Inserts the specified element at the specified index
     * @param index the index at which the element is to be inserted
     * @param data the data to be inserted
     * @throws IndexOutOfBoundsException when index is not between 0 and size
     */
    public void insert(int index, Object data) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        DoublyNode newNode = new DoublyNode(data);
        newNode.llink = newNode;
        newNode.rlink = newNode;

        if(size == 0) {
            first = newNode;
        } else if(index == 0) {
            newNode.rlink = first;
            newNode.llink = first.llink;
            first.llink.rlink = newNode;
            first.llink = newNode;
            first = newNode;
        } else {
            DoublyNode current = first;
            for(int i = 0; i < index; i++)
                current = current.rlink;

            newNode.rlink = current.rlink;
            newNode.llink = current;
            current.rlink.llink = newNode;
            current.rlink = newNode;
        }
        size++;
    }

    /**
     * Method display
     * Displays the elements of the doubly circular linked list
     */
    public void display() {
        DoublyNode current = first;
        System.out.print("[");
        for(int i = 0; i < size-1; i++) {
            System.out.print(current.data + ", ");
            current = current.rlink;
        }
        System.out.print(current.data +"]");
    }

    /**
     * Method Main
     * The main method is used to test the DoublyCircularLinkedList class
     */
    public static void main(String[] args) {
        DoublyCircularLinkedList list = new DoublyCircularLinkedList();
        list.insert(0, 10);
        list.insert(1, 20);
        list.insert(2, 30);
        list.insert(3, 40);
        list.insert(4, 50);
        list.display();
        System.out.println();
        list.remove(2);
        list.display();
        System.out.println();
        list.insert(2, 35);
        list.display(); 
        System.out.println();
        System.out.println(list.indexOf(35));
        System.out.println(list.get(2));
    }
}
