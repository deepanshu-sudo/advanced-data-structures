/**
 * Define LinkedList class with LinkedList() Constructor and create(n) and display() member functions. Therefore write an efficient segregate(LinkedList &lst) (without creating extra nodes) to segregate positive and negative integers without changing the relative order of elements of same sign. In the output there should be negative numbers followed by positive numbers, and order among negative and positive numbers does not change. If ex if the input is [9,-3,5,-2,-8,-6,1,3] then output should be [-3,-2,-8,-6,9,5,1,3]. Then show these operations in main function. Assume all data members of LinkedList class are public and Consider all positities such that list have only positive,  only negative. 
 */
public class LinkedListPYQ {
     // Function to segregate the linked list
     static void segregate(LinkedList lst) {
        if (lst.first == null || lst.first.next == null) return;
        
        Node current = lst.first;
        Node lastNegative = null; // the last node in the negative partition

        // Dummy nodes to start negative and positive partitions
        Node negDummy = new Node(0);
        Node posDummy = new Node(0);
        Node negTail = negDummy;
        Node posTail = posDummy;

        while (current != null) {
            if (current.data < 0) {
                negTail.next = current;
                negTail = negTail.next;
            } else {
                posTail.next = current;
                posTail = posTail.next;
            }
            current = current.next;
        }
        
        // Connect negative and positive partitions
        negTail.next = posDummy.next;
        posTail.next = null; // Important to end the list

        // Reset the head of the list
        lst.first = negDummy.next;
    }

    // Main method to test the LinkedList functionality
    public static void main(String[] args) {
        LinkedList lst = new LinkedList();
        int[] values = {9, -3, 5, -2, -8, -6, 1, 3};
        lst.create(values);
        System.out.println("Original List:");
        lst.display();
        segregate(lst);
        System.out.println("Segregated List:");
        lst.display();
    }
}
