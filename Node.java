/**
 * Node class for the linked list
 * This class is used to create a node for the linked list
 */
public class Node {
    /** data members */
    Object data; // data of the node
    Node next; // reference to the next node

    /**
     * Default Constructor
     */
    public Node() {
        this.data = null;
        this.next = null;
    }

    /** 
     * This constructor creates a node with the given data 
     * and in the next field, it stores null
     * @param data data of the node
     */
    public Node(Object data) {
        this.data = data;
        this.next = null;
    }

    /**
     * This constructor creates a node with the given data and next node
     * @param data data of the node
     * @param next reference to the next node
     */
    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }

    /**
     * This method returns a new node
     */
    public static Node createNewNode() {
        return new Node();
    }
}
