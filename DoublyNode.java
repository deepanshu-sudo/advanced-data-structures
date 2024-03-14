/**
 * Node class for the doubly linked list
 * This class is used to create a node for the doubly linked list
 */
public class DoublyNode {
    /** Data Members */
    Object data; // data stored in the node
    DoublyNode llink; // reference to the previous node
    DoublyNode rlink; // reference to the next node

    /**
     * Constructor
     * Creates a node that is empty
     */
    public DoublyNode() {
        data = null;
        llink = null;
        rlink = null;
    }

    /**
     * Constructor
     * @param data the data to be stored in the node
     */
    public DoublyNode(Object data) {
        this.data = data;
        llink = null;
        rlink = null;
    }
    
    /**
     * Constructor
     * @param data the data to be stored in the node
     * @param llink the reference to the previous node
     * @param rlink the reference to the next node
     */
    public DoublyNode(Object data, DoublyNode llink, DoublyNode rlink) {
        this.data = data;
        this.llink = llink;
        this.rlink = rlink;
    }
}
