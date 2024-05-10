    import java.util.Stack;

    /**
     * This file contains previous year questions and solutions of stack data structure.
     */

    public class StackPYQ {
        /**
         * Question 1:
         * Write a recursive function, dsum(n), in mathematical equation form to get the sum of the digits of a positive integer and write an algorithm to implement it using stack 
         * 
         * Algorithm:
         * 1. Create a function dsum(n) which takes an integer n as input.
         * 2. If n is 0, return 0.
         * 3. Return n % 10 + dsum(n / 10).
         * 
         * Algorithm with Stack:
         * 1. Create a function dsumWithStack(n) which takes an integer n as input.
         * 2. Create a stack.
         * 3. While n is greater than 0, push n % 10 to the stack and update n to n / 10.
         * 4. Create a variable sum and initialize it to 0.
         * 5. While stack is not empty, pop the top element from the stack and add it to sum.
         * 6. Return sum.
         */   
        public static int dsum(int n) {
            if (n == 0) {
                return 0;
            }
            return n % 10 + dsum(n / 10);
        }

        public static int dsumWithStack(int n) {
            Stack<Integer> stack = new Stack<>();
            while(n > 0) {
                stack.push(n % 10);
                n = n / 10;
            }
            int sum = 0;
            while(!stack.isEmpty()) {
                sum += stack.pop();
            }
            return sum;
        }

        /**
         * Question:
         * Write efficient getFibStack(n) to get the nth Fibonacci term using stack and compute 6th Fibonacci Term using this algorithm. Also determine the maximum size of stack needed in this computation. [Assume that first 2 fibonacci terms are 1]
         * 
         * Algorithm:
         * 1. Create a function getFibStack(n) which takes an integer n as input.
         * 2. Create an array stack with capacity n/2 + 1.
         * 3. Push n onto the stack.
         * 4. Initialize fib to 0.
         * 5. While stack is not empty, do the following:
         *      a. Pop the top element x from the stack.
         *      b. If x is less than or equal to 2, increment fib by 1.
         *      c. Otherwise, push x-1 and x-2 onto the stack.
         * 6. Return fib.
         * @param n The index of the Fibonacci number to calculate.
         * 
         * 
         * Let n be the index of the Fibonacci number to calculate.
         * Observe the stack growth pattern:
         * Each popped element x > 2 pushes x-1 and x-2 onto the stack.
         * Stack size increases by at most 1 for each popped element.
         * Maximum stack size (max_size) is reached when approximately n/2 elements are popped:
         * max_size ≈ n/2
         * 
         * Add a buffer of 1 to ensure sufficient capacity
         * stack_size = max_size + 1
         * stack_size ≈ n/2 + 1
         */
        public static int getFibStack(int n) {
            ArrayStack stack = new ArrayStack(n/2+1);
            stack.push(n);
            int fib = 0;
            while(!stack.isEmpty()) {
                int x = (int) stack.pop();
                if(x <= 2) {
                    fib += 1;
                } else {
                    stack.push(x-1);
                    stack.push(x-2);
                 }
            }
            return fib;
        }

        /**
         * Question 3:
         * Convert the mathematical expression (10*4+6)/(8-3*2) into prefix and postfix form directly.
         * Then write an algorithm to convert it into postfix form using stack.
         * 
         * Direct Method:
         * Prefix:
         * (10*4+6)/(8-3*2)
         * ((*,10,4)+6)/(8-(*,3,2))
         * (+,*,10,4,6)/(-,8,*,3,2)
         * /,+,*,10,4,6,-,8,*,3,2
         * 
         * Postfix:
         * (10*4+6)/(8-3*2)
         * ((10,4,*)+6)/(8-(3,2,*))
         * (10,4,*,6,+)/(8,3,2,*,-)
         * 10,4,*,6,+,8,3,2,*,-,/
         * 
         * Postfix Using Stack:
         *  +---------+------------+------------+----------------+
            | Sr. no. | Expression | Stack      | Postfix        |
            +---------+------------+------------+----------------+
            | 0       |            | (          |                |
            | 1       | (          | ( (        |                |
            | 2       | 1          | ( (        | 1              |
            | 3       | 0          | ( (        | 10             |
            | 4       | *          | ( ( *      | 10             |
            | 5       | 4          | ( ( *      | 104            |
            | 6       | +          | ( ( +      | 104*           |
            | 7       | 6          | ( ( +      | 104*6          |
            | 8       | )          | (          | 104*6+         |
            | 9       | /          | ( /        | 104*6+         |
            | 10      | (          | ( / (      | 104*6+         |
            | 11      | 8          | ( / (      | 104*6+8        |
            | 12      | \-         | ( / ( -    | 104*6+8        |
            | 13      | 3          | ( / ( -    | 104*6+83       |
            | 14      | *          | ( / ( - *  | 104*6+83       |
            | 15      | 2          | ( / ( - *  | 104*6+832      |
            | 16      | )          | ( /        | 104*6+832*-    |
            | 17      | )          |            | 104*6+832*-/   |
            +---------+------------+------------+----------------+
        * 
        * Algorithm:
        * Step 1: Add ")" to the end of the infix expression
        * Step 2: Push "(" onto the stack
        * Step 3: Repeat until each character in the infix notation is scanned
        *       a. IF a "(" is encountered, push it on the stack
        *       b. IF an operand ( whether a digit or a character) is encountered, add it postfix expression.
        *       c. IF a ")" is encountered, then
        *               i. Repeatedly pop from stack and add it to the postfix expression until a "(" is encountered.
        *               ii. Discard the "(". That is, remove the(from stack and do not add it to the postfix expression
        *       d. IF an operator O is encountered, then
        *               i. Repeatedly pop from stack and add each operator ( popped from the stack) to the postfix expression which has equal or higher precedence than O
        *               ii. Push the operator O to the stack.
        * Step 4: Repeatedly pop from the stack and add it to the postfix expression until the stack is empty
        * Step 5: Exit
        */
        public static String infixToPostfix(String infix) {
            Stack<Character> stack = new Stack<>();
            infix += ")";
            stack.push('(');
            String postfix = "";
            for(int i = 0; i < infix.length(); i++) {
                char ch = infix.charAt(i);
                if(ch == '(')
                    stack.push(ch);
                else if(ch == ')') {
                    char y = (char) stack.pop();
                    while(y != '(') {
                        postfix += y;
                        y = (char) stack.pop();
                    }
                } else if(isOperator(ch)) {
                    char y = (char) stack.pop();
                    while(isOperator(y) && precedence(y) >= precedence(ch)) {
                        postfix += y;
                        y = (char) stack.pop();
                    }
                    stack.push(y);
                    stack.push(ch);
                } else if(Character.isDigit(ch))
                    postfix += ch;
                else {
                    System.out.println("Invalid character in infix expression");
                    return "";
                }
            }
            return postfix;
        }

        /**
         * Precedence of operators
         */
        public static int precedence(char ch) {
            switch(ch) {
                case '+':
                case '-':
                    return 1;
                case '*':
                case '/':
                    return 2;
                case '^':
                    return 3;
                default:
                    return 0;
            }
        }

        /**
         * Validity of Operator
         */
        public static boolean isOperator(char ch) {
            return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
        }

        /**
         * Question 4:
         * Let a stack and circular queue is represented by an array [0:2m-1] for m > 0. Where first half array is used for stack and 2nd half array is circular queue. Assume that stack grow from beginning to mid and queue from end to mid. Write algorithms for push(x) operation of the stack and insert(x) and del() operations of the circular queue.
         * 
         * Answer 4:
         * Check the file ArrayStackQueue.java for the solution of this question.
         */

        /**
         * Question 5:
         * Convert the following mathematical expression into prefix and postfix form directly without using stack. Also convert it into postfix form using stack.
         * ((10-4)*5)/(8-3*2)
         * 
         * Prefix: 
         * ((-,10,4)*5)/(8-3*2)
         * (*,-,10,4,5)/(8-(*,3,2))
         * (*,-,10,4,5)/(-,8,*,3,2)
         * /,*,-,10,4,5,-,8,*,3,2
         * 
         * Postfix:
         * ((10-4)*5)/(8-3*2)
         * ((10,4,-)*5)/(8-(3,2,*))
         * (10,4,-,5,*)/(8,3,2,*,-)
         * 10,4,-,5,*,8,3,2,*,-,/
         * 
         * Postfix Using Stack:
         * 
                +---------+------------+-----------+--------------+
                | Sr. no. | Expression | Stack     | Postfix      |
                +---------+------------+-----------+--------------+
                | 0       |            | (         |              |
                | 1       | (          | ( (       |              |
                | 2       | 1          | ( (       | 1            |
                | 3       | 0          | ( (       | 10           |
                | 4       | -          | ( ( -     | 10           |
                | 5       | 4          | ( ( -     | 104          |
                | 6       | )          | (         | 104-         |
                | 7       | *          | ( *       | 104-         |
                | 8       | 5          | ( *       | 104-5        |
                | 9       | /          | ( /       | 104-5*       |
                | 10      | (          | ( / (     | 104-5*       |
                | 11      | 8          | ( / (     | 104-5*8      |
                | 12      | -          | ( / ( -   | 104-5*8      |
                | 13      | 3          | ( / ( -   | 104-5*83     |
                | 14      | *          | ( / ( - * | 104-5*83     |
                | 15      | 2          | ( / ( - * | 104-5*832    |
                | 16      | )          | ( /       | 104-5*832*-  |
                | 17      | )          |           | 104-5*832*-/ |
                +---------+------------+-----------+--------------+
         */

        /**
         * Question 6:
         * Write a recursive function decimal(x) in mathematical equation to convert a positive octal integer number x into equivalent decimal integer and write an algorithm to implement it using stack.
         * 
         * Recursive Algorithm:
         * 1. Create a function decimal(x) which takes an integer x as input.
         * 2. If x is 0, return 0.
         * 3. Return x % 10 + 8 * decimal(x / 10).
         * 
         * Algorithm to convert octal to decimal using stack:
         * 1. Create a function decimalWithStack(x) which takes an integer x as input.
         * 2. Create a stack.
         * 3. While x is greater than 0, push x % 10 to the stack and update x to x / 10.
         * 4. Create a variable decimal and initialize it to 0.
         * 5. Create a variable power and initialize it to stack.size()-1.
         * 6. While stack is not empty, do the following:
         *     a. Pop the top element from the stack and add it to decimal multiplied by 8 raised to the power.
         *     b. Decrement the power by 1.
         * 7. Return decimal.
        */
        public static int decimal(int x) {
            if (x == 0) {
                return 0;
            }
            return x % 10 + 8 * decimal(x / 10);
        }

        public static int decimalWithStack(int x) {
            Stack<Integer> stack = new Stack<>();

            while(x > 0) {
                stack.push(x % 10);
                x = x / 10;
            }
            int decimal = 0;
            int power = stack.size()-1;
            while(!stack.isEmpty()) {
                decimal += stack.pop() * Math.pow(8, power);
                power--;
            }
            return decimal;
        }




        // Main method
        public static void main(String[] args) {
            System.out.println(dsum(12345)); // 15
            System.out.println(dsumWithStack(12345)); // 15
            System.out.println(getFibStack(6)); // 8
            System.out.println(infixToPostfix("(10*4+6)/(8-3*2)")); // 104*6+832*-/
            System.out.println(infixToPostfix("((10-4)*5)/(8-3*2)")); // 104-5*832*-/
            System.out.println(decimal(123)); // 83
            System.out.println(decimalWithStack(123)); // 83
        }
    }