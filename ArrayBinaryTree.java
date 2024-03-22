/**
 * Tree is a data structure that is used to store data in a hierarchical manner.
 * It is a non-linear data structure.
 * It is a collection of nodes connected by edges.
 * The topmost node is called the root of the tree.
 * The nodes that are connected to the root node are called the children of the root node.
 * The nodes that are connected to the children nodes are called the children of the children nodes, and so on.
 * The nodes that do not have any children are called leaf nodes.
 * The nodes that are connected to the same parent node are called siblings.
 * The height of a tree is the length of the longest path from the root node to a leaf node.
 * The depth of a node is the length of the path from the root node to that node.
 * The degree of a node is the number of children of that node. The degree of leaf nodes is 0 and the degree of the root node is the maximum.
 * The level of a node is the depth of the node + 1.
 * This class implements a binary tree using an array.
 */
import java.util.Scanner;
import java.util.Arrays;

public class ArrayBinaryTree<T> {
    /**
     * Data Members
     */
    T[] tree; // array to store the elements of the tree
    int h; // height of the tree 

    /** Constructor */
    @SuppressWarnings("unchecked")
    public ArrayBinaryTree(int height) {
        if(height < 0)
            throw new IllegalArgumentException("Height of the tree should be greater than or equal to 0");

        h = height;
        tree = (T[]) new Object[(int) Math.pow(2,h)-1];

        for(int i = 0; i < tree.length; i++)
            tree[i] = null;
    }

    /**
     * build(int i)
     * This function is used to build the tree.
     * It is a recursive function that builds the tree in a top-down manner.
     * They are called with the index of the root node of the tree.
     * @param i the index of the root node of the tree
     */
    @SuppressWarnings("unchecked")
    public void build(int i) {
        Scanner sc = new Scanner(System.in);
        if(i == 0) {
            System.out.println("Enter the root node: ");
        } else if (i % 2 == 1) {
            System.out.println("Enter the left child of "+tree[(i-1)/2]+": ");
        } else {
            System.out.println("Enter the right child of "+tree[(int) Math.floor((i-1)/2)]+": ");
        }
        tree[i] = (T) sc.next();

        System.out.println("Does "+tree[i]+" has a left child? (y/n)");
        if((sc.next().toLowerCase().charAt(0) == 'y') && (2*(i+1)-1 < tree.length)) {
            build(2*(i+1)-1);
        }

        System.out.println("Does "+tree[i]+" has a right child? (y/n)");
        if((sc.next().toLowerCase().charAt(0) == 'y') && (2*(i+1) < tree.length)) {
            build(2*(i+1));
        }
    }

    /** @return true iff the tree is empty */
    public boolean isEmpty() { return tree[0] == null; }

    /** @return size the number of nodes in the binary tree */
    public int size() {
        int size = 0;
        for(int i = 0; i < tree.length; i++) {
            if(tree[i] != null)
                size++;
        }
        return size;
    }

    /**
     * Pre Order Traversal (VLR)
     * This function is used to traverse the tree in pre-order.
     * In pre-order traversal, the root node is visited first, then the left child, and then the right child.
     * @param i the index of the root node of the tree
     */
    public void preOrder(int i) {
        if(i < tree.length && tree[i] != null) {
            System.out.print(tree[i]+" ");
            preOrder(2*(i+1)-1);
            preOrder(2*(i+1));
        }
    }

    /**
     * In Order Traversal (LVR)
     * This function is used to traverse the tree in in-order.
     * In in-order traversal, the left child is visited first, then the root node, and then the right child.
     * @param i the index of the root node of the tree
     */
    public void inOrder(int i) {
        if(i < tree.length && tree[i] != null) {
            inOrder(2*(i+1)-1);
            System.out.print(tree[i]+" ");
            inOrder(2*(i+1));
        }
    }

    /**
     * Post Order Traversal (LRV)
     * This function is used to traverse the tree in post-order.
     * In post-order traversal, the left child is visited first, then the right child, and then the root node.
     * @param i the index of the root node of the tree
     */
    public void postOrder(int i) {
        if(i < tree.length && tree[i] != null) {
            postOrder(2*(i+1)-1);
            postOrder(2*(i+1));
            System.out.print(tree[i]+" ");
        }
    }

    /**
     * Level Order Traversal
     * This function is used to traverse the tree in level-order.
     * In level-order traversal, the nodes are visited level by level from left to right.
     */
    public void levelOrder() {
        if(isEmpty()) {
            System.out.println("Tree is empty");
            return;
        }
        ArrayQueue<Integer> queue = new ArrayQueue<>(size());
        queue.insert(0);
        while(!queue.isEmpty()) {
            int i = queue.remove();
            System.out.print(tree[i]+" ");
            if(2*(i+1)-1 < tree.length && tree[2*(i+1)-1] != null)
                queue.insert(2*(i+1)-1);
            if(2*(i+1) < tree.length && tree[2*(i+1)] != null)
                queue.insert(2*(i+1));
        }
    }

    /**
     * Level Order Traversal Easy
     * This function is used to traverse the tree in level-order.
     * In level-order traversal, the nodes are visited level by level from left to right.
     * Not preferred because complexity is O(n) and it is not efficient.
     */
    public void levelOrderEasy() {
        for(int i = 0; i < tree.length; i++) {
            if(tree[i] != null)
                System.out.print(tree[i]+" ");
        }
    }

    /** @return height of the tree */
    public int height() { return h; }

    /** @return index of the element that's being searched */
    public int search(T element) {
        for(int i = 0; i < tree.length; i++) {
            if(tree[i] == element)
                return i;
        }
        return -1;
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the height of the tree: ");
        int h = sc.nextInt();
        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>(h);
        tree.build(0);
        System.out.println(Arrays.toString(tree.tree));
        System.out.println("Pre Order Traversal: ");
        tree.preOrder(0);
        System.out.println();
        System.out.println("In Order Traversal: ");
        tree.inOrder(0);
        System.out.println();
        System.out.println("Post Order Traversal: ");
        tree.postOrder(0);
        System.out.println();
        System.out.println("Level Order Traversal: ");
        tree.levelOrder();
        System.out.println();
    }
}
