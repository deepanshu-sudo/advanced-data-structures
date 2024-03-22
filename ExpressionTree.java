import java.util.Stack;

/**
 * An expression tree is a binary tree which is used to store arithmetic expressions.
 * The internal nodes of an expression tree are operators and leaves are operands.
 * This class represents an expression tree using a linked list.
 * The class provides methods to build an expression tree from a postfix expression and to evaluate the expression.
 */
public class ExpressionTree {
    /**
     * Data Members
     */
    DoublyNode root; // root of the expression tree

    /** Constructor */
    public ExpressionTree() {
        root = null;
    }

    /**
     * This method is used to check if a character is an operator
     * @param c the character to be checked
     * @return true if the character is an operator, false otherwise
     */
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    /**
     * This method is used to check the precedence of an operator
     * @param c the operator whose precedence is to be checked
     * @return the precedence of the operator
     */
    public static int precedence(char c) {
        if(c == '^') {
            return 3;
        } else if(c == '*' || c == '/') {
            return 2;
        } else if(c == '+' || c == '-') {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * create(String postfix)
     * This function is used to build the expression tree from a postfix expression.
     * @param postfix the postfix expression
     */
    public void create(String postfix) {
        LinkedStack s = new LinkedStack();
        for(char c : postfix.toCharArray()) {
            if(isOperator(c)) {
                DoublyNode t1 = (DoublyNode) s.pop();
                DoublyNode t2 = (DoublyNode) s.pop();
                DoublyNode newNode = new DoublyNode(c);
                newNode.llink = t2;
                newNode.rlink = t1;
                s.push(newNode);
            } else {
                DoublyNode newNode = new DoublyNode(c);
                s.push(newNode);
            }
        }
        root = (DoublyNode) s.pop();
    }

    /**
     * evaluate(DoublyNode root)
     * This function is used to evaluate the expression tree.
     * It is a recursive function that evaluates the expression tree in a postorder manner.
     * @param root the root of the expression tree
     * @return the result of the expression
     */
    public double evaluate(DoublyNode root) {
        if(root != null) {
            DoublyNode left = root.llink;
            DoublyNode right = root.rlink;
            Double leftOperand, rightOperand;

            if(!isOperator((char)left.data)) {
                leftOperand = Double.parseDouble(left.data.toString());
            } else {
                leftOperand = evaluate(left);
            }

            if(!isOperator((char)right.data)) {
                rightOperand = Double.parseDouble(right.data.toString());
            } else {
                rightOperand = evaluate(right);
            }

            switch((char)root.data) {
                case '+':
                    return leftOperand + rightOperand;
                case '-':
                    return leftOperand - rightOperand;
                case '*':
                    return leftOperand * rightOperand;
                case '/':
                    return leftOperand / rightOperand;
                case '^':
                    return Math.pow(leftOperand, rightOperand);
                default:
                    return 0;
            }
        }
        return 0;
    }

    /**
     * Pre Order Traversal (VLR)
     * This function is used to traverse the expression tree in a pre-order manner.
     * @param root the root of the expression tree
     */
    public void preOrder(DoublyNode root) {
        if(root != null) {
            System.out.print(root.data + "");
            preOrder(root.llink);
            preOrder(root.rlink);
        }
    }

    /**
     * In Order Traversal (LVR)
     * This function is used to traverse the expression tree in an in-order manner.
     * @param root the root of the expression tree
     */
    public void inOrder(DoublyNode root) {
        if(root != null) {
            inOrder(root.llink);
            System.out.print(root.data + "");
            inOrder(root.rlink);
        }
    }

    /**
     * Post Order Traversal (LRV)
     * This function is used to traverse the expression tree in a post-order manner.
     * @param root the root of the expression tree
     */
    public void postOrder(DoublyNode root) {
        if(root != null) {
            postOrder(root.llink);
            postOrder(root.rlink);
            System.out.print(root.data + "");
        }
    }

    /**
     * Level Order Traversal
     * This function is used to traverse the expression tree in a level-order manner.
     */
    public void levelOrder() {
        if(root == null) {
            return;
        }
        LinkedQueue<DoublyNode> q = new LinkedQueue<>();
        q.insert(root);
        while(!q.isEmpty()) {
            DoublyNode temp = (DoublyNode) q.remove();
            System.out.print(temp.data + "");
            if(temp.llink != null) {
                q.insert(temp.llink);
            }
            if(temp.rlink != null) {
                q.insert(temp.rlink);
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        ExpressionTree tree = new ExpressionTree();
        String postfix = "23*54*+9-";
        tree.create(postfix);
        System.out.println("Result: " + tree.evaluate(tree.root));
        System.out.print("Pre Order: ");
        tree.preOrder(tree.root);
        System.out.println();
        System.out.print("In Order: ");
        tree.inOrder(tree.root);
        System.out.println();
        System.out.print("Post Order: ");
        tree.postOrder(tree.root);
        System.out.println();
        System.out.print("Level Order: ");
        tree.levelOrder();
        System.out.println();
    }
}