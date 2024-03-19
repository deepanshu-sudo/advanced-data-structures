import java.math.BigInteger;
import java.util.Arrays;

/**
 * This class is used to do conversion of expressions from
 * infix to postfix and postfix to infix
 * There are also methods for evaluation of the same
 * The class makes use of Stack for the same
 */
public class StackApplications {

    /**
     * Create a Point class to represent a point in the maze
     */
    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Point p) {
            return this.x == p.x && this.y == p.y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
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
     * This method is used to convert an infix expression to a postfix expression
     * @param exp the infix expression to be converted
     * @return the postfix expression
     */
    public static String infixToPostfix(String exp) {
        String result = "";
        LinkedStack stack = new LinkedStack();
        stack.push('(');
        exp += ')';

        for(int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if(c == '(') {
                stack.push(c);
            } else if(c == ')') {
                char y = (char) stack.pop();
                while(y != '(') {
                    result += y;
                    y = (char) stack.pop();
                }
            } else if(isOperator(c)) {
                char y  = (char) stack.pop();
                while(isOperator(y) && precedence(y) >= precedence(c)) {
                    result += y;
                    y = (char) stack.pop();
                }
                stack.push(y);
                stack.push(c);
            } else if(Character.isLetterOrDigit(c)) {
                result += c;
            } else {
                throw new IllegalArgumentException("Invalid character in expression");
            }
        }

        return result;
    }

    /**
     * this method is used to evaluate a postfix expression
     * @param exp the postfix expression to be evaluated
     * @return the result of the evaluation
     */
    public static int evaluatePostfix(String exp) {
        LinkedStack stack = new LinkedStack();
        for(int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if(Character.isDigit(c)) {
                stack.push(c - '0'); // convert char to int
            } else {
                int val1 = (int) stack.pop();
                int val2 = (int) stack.pop();
                switch(c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                    case '^':
                        stack.push((int) Math.pow(val2, val1));
                        break;
                }
            }
        }
        return (int) stack.pop();
    }

    /** 
     * this method is used to match the opening and closing brackets in an expression
     * this algorithm works by using a stack to keep track of the opening brackets
     * whenever a closing bracket is encountered, it is checked if there is a matching opening bracket
     * if there is, the opening bracket is popped from the stack
     * if there isn't, the expression is not balanced
     * @param exp the expression to be checked
     * @return true if the brackets are balanced, false otherwise
     */
    public static boolean validParentheses(String exp) {
        LinkedStack stack = new LinkedStack();
        for(int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if(c == '(') {
                stack.push(c);
            } else if(c == ')') {
                if(stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    /**
     * Rat in a Maze
     * Given a maze, NxN matrix. A rat has to find a path from source to destination.
     * A maze is a rectangular area made of mxn squares with an entry and an exit point.
     * Some squares of maze has obstacles (blue).
     * Entrance at (1, 1) and exit at (m, n).
     * It is represented by a 2D array of size mxn with obstacle cells with 2 and free cell with 0.
     * The rat can move in all 4 directions: up, down, left, right.
     * The rat cannot move to any of the obstacle cells.
     * The rat can move to a cell only if it is free.
     * The rat cannot move outside the maze.
     * @param maze the maze to be solved
     */
    public static void ratInAMaze(int[][] maze) {
        LinkedStack path = new LinkedStack();
        int row = 0;
        int col = 0;
        maze[row][col] = 1;
        int m = maze.length-1;
        int n = maze[0].length-1;

        while(row != m || col != n) {
            if(col < n && maze[row][col+1] == 0 ) { // move right
                path.push(new Point(row, col));
                col++;
                maze[row][col] = 1;
            } else if(row < m && maze[row+1][col] == 0) { // move down
                path.push(new Point(row, col));
                row++;
                maze[row][col] = 1;
            } else if(col > 1 && maze[row][col-1] == 0) { // move left
                path.push(new Point(row, col));
                col--;
                maze[row][col] = 1;
            } else if(row > 1 && maze[row-1][col] == 0) { // move up
                path.push(new Point(row, col));
                row--;
                maze[row][col] = 1;
            } else {
                maze[row][col] = 2;
                if(path.isEmpty()) {
                    System.out.println("No path exists");
                    return;
                }
                Point p = (Point) path.pop();
                row = p.x;
                col = p.y;
            }
        }
        path.display();
        System.out.println("Maze Path: ");
        for(int[] r : maze) {
            System.out.println(Arrays.toString(r));
        }
    }

    /**
     * Factorial Using Stack:
     * This method is used to calcluate factorial of a number using Stack
     * Algorithm:
     * 1. Create a stack
     * 2. Push all numbers from 1 to n into the stack
     * 3. Pop the numbers from the stack and multiply them to get the factorial
     * 4. Return the factorial
     * @param n the number whose factorial is to be calculated
     * @return the factorial of the number
     */
    public static BigInteger factorialStack(int n) {
        // Create a stack
        LinkedStack stack = new LinkedStack();

        // Push all numbers from 1 to n into the stack
        while(n > 1) {
            stack.push(n);
            n--;
        }
        BigInteger fact = BigInteger.ONE;

        // Pop the numbers from the stack and multiply them to get the factorial
        while(!stack.isEmpty()) {
            fact = fact.multiply(BigInteger.valueOf((int) stack.pop()));
        }
        return fact;
    }

    /**
     * GCD Using Stack
     * This method is used to calculate the Greatest Common Divisor of two numbers using Stack
     * Algorithm:
     * 1. Create two stacks
     * 2. Push the numbers into the stacks
     * 3. Keep popping the numbers from the stacks and calculating the GCD
     * 4. Return the GCD
     * @param a the first number
     * @param b the second number
     * @return the GCD of the two numbers
     */
    public static int gcdStack(int a, int b) {
        // Create two stacks
        LinkedStack s1 = new LinkedStack();
        LinkedStack s2 = new LinkedStack();

        // Push the numbers into the stacks
        s1.push(a);
        s2.push(b);
        
        int m = 0; 
        int n = 0;

        // Keep popping the numbers from the stacks and calculating the GCD
        while(!s1.isEmpty() && !s2.isEmpty()) {
            m = (int) s1.pop();
            n = (int) s2.pop();
            if(m % n != 0) {
                s1.push(n);
                s2.push(m % n);
            }
        }

        return n;
    }

    /**
     * Fibonacci using Stack
     * This method is used to calculate the nth Fibonacci number using Stack
     * Algorithm:
     * 1. Create a stack
     * 2. Push the number n into the stack
     * 3. Pop the number from the stack and push n-1 and n-2 into the stack
     * 4. Repeat the process until the number is 1 or 2
     * 5. Increment the Fibonacci number
     * 6. Return the Fibonacci number
     * @param n the number whose Fibonacci number is to be calculated
     * @return the nth Fibonacci number
     */
    public static int fibonacciStack(int n) {
        // Create a stack
        LinkedStack stack = new LinkedStack();

        stack.push(n);
        int fib = 0;

        while(stack.isEmpty() == false) {
            int x = (int) stack.pop();
            if(x > 2) {
                stack.push(x-1);
                stack.push(x-2);
            } else if(x == 1 || x == 2) {
                fib += 1;
            }
        }

        return fib;
    }

    /**
     * Fibonacci Using Stack - Version 2 [Easy]
     * This method is used to calculate the nth Fibonacci number using Stack
     * Algorithm:
     * 1. Create a stack
     * 2. Push 0 and 1 into the stack
     * 3. Pop the numbers from the stack and push a and a+b into the stack
     * 4. Repeat the process until the number is n
     * 5. Return the nth Fibonacci number
     * @param n the number whose Fibonacci number is to be calculated
     * @return the nth Fibonacci number
     */
    public static int fibonacciStackEasy(int n) {
        LinkedStack stack = new LinkedStack();

        stack.push(0);
        stack.push(1);

        for(int i = 2; i <= n; i++) {
            int a = (int) stack.pop();
            int b = (int) stack.pop();
            stack.push(a);
            stack.push(a + b);
        }

        return (int) stack.pop();
    }

    /**
     * Decimal To Binary Usint Stack
     * This method is used to convert a decimal number to binary using Stack
     * No. of digits in binary representation of a number = log2(n) + 1
     * Algorithm:
     * 1. Create a stack
     * 2. Push the remainder of the number when divided by 2 into the stack
     * 3. Repeat the process until the number is greater than 0
     * 4. Pop the numbers from the stack and append them to a string
     * 5. Return the binary number
     * @param n the number to be converted to binary
     * @return the binary representation of the number
     */
    public static String decimalToBinary(int n) {
        LinkedStack stack = new LinkedStack();
        while(n > 0) {
            stack.push(n % 2);
            n = n / 2;
        }
        String binary = "";
        while(!stack.isEmpty()) {
            binary += stack.pop();
        }
        return binary;
    }

    /**
     * Implementation of Tower of Hanoi Problem using Stack
     * This method is used to solve the Tower of Hanoi problem using Stack
     * Algorithm:
     * 1. Create 4 stacks
     * 2. The first stack is used to keep track of the number of disks
     * 3. The second stack is used to keep track of the source pole
     * 4. The third stack is used to keep track of the intermediate pole
     * 5. The fourth stack is used to keep track of the destination pole
     * 6. Push the number of disks, source pole, intermediate pole and destination pole into the stacks
     * 7. Pop the numbers from the stacks and move the disks from source to destination using the intermediate pole
     * 8. This works by pushing the numbers back into the stack in the correct order
     * 9. The correct order is determined by the Tower of Hanoi algorithm which works as follows: 
     *  a. Move n-1 disks from source to intermediate using destination
     *  b. Move the nth disk from source to destination
     *  c. Move n-1 disks from intermediate to destination using source
     * 10. Repeat the process until the number of disks is 1
     * 11. Print the move from source to destination
     * 
     * No. of movements: 2^n - 1
     * @param n the number of disks
     */
    public static void towerOfHanoi(int n) {
        ArrayStack s_n = new ArrayStack(n*n);
        ArrayStack s_s = new ArrayStack(n*n);
        ArrayStack s_i = new ArrayStack(n*n);
        ArrayStack s_d = new ArrayStack(n*n);

        s_n.push(n);
        s_s.push('A');
        s_i.push('B');
        s_d.push('C');

        while(!s_n.isEmpty()) {
            int x = (int) s_n.pop();
            char s = (char) s_s.pop();
            char i = (char) s_i.pop();
            char d = (char) s_d.pop();

            if(x > 1) {
                s_n.push(x-1);
                s_s.push(i);
                s_i.push(s);
                s_d.push(d);

                s_n.push(1);
                s_s.push(s);
                s_i.push(i);
                s_d.push(d);

                s_n.push(x-1);
                s_s.push(s);
                s_i.push(d);
                s_d.push(i);
            } else {
                System.out.println("Move " + s + " -> " + d);
            }
        }
    }

    // main method for testing
    public static void main(String[] args) {
        // Test infixToPostfix
        String infix = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println("Infix to Postfix: " + infix);
        System.out.println(infixToPostfix(infix));

        // Test evaluatePostfix
        String postfix = "231*+9-";
        System.out.println("Evaluating Postfix: " + postfix);
        System.out.println(evaluatePostfix(postfix));

        // Test validParentheses
        String exp = "(a+b*(c^d-e)^(f+g*h)-i)";
        System.out.println("Valid Parentheses: " + exp);
        System.out.println(validParentheses(exp));

        // Test Rat in a Maze
        int[][] maze = {
            {0, 2, 2, 2, 2, 2, 2},
            {0, 0, 0, 0, 0, 0, 2},
            {2, 0, 2, 2, 2, 0, 2},
            {2, 0, 0, 2, 0, 2, 2},
            {2, 2, 0, 2, 0, 2, 2},
            {2, 0, 0, 0, 0, 0, 2},
            {2, 2, 2, 2, 2, 0, 0}
        };
        ratInAMaze(maze);

        // Test factorial
        System.out.println("Factorial of 10: ");
        System.out.println(factorialStack(10));

        // Test GCD
        System.out.println("GCD of 12 and 15:");
        System.out.println(gcdStack(12, 15));

        // Test Fibonacci
        System.out.println("Fibonacci of 10:");
        System.out.println(fibonacciStack(10));

        // Test Fibonacci Easy
        System.out.println("Fibonacci of 10:");
        System.out.println(fibonacciStackEasy(10));

        // Test Decimal to Binary
        System.out.println("Binary of 13:");
        System.out.println(decimalToBinary(13));

        // Test Tower of Hanoi
        System.out.println("Running TOH on n = 3: ");
        towerOfHanoi(3);
    }
}