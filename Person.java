/**
 * The BMI of a person is calculated by weight (in kg) divided by height^2 (in meter) and represetned in kg/m^2. From BMI, the BMI Category of a person is computed by:
 * 
 * BMI Value - Category
 * < 18.5 - Underweight
 * 18.5 - 24.9 - Normal
 * 25 - 29.9 - Overweight
 * >= 30 - Obese
 * 
 * Define Person Class with name, weight, and height data members. getBMI() and getBMICategory() member functions and overload >>,<<,< and >= operators. Thereafter write read(Person a[], int n) and print(Person a[], int n) functions for reading and printing the information of n persons; and heapSort(Person a[], int n) to sort the person in increasing order of their BMI Values using Heap Sort. Illustrate the use of these functions in main function. (Don't store the BMI value in the object, calculate it on the fly.)
 */
import java.util.Scanner;

public class Person implements Comparable<Person> {
    private String name;
    private double weight; // in kg
    private double height; // in meters

    public Person() {
    }

    public Person(String name, double weight, double height) {
        this.name = name;
        this.weight = weight;
        this.height = height;
    }

    public double getBMI() {
        return weight / (height * height);
    }

    public String getBMICategory() {
        double bmi = getBMI();
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    // Implementing Comparable interface
    @Override
    public int compareTo(Person other) {
        return Double.compare(this.getBMI(), other.getBMI());
    }

    // For printing Person details
    @Override
    public String toString() {
        return "Name: " + name + ", BMI: " + String.format("%.2f", getBMI()) + ", Category: " + getBMICategory();
    }

    // Heap Sort function
    public static void heapSort(Person[] a, int n) {
        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(a, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            Person temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            // call max heapify on the reduced heap
            heapify(a, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is an index in a[]
    private static void heapify(Person[] a, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // If left child is larger than root
        if (left < n && a[left].compareTo(a[largest]) < 0)
            largest = left;

        // If right child is larger than largest so far
        if (right < n && a[right].compareTo(a[largest]) < 0)
            largest = right;

        // If largest is not root
        if (largest != i) {
            Person swap = a[i];
            a[i] = a[largest];
            a[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(a, n, largest);
        }
    }

    // Read array of Persons
    public static void read(Person[] a, int n) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.println("Enter name, weight (kg), and height (m) for person " + (i + 1) + ":");
            String name = scanner.next();
            double weight = scanner.nextDouble();
            double height = scanner.nextDouble();
            a[i] = new Person(name, weight, height);
        }
    }

    // Print array of Persons
    public static void print(Person[] a, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of persons: ");
        int n = scanner.nextInt();
        Person[] persons = new Person[n];

        read(persons, n);
        heapSort(persons, n);
        System.out.println("Sorted Persons by BMI:");
        print(persons, n);
    }
}