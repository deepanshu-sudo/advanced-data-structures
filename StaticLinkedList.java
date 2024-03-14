import java.util.Scanner;

public class StaticLinkedList {

    public static Node create(int n) {
        Node current = null;
        Node first = null;
        Scanner sc = new Scanner(System.in);
        for(int i = 1; i <= n; i++) {
            if(i == 1) {
                first = Node.createNewNode();
                current = first;
            } else {
                current.next = Node.createNewNode();
                current = current.next;
            }
            System.out.println("Enter Data: ");
            current.data = sc.nextInt();
        }
        current.next = null;
        return first;
    }

    public static void display(Node first) {
        Node current = first;
        System.out.print("[");
        while(current != null) {
            System.out.print(current.data+", ");
            current = current.next;
        }
        System.out.println(" ]");
    }

    public static int size(Node first) {
        Node current = first;
        int size = 0;
        while(current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    // main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of nodes: ");
        int n = sc.nextInt();
        Node first = create(n);
        display(first);
        System.out.println("Size: " + size(first));
    }
}
