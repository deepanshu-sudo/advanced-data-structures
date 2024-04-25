/**
 * A binary search tree is a data structure that is used to store data in a way
 * that makes it easy to search for, insert, and delete data.
 * In a BST, each node has at most two children, referred to as the left child
 * and the right child.
 * The BST property states that for any node in the tree, the data in the left
 * child is less than the data in the node, and the data in the right child is
 * greater than the data in the node.
 * The BST supports the following operations:
 * 1. Insert: This operation is used to insert data into the BST.
 * 2. Delete: This operation is used to delete data from the BST.
 * 3. Search: This operation is used to search for data in the BST.
 * 4. Inorder Traversal: This operation is used to traverse the BST in an
 * inorder manner.
 * 5. Preorder Traversal: This operation is used to traverse the BST in a
 * preorder manner.
 * 6. Postorder Traversal: This operation is used to traverse the BST in a
 * postorder manner.
 * 
 * Note: The inorder traversal of a BST gives the data in sorted order.
 */
public class BinarySearchTree {
    /**
     * The Node class represents a node in the BST.
     * Each node has a data field, a left child, and a right child.
     * The data field stores the data in the node.
     * The left child field stores a reference to the left child of the node.
     * The right child field stores a reference to the right child of the node.
     * The Node class has a constructor that initializes the data field and sets the
     * left and right child fields to null.
     */
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    /** Data Members */
    Node root;

    /**
     * The insert method is used to insert data into the BST.
     * The method takes an integer data as a parameter and inserts it into the BST.
     * The method starts at the root of the BST and traverses the tree to find the correct position to insert the data.
     * If the data is less than the data in the current node, the method moves to the left child of the node.
     * If the data is greater than the data in the current node, the method moves to the right child of the node.
     * The method continues this process until it reaches a leaf node, where it inserts the data.
     * If the data is already present in the BST, the method throws an IllegalArgumentException.
     */
    public void insert(int data) {
        Node r = root;
        Node p = null;

        while(r != null) {
            if(data == r.data) 
                throw new IllegalArgumentException("Duplicate key");
            else if(data < r.data) {
                p = r;
                r = r.left;
            } else {
                p = r;
                r = r.right;
            }
        }

        Node newNode = new Node(data);
        if(p == null)
            root = newNode;
        else if(data < p.data)
            p.left = newNode;
        else
            p.right = newNode;
    }

    /**
     * Delete:
     * The delete method is used to delete data from the BST.
     * The method takes an integer data as a parameter and deletes it from the BST.
     * To delete a node with key = x from BST, the BST is traversed to find the node with key = x.
     * If x is not found in the BST, the method throws an Exception.
     * If x is found in the BST, the following cases are considered:
     * 
     * Case 1: If the node to be deleted has no children, the node is simply removed from the BST.
     * 
     * Case 2: If the node to be deleted has one child, the child is linked to the parent of the node to be deleted.
     * 
     * Case 3: If the node to be deleted has two children, the node is replaced by the inorder successor of the node. Then the inorder successor is deleted using case 1 or case 2.
     */
    public void delete(int x) {
        Node r = root;
        Node p = null;

        while(r != null && r.data != x) {
            p = r;
            if(r.data > x)
                r = r.left;
            else
                r = r.right;
        }

        if(r == null)
            throw new IllegalArgumentException("Data not found");

        // Case 1: No children
        if(r.left == null && r.right == null) {
            if(r == root)
                root = null;
            else if(p.left == r)
                p.left = null;
            else
                p.right = null;
        }

        // Case 2: Single Child
        else if(r.left == null || r.right == null) {
            Node child = r.left != null ? r.left : r.right;
            if(r == root)
                root = child;
            else if(p.left == r)
                p.left = child;
            else
                p.right = child;
        }

        // Case 3: Two Children
        else {
            Node suc = r.right;
            p = r;
            while(suc.left != null) {
                p = suc;
                suc = suc.left;
            }

            r.data = suc.data;
            if(p.left == suc)
                p.left = suc.right;
            else
                p.right = suc.right;
        }
    }

    /**
     * Search:
     * The search method is used to search for data in the BST.
     * The method takes an integer data as a parameter and searches for it in the BST.
     * The method starts at the root of the BST and traverses the tree to find the data.
     * If the data is less than the data in the current node, the method moves to the left child of the node.
     * If the data is greater than the data in the current node, the method moves to the right child of the node.
     * The method continues this process until it finds the data or reaches a leaf node.
     * If the data is found, the method prints a message indicating that the data is found.
     * If the data is not found, the method prints a message indicating that the data is not found.
     */
    public void search(int data) {
        Node r = root;
        while(r != null) {
            if(r.data == data) {
                System.out.println("Data found: " + r.data);
                return;
            } else if(r.data < data) {
                r = r.right;
            } else {
                r = r.left;
            }
        }

        System.out.println("Data not found");
    }

    /**
     * InOrder Traversal - Left -> Root -> Right
     */
    public void inOrder(Node root) {
        if(root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    /**
     * PreOrder Traversal - Root -> Left -> Right
     */
    public void preOrder(Node root) {
        if(root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * PostOrder Traversal - Left -> Right -> Root
     */
    public void postOrder(Node root) {
        if(root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    /** Main Method */
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Inorder Traversal:");
        bst.inOrder(bst.root);
        System.out.println();

        System.out.println("Preorder Traversal:");
        bst.preOrder(bst.root);
        System.out.println();

        System.out.println("Postorder Traversal:");
        bst.postOrder(bst.root);
        System.out.println();

        bst.search(40);
        bst.search(100);

        bst.delete(20);
        bst.delete(80);
        bst.delete(50);

        System.out.println("Inorder Traversal:");
        bst.inOrder(bst.root);
        System.out.println();
    }
}