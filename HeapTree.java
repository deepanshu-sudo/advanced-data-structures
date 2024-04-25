import java.util.Arrays;

/**
 * Heap is a Complete Binary Tree.
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.
 * A heap tree may be of two types 
 * 1. Max Heap
 * 2. Min Heap
 * 
 * In Max Heap, the value of a node should be greater than or equal to the value of its children.
 * In Min Heap, the value of a node should be less than or equal to the value of its children.
 * 
 * Heap may be represented in the form of an array.
 * 
 * The major operations that can be performed on a heap is:
 * 1. Insertion
 * 2. Deletion
 * 3. Build Heap
 */
public class HeapTree {
    /**
     * Insertion in a Max Heap Tree:
     * 
     * Insertion is the process of adding a new element to the heap.
     * 
     * Algorithm:
     * 1. Insert the new element at the end of the heap.
     * 2. Compare the new element with its parent.
     * 3. If the new element is greater than its parent, swap the new element with its parent.
     * 4. Repeat steps 2 and 3 until the new element is less than or equal to its parent.
     * 
     * @param heap: The heap in which the element is to be inserted.
     * @param n: The number of elements in the heap.
     * @param x: The element to be inserted.
     */
    public static void insertMax(int[] heap,int n, int x) {
        int i = n-1;
        while((i > 0) && (x > heap[(i-1)/2])) {
            heap[i] = heap[(i-1)/2];
            i = (i-1)/2;
        }
        heap[i] = x;
    }

    /**
     * Insertion in a Min Heap Tree:
     * 
     * Insertion is the process of adding a new element to the heap.
     * 
     * Algorithm:
     * 1. Insert the new element at the end of the heap.
     * 2. Compare the new element with its parent.
     * 3. If the new element is less than its parent, swap the new element with its parent.
     * 4. Repeat steps 2 and 3 until the new element is greater than or equal to its parent.
     * 
     * @param heap: The heap in which the element is to be inserted.
     * @param n: The number of elements in the heap.
     * @param x: The element to be inserted.
     */
    public static void insertMin(int[] heap,int n, int x) {
        int i = n-1;
        while((i > 0) && (x < heap[(i-1)/2])) {
            heap[i] = heap[(i-1)/2];
            i = (i-1)/2;
        }
        heap[i] = x;
    }

    /**
     * Adjust Function for Max Heap:
     * This function is used to adjust the heap after deletion of an element.
     * 
     * Algorithm:
     * 1. Compare the element with its children.
     * 2. If the element is less than the children, swap the element with the greater child.
     * 3. Repeat steps 1 and 2 until the element is greater than or equal to its children.
     * 
     * @param heap: The heap to be adjusted.
     * @param n: The number of elements in the heap.
     * @param i: The index of the element to be adjusted.
     */
    public static void adjustMax(int[] heap, int n, int i) {
        // This adjusts the max heap stored in a[i..n-1]
        int x = heap[i];
        int j = 2*(i+1)-1;
        while(j <= n-1) {
            if((j < n-1) && (heap[j] < heap[j+1]))
                j++;
            if(x >= heap[j])
                break;
            heap[(j-1)/2] = heap[j];
            j = 2*(j+1)-1;
        }
        heap[(j-1)/2] = x;
    }

    /**
     * Adjust Function for Min Heap:
     * This function is used to adjust the heap after deletion of an element.
     * 
     * Algorithm:
     * 1. Compare the element with its children.
     * 2. If the element is greater than the children, swap the element with the smaller child.
     * 3. Repeat steps 1 and 2 until the element is less than or equal to its children.
     * 
     * @param heap: The heap to be adjusted.
     * @param n: The number of elements in the heap.
     * @param i: The index of the element to be adjusted.
     */
    public static void adjustMin(int[] heap, int n, int i) {
        // This adjusts the min heap stored in a[i..n-1]
        int x = heap[i];
        int j = 2*(i+1)-1;
        while(j <= n-1) {
            if((j < n-1) && (heap[j] > heap[j+1]))
                j++;
            if(x <= heap[j])
                break;
            heap[(j-1)/2] = heap[j];
            j = 2*(j+1)-1;
        }
        heap[(j-1)/2] = x;
    }

    /**
     * Deletion in a Max Heap Tree:
     * This function is used to delete an element from the heap.
     * 
     * Algorithm:
     * 1. Replace the root element with the last element of the heap.
     * 2. Adjust the heap.
     * 
     * @param heap: The heap from which the element is to be deleted.
     * @param n: The number of elements in the heap.
     * @return The element deleted from the heap.
     */
    public static int deleteMax(int[] heap, int n) {
        int x = heap[0];
        heap[0] = heap[n-1];
        adjustMax(heap, n-1, 0);
        return x;
    }

    /**
     * Deletion in a Min Heap Tree:
     * This function is used to delete an element from the heap.
     * 
     * Algorithm:
     * 1. Replace the root element with the last element of the heap.
     * 2. Adjust the heap.
     * 
     * @param heap: The heap from which the element is to be deleted.
     * @param n: The number of elements in the heap.
     * @return The element deleted from the heap.
     */
    public static int deleteMin(int[] heap, int n) {
        int x = heap[0];
        heap[0] = heap[n-1];
        adjustMin(heap, n-1, 0);
        return x;
    }

    /**
     * Build Max Heap - HeapifyMax:
     * This function is used to build a max heap from an array of elements.
     * 
     * Algorithm:
     * 1. Start from the last non-leaf node.
     * 2. Adjust the heap.
     * 3. Repeat step 2 until the root node.
     * 
     * @param heap: The array of elements from which the max heap is to be built.
     * @param n: The number of elements in the array.
     */
    public static void heapify(int[] heap, int n) {
        for(int i = (n-2)/2; i >= 0; i--)
            adjustMax(heap, n, i);
    }

    /**
     * Build Min Heap - HeapifyMin:
     * This function is used to build a min heap from an array of elements.
     * 
     * Algorithm:
     * 1. Start from the last non-leaf node.
     * 2. Adjust the heap.
     * 
     * 
     * @param heap: The array of elements from which the min heap is to be built.
     * @param n: The number of elements in the array.
     */
    public static void heapifyMin(int[] heap, int n) {
        for(int i = (n-2)/2; i >= 0; i--)
            adjustMin(heap, n, i);
    }

    /**
     * Heap Sort
     * This function is used to sort an array of elements using heap sort.
     * 
     * Algorithm:
     * 1. Build a max heap from the array.
     * 2. Delete the root element and adjust the heap.
     * 3. Repeat step 2 until the heap is empty.
     * 
     * Time Complexity:
     * The time complexity of heap sort is O(nlogn).
     * 
     * @param heap: The array of elements to be sorted.
     * @param n: The number of elements in the array.
     */
    public static void heapSort(int[] heap, int n) {
        heapify(heap, n);
        for(int i = n-1; i >= 1; i--) {
            int x = heap[0];
            heap[0] = heap[i];
            heap[i] = x;
            adjustMax(heap, i, 0);
        }
    }

    // Main
    public static void main(String[] args) {
        // create an array with following values: 40 35 45 60 90 70 80
        int[] heap = {40, 35, 45, 60, 90, 70, 80};

        // print the array
        System.out.println(Arrays.toString(heap));

        // call the heapify function
        heapify(heap, heap.length);

        System.out.println(Arrays.toString(heap));

        // perform heap sort
        heapSort(heap, heap.length);

        System.out.println("After Sorting");
        System.out.println(Arrays.toString(heap));
    }
}
