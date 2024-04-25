import java.util.Scanner;

public class MinHeapTree {
    /**
     * Data Members
     */
    int[] heap; // min heap tree to implement the priority queue
    int size; // size of the priority queue

    /**
     * Constructor
     * @param size: The size of the priority queue
     */
    public MinHeapTree(int size) {
        Scanner sc = new Scanner(System.in);
        heap = new int[size];
        this.size = size;

        System.out.println("Enter elements: ");
        for(int i = 0; i < size; i++) {
            System.out.print("Element " + (i+1) + ": ");
            heap[i] = sc.nextInt();
        }

        // build min heap
        HeapTree.heapifyMin(heap, size);
    }

    /**
     * insert(int x)
     * This function is used to insert an element into the priority queue.
     * 
     * Algorithm:
     * 1. Insert the element at the end of the heap.
     * 2. Adjust the heap.
     * 
     * @param x the element to be inserted
     */
    public void insert(int x) {
        if(size == heap.length) {
            System.out.println("Priority Queue is full");
            return;
        }

        HeapTree.insertMin(heap, ++size, x);
    }

    /**
     * delete()
     * This function is used to delete an element from the priority queue.
     * @return the element with the highest priority
     */
    public int delete() {
        if(isEmpty()) {
            System.out.println("Priority Queue is empty");
            return -1;  
        }

        int min = HeapTree.deleteMin(heap, size);
        size--;
        return min;
    }

    /**
     * isEmpty()
     * This function is used to check if the priority queue is empty.
     * @return true if the priority queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * display()
     * This function is used to display the elements of the priority queue.
     */
    public void display() {
        if(isEmpty()) {
            System.out.println("Priority Queue is empty");
            return;
        }

        System.out.print("Priority Queue: ");
        for(int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
