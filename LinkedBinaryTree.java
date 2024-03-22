import java.util.Scanner;

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
 * This class implements a binary tree using an linked list.
 */
public class LinkedBinaryTree<T> {
    /** 
     * Data Members
     */
    DoublyNode root; // root node of the tree

    /** Constructor */
    public LinkedBinaryTree() {
        root = null;
    }

    /**
     * build(r,p)
     * This function is used to build the tree.
     * It is a recursive function that builds the tree in a top-down manner.
     * They are called with the index of the root node of the tree.
     * @param r the root node of the tree
     * @param p the parent node of the root node
     */
    public void build(DoublyNode r, DoublyNode p) {
        Scanner sc = new Scanner(System.in);
        if(r == null) {
            r = new DoublyNode();
            root = r;
            System.out.println("Enter Root Node: ");
        } else if(r == p.llink) {
            System.out.println("Enter Left Child of " + p.data + ": ");
        } else {
            System.out.println("Enter Right Child of " + p.data + ": ");
        }
        r.data = sc.next();

        System.out.println("Does " + r.data + " has left child? (y/n)");
        if(sc.next().toLowerCase().charAt(0) == 'y') {
            r.llink = new DoublyNode();
            build(r.llink, r);
        }

        System.out.println("Does " + r.data + " has right child? (y/n)");
        if(sc.next().toLowerCase().charAt(0) == 'y') {
            r.rlink = new DoublyNode();
            build(r.rlink, r);
        }
    }

    /** @return true iff tree is empty */
    public boolean isEmpty() { return root == null; }

    /** @return size the number of nodes in the binary tree */
    public int size() {
        if(root == null)
            return 0;

        int size = 0;
        LinkedQueue<DoublyNode> queue = new LinkedQueue<>();
        queue.insert(root);

        while(!queue.isEmpty()) {
            DoublyNode node = queue.remove();
            size++;
            if(node.llink != null)
                queue.insert(node.llink);
            
            if(node.rlink != null)
                queue.insert(node.rlink);
        }

        return size;
    }

    /**
     * Recursive Implementation of Size() Function
     * @return size the number of nodes in the binary tree
     */
    public int size(DoublyNode root) {
        if(root == null) {
            return 0;
        } else {
            return size(root.llink) + size(root.rlink) + 1;
        }
    }

    /**
     * Pre Order Traversal (VLR)
     * This function is used to traverse the tree in pre-order.
     * In pre-order traversal, the root node is visited first, then the left child, and then the right child.
     * @param root the root node of the tree
     */
    public void preOrder(DoublyNode root) {
        if(root != null) {
            System.out.print(root.data + " ");
            preOrder(root.llink);
            preOrder(root.rlink);
        }
    }

    /**
     * In Order Traversal (LVR)
     * This function is used to traverse the tree in in-order.
     * In in-order traversal, the left child is visited first, then the root node, and then the right child.
     * @param root the root node of the tree
     */
    public void inOrder(DoublyNode root) {
        if(root != null) {
            inOrder(root.llink);
            System.out.print(root.data + " ");
            inOrder(root.rlink);
        }
    }

    /**
     * Post Order Traversal (LRV)
     * This function is used to traverse the tree in post-order.
     * In post-order traversal, the left child is visited first, then the right child, and then the root node.
     * @param root the root node of the tree
     */
    public void postOrder(DoublyNode root) {
        if(root != null) {
            postOrder(root.llink);
            postOrder(root.rlink);
            System.out.print(root.data + " ");
        }
    }

    /**
     * Level Order Traversal
     * This function is used to traverse the tree in level-order.
     * In level-order traversal, the nodes are visited level by level from left to right.
     */
    public void levelOrder() {
        if(root == null)
            return;

        LinkedQueue<DoublyNode> queue = new LinkedQueue<>();
        queue.insert(root);

        while(!queue.isEmpty()) {
            DoublyNode node = queue.remove();
            System.out.print(node.data + " ");
            if(node.llink != null)
                queue.insert(node.llink);
            
            if(node.rlink != null)
                queue.insert(node.rlink);
        }
    }

    /** @return height of the tree */
    public int height(DoublyNode root) {
        if(root == null)
            return 0;
        else if(height(root.llink) > height(root.rlink))
            return 1 + height(root.llink);
        else
            return 1 + height(root.rlink);
    }

    /** @return root of the tree */
    public DoublyNode getRoot() { return root; }

    /** @return address of the element that's being searched */
    public DoublyNode search(DoublyNode root, T key) {
        if(root == null)
            return null;
        else if(root.data.equals(key))
            return root;
        else {
            DoublyNode temp = search(root.llink, key);
            if(temp == null)
                temp = search(root.rlink, key);
            return temp;
        }
    }

    // Main Method
    public static void main(String[] args) {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
        System.out.println("Do you want to build the tree? (y/n)");
        Scanner sc = new Scanner(System.in);
        if(sc.next().toLowerCase().charAt(0) == 'y') {
            tree.build(tree.root, null);
            System.out.println("Tree Built Successfully!");
        }
        
        System.out.println("Size of the Tree: " + tree.size());
        System.out.println("Size of the Tree: " + tree.size(tree.root));
        System.out.print("Pre Order Traversal: ");
        tree.preOrder(tree.root);
        System.out.println();
        System.out.print("In Order Traversal: ");
        tree.inOrder(tree.root);
        System.out.println();
        System.out.print("Post Order Traversal: ");
        tree.postOrder(tree.root);
        System.out.println();
        System.out.print("Level Order Traversal: ");
        tree.levelOrder();
        System.out.println();
        System.out.println("Height of the Tree: " + tree.height(tree.root));
        System.out.println("Enter the element to be searched: ");
        DoublyNode node = tree.search(tree.root, sc.next());
        if(node != null)
            System.out.println("Element Found at: " + node);
        else
            System.out.println("Element Not Found!");
    }
}
