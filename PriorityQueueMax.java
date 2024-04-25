import java.util.Scanner;

/**
 * A priority queue is a data structure that stores elements in a way that they can be retrieved in a sorted order.
 * The elements are stored in the queue based on their priority.
 * The element with the highest priority is retrieved first.
 * The priority queue is implemented using a heap data structure.
 * The heap is a binary tree that satisfies the heap property.
 * The heap property states that the parent node is greater than or equal to its children in a max heap and less than or equal to its children in a min heap.
 * The priority queue can be implemented using a max heap or a min heap.
 * The max heap is used to implement a priority queue that retrieves the element with the highest priority first.
 * The min heap is used to implement a priority queue that retrieves the element with the lowest priority first.
 * The priority queue supports the following operations:
 * 1. Insert: This operation is used to insert an element into the priority queue.
 * 2. Delete: This operation is used to delete an element from the priority queue.
 * 3. IsEmpty: This operation is used to check if the priority queue is empty.
 */

public class PriorityQueueMax {
    /**
     * Data Members
     */
    MaxHeapTree heap; // max heap tree to implement the priority queue
    
    /**
     * Constructor
     * @param size: The size of the priority queue
     */
    public PriorityQueueMax(int size) {
        heap = new MaxHeapTree(size);
    }

    /**
     * insert(int x)
     * This function is used to insert an element into the priority queue.
     * @param x the element to be inserted
     */
    public void insert(int x) {
        heap.insert(x);
    }

    /**
     * delete()
     * This function is used to delete an element from the priority queue.
     * @return the element with the highest priority
     */
    public int delete() {
        return heap.delete();
    }

    /**
     * isEmpty()
     * This function is used to check if the priority queue is empty.
     * @return true if the priority queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * display()
     * This function is used to display the elements of the priority queue.
     */
    public void display() {
        heap.display();
    }

    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the priority queue: ");
        int size = sc.nextInt();

        PriorityQueueMax pq = new PriorityQueueMax(size);

        while(true) {
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. IsEmpty");
            System.out.println("4. View");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.print("Enter the element to be inserted: ");
                    int x = sc.nextInt();
                    pq.insert(x);
                    break;
                case 2:
                    int max = pq.delete();
                    if(max != -1)
                        System.out.println("Element deleted: " + max);
                    break;
                case 3:
                    if(pq.isEmpty())
                        System.out.println("Priority Queue is empty");
                    else
                        System.out.println("Priority Queue is not empty");
                    break;
                case 4:
                    pq.display();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
