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

    // main method for testing
    public static void main(String[] args) {
        String infix = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println(infixToPostfix(infix));

        String postfix = "231*+9-";
        System.out.println(evaluatePostfix(postfix));

        String exp = "(a+b*(c^d-e)^(f+g*h)-i)";
        System.out.println(validParentheses(exp));

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
    }
}