/** 
 * Array Class is used to store elements in an array and perform operations on the array
 */
public class Array {
    /**
     * Data Members
     * @param array - stores elements in a 1d array named element
     * @param length - the total length of the array
     * @param size - the size of the number of elements currently present in the array
     */
    private Object[] element;
    private int length;
    private int size;

    /** Constructor
     * @param length - takes in the length of the element array
     * @throws IllegalArgumentException - if the length given is less than 1
     */
    public Array(int length) {
        if(length < 1)
            throw new IllegalArgumentException("initial capacity must be > 1");

        this.length = length;
        element = new Object[length];
    }

    /** @return true iff array is empty */
    public boolean isEmpty() { return size == 0; }

    /** @return size of the list */
    public int getSize() { return size; }

    /** @throws IndexOutOfBoundsException when
     * index is not between 0 and size - 1
     */
    public int insert(Object theElement, int index) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException("index = "+index+" size = "+size);

        if(size == length)
            throw new ArrayStoreException("Array is already full.");

        int totalMovements = 0;

        for(int i = size-1; i >= index; i--) {
            element[i+1] = element[i];
            totalMovements++;
        }

        element[index] = theElement;
        size++;
        return totalMovements;
    }

    /** @throws IndexOutOfBoundsException when
     * index is not between 0 and size - 1 
     */
        public int del(int index) {
            if(index < 0 || index >= size)
                throw new IndexOutOfBoundsException("index = "+index+" size = "+size);

            // Object toBeRemoved = element[index];
            int totalMovements = 0;

            for(int i = index+1; i < size; i++) {
                element[i-1] = element[i];
                totalMovements++;
            }
            
            size--;
            return totalMovements;
        }

    /** @return index of theElement in element */
    public int indexOf(Object theElement) {
        for(int i = 0; i < size; i++)
            if(element[i].equals(theElement))
                return i;
        
        return -1;
    }

    /** @return theElement of element present at index */
    public Object get(int index) {
        if(index < 0 || index >= size) 
            throw new IndexOutOfBoundsException("index = "+index+" size = "+size);

        return element[index];
    }

    /** convert to a string */
    @Override  
    public String toString() {
        StringBuffer s = new StringBuffer("[");

        // put elements in the buffer
        for(int i = 0; i < size; i++)
            if(element[i] == null)
                s.append("null, ");
            else
                s.append(element[i].toString()+", ");
        
        if(size > 0)
            s.delete(s.length()-2, s.length());      // remove last ", "
        
        s.append("]");

        // create equivalent String
        return new String(s);
    }

    /** Store series 1,2,2,3,4,4,5,6,6... in the MyArray in minimum movements 
     * Total Movements: 0
     * Why? Because we are inserting at the end of the array
     * so there will be no movements
    */
    public static void storeDoubleEvenSingleOddMin(Array arr, int n) {
        int totalMovements = 0;
        for(int i = 1; i <= n; i++) {
            if(i % 2 == 0) {
                totalMovements += arr.insert(i, arr.getSize());
                totalMovements += arr.insert(i, arr.getSize());
            } else {
                totalMovements += arr.insert(i, arr.getSize());
            }
        }
        System.out.println("Total movements: "+totalMovements);
    } 

    /** Store series 1,2,2,3,4,4,5,6,6... in the MyArray in maximum movements 
     * Total Movements: (n+floor(n/2)-1)*(n+floor(n/2))/2
     * Why? Because we are inserting at the beginning of the array and
     * the total number of movements is the sum of the first n whole numbers
     * since the first insertion will have 0 movements, the second will have 1, the third will have 2 and so on
     * and the total number of elements that we have to insert is n+floor(n/2)
     * so the total number of movements is the sum of the first n whole numbers
     * whose formula is
     * (n*n-1)/2
     * where n = n+floor(n/2)
     */ 
    public static void storeDoubleEvenSingleOddMax(Array arr, int n) {
        int totalMovements = 0;
        for(int i = n; i >= 1; i--) {
            if(i % 2 == 0) {
                totalMovements += arr.insert(i, 0);
                totalMovements += arr.insert(i, 0);
            } else {
                totalMovements += arr.insert(i, 0);
            }
        }
        System.out.println("Total movements: "+totalMovements);
    }

    /** Store series 1,2,2,3,4,4,5,6,6... in the MyArray using Index Formula
     * Total Movements: floor(n/2)
     * Why? Because whenever we insert an even number
     * we will have to move the elements by 1
     * and the total even numbers in the series are floor(n/2)
     * So the number of movements will be floor(n/2)
     * Insersion of odd numbers will not require any movements
     */
    public static void storeDoubleEvenSingleOddIndex(Array arr, int n) {
        int totalMovements = 0;
        for(int i = 1; i <= n; i++) {
            if(i % 2 == 0) {
                totalMovements += arr.insert(i, (3*i-3)/2);
                totalMovements += arr.insert(i, (3*i-3)/2);
            } else {
                totalMovements += arr.insert(i, (3*i-3)/2);
            }
        }
        System.out.println("Total movements: "+totalMovements);
    }

    /** insert 2,2,4,4,6,6,8,8,10,10,1,3,5,7,9 in the Array 
     * Total Movements: (n*(n+2))/4
     * Why? Let's see the computation process for n = 10:
        +---+------+---+------+---+------+-----+------+
        | x |  nom | x |  nom | x |  nom |  x  |  nom |
        +---+------+---+------+---+------+-----+------+
        | 1 |    0 | 3 |    0 | 5 |    0 | n-1 | 0    |
        | 2 |    1 | 4 |    2 | 6 |    3 | n   | n/2  |
        | 2 |    1 | 4 |    2 | 6 |    3 | n   | n/2  |
        +---+------+---+------+---+------+-----+------+
     * where nom = number of movememts
     * Total Movements: 1+1+2+2+3+3+...+n/2+n/2 =  2 * (n/2 * (n/2+1))/2 = (n*(n+2))/4
     * How does it work?
     * Odd insertions always happen at end of the array so they cause NO MOVEMENTS
     * Even insertion however happens before the ODD Series starts and EACH even insertions causes the no of movements corresponding to the number of odd integers currently present in the array
     */
    public static void storeDoubleEvenThenOdd(Array arr, int n) {
        int totalMovements = 0;
        if(n % 2 == 1) {
            throw new IllegalArgumentException("n must be even");
        }

        for(int i = 1; i <= n; i++) {
            if(i % 2 == 1) {
                totalMovements += arr.insert(i,3*(i-1)/2);
            } else {
                totalMovements += arr.insert(i,(i-2));
                totalMovements += arr.insert(i,(i-1));
            }
        }

        System.out.println("Total movements: "+totalMovements);
    }

    /** insert 1,1,3,3,5,5,7,7,9,9,2,4,6,8,10 in the Array 
     * Total Movements: n(n-2)/4
     * Why? Let's see the computation process for n = 10:
        +---+------+---+------+---+------+-----+------+
        | x |  nom | x |  nom | x |  nom |  x  |  nom |
        +---+------+---+------+---+------+-----+------+
        | 1 |    0 | 3 |    1 | 5 |    2 | n-1 | n-2/2|
        | 1 |    0 | 3 |    1 | 5 |    2 | n-1 | n-2/2|
        | 2 |    0 | 4 |    0 | 6 |    0 | n   | 0    |
        +---+------+---+------+---+------+-----+------+
     * where nom = number of movememts
     * Total Movements: 0+2+4+6+...+n-2 = 2(1+2+3+....+(n-2)/2)
     * Sum of Natural Number Series: n(n+1)/2
     * Put n = (n-2)/2
     * 2 * ((n-2)/2 * ((n-2)/2+1))/2 = n(n-2)/4
     * 
     * How does it work?
     * Even insertions always happen at end of the array so they cause NO MOVEMENTS
     * Odd insertion however happens before the Even Series starts and EACH odd insertions causes the no of movements corresponding to the number of even integers currently present in the array
     */
    public static void storeDoubleOddThenEven(Array arr, int n) {
        int totalMovements = 0;
        if(n % 2 == 1) {
            throw new IllegalArgumentException("n must be even");
        }

        for(int i = 1; i <= n; i++) {
            System.out.println("Movents in iteration "+i+": "+totalMovements);
            if(i % 2 == 0) {
                totalMovements += arr.insert(i,3*i/2-1);
            } else {
                totalMovements += arr.insert(i,(i-1));
                totalMovements += arr.insert(i,(i));
            }
        }

        System.out.println("Total movements: "+totalMovements);
    }

    /**
     * Store the following series:
     * 1,1,1,1,1,2,2,2,2,2,3,3,3,3,3....n,n,n,n,n
     * in Array in maximum movements
     * 
     * Formula:
     * Total Movements: 5n(5n-1)/2
     */
    public static void storePentaNaturalSeriesMax(Array arr, int n) {
        int totalMovements = 0;
        for(int i = n; i >= 1; i--) {
            for(int j = 1; j <= 5; j++) {
                totalMovements += arr.insert(i, 0);
            }
        }
        System.out.println("Total movements: "+totalMovements);
    }

    /**
     * Store the series
     * 1,2,2,2,2,3,3,3,3,3,3,3,3,3,....,n,n,n,n,n^2
     */
    public static void storeInNumsOfPower(Array arr, int n) {
        int totalMovements = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= i*i; j++) {
                totalMovements += arr.insert(i, arr.getSize());
            }
        }
        System.out.println("Total movements: "+totalMovements);
    }

    /**
     * Delete Duplicates from the given series:
     * 1,2,2,2,2,3,3,3,3,3,3,3,3,3,....,n,n,n,n,n^2
     */
    public static void deleteInNumsPower(Array arr, int n) {
        int prev = (int)arr.get(arr.getSize()-1); // Variable to track the previous number

        // Iterate through the arr7ay starting from the second element
        for (int currentIndex = arr.getSize()-2; currentIndex >=0 ; currentIndex--) {
            int currentNum = (int)arr.get(currentIndex);
            if (currentNum == prev) {
                // If the current number is the same as the previous one,
                // delete it from the arr7ay
                arr.del(currentIndex);
            } else {
                // If the current number is different, update 'prev'
                prev = currentNum;
            }
        }
    }

    /** Given Series 1,2,2,3,4,4,5,6,6....n for a given value n
     * We want to delete duplicates of the series using del(index) method
     * Write algorithm for this task that takes minimum number of movements
     * 
     * When n is even:
     * Total Movements: (n * n-2) / 4
     * 
     * Why?
     * Let's see the computation process for n = 8:
         +----+-----+
        | i  | nom |
        +----+-----+
        | 11 |   0 |
        |  8 |   2 |
        |  5 |   4 |
        |  2 |   6 |
        +----+-----+
     * where nom = number of movements
     * and i = index to delete
     * 
     * Total Movements: 2+4+6 = 12 = (8*6)/4 = 12
     * 
     * Formula Derivation using AP:
     * a = 0
     * a_m = n-2
     * m = n/2
     * S_m = m/2(a+a_m) = (n/2)/2(0+n-2) = (n * n-2)/4 
     * 
     * When n is odd:
     * Total Movements: (n-1)^2 / 4
     *
     * Why?
     * Let's see the computation process for n = 7:
     * 
        +---+-----+
        | i | nom |
        +---+-----+
        | 8 |   1 |
        | 5 |   3 |
        | 2 |   5 |
        +---+-----+

     * where nom = number of movements
     * and i = index to delete
     * 
     * Total Movements: 1+3+5 = 9 = (7-1)^2 / 4 = 9
     * 
     * Formula Derivation using AP:
     * a = 1
     * a_m = n-2
     * m = (n-1)/2
     * S_m = m/2(2a+(m-1)d) = (n-1)/2(2+((n-1)/2-1)2) = (n-1)^2 / 4
     */
    public static void deleteDuplicatesMin(Array arr, int n) {
        int totalMovements = 0;
        int m = 0;
        if(n % 2 == 1) {
            m = n+((n-1)/2)-2;
        } else {
            m = n + (n/2) -1;
        }

        for(int i = m; i >= 2; i-=3) {
            // System.out.println("m = "+m+" i = "+i);
            totalMovements += arr.del(i);
        }

        System.out.println("Total movements: "+totalMovements);
    }

    /** Given Series 1,2,2,3,4,4,5,6,6....n for a given value n
     * We want to delete duplicates of the series using del(index) method
     * Write algorithm for this task that takes minimum number of movements
     * 
     * When n is even:
     * Total Movements: (n * 3n - 2) / 8
     * 
     * Why?
     * Let's see the computation process for n = 8:
     * 
        +----+-----+
        | i  | nom |
        +----+-----+
        |  1 |  10 |
        |  3 |   7 |
        |  5 |   4 |
        |  7 |   1 |
        +----+-----+
     *
     * where nom = number of movements
     * and i = index to delete 
     * 
     * Total Movements: 10+7+4+1 = 22 = (8 * 24 - 2) / 8 = (8*22)/8 = 22
     * 
     * Formula Derivation using AP:
     * a = (n + n/2) - 2 [in this case: 8+8/2-2 = 10]
     * a_m = 1
     * m = n/2
     * S_m = m/2(a+a_m) = (n/2)/2((n+n/2)-2+1) = n/4(3n-2) = (n * 3n - 2) / 8
     * 
     * When n is odd:
     * Total Movements: (n-1)(3n-1)/8
     * 
     * Why?
     * Let's see the computation process for n = 7:
     * 
        +---+-----+
        | i | nom |
        +---+-----+
        | 1 |  8  |
        | 3 |  5  |
        | 5 |  2  |
        +---+-----+
     * 
     * where nom = number of movements
     * and i = index to delete
     * 
     * Total Movements: 8+5+2 = 15 = (7-1)(3*7-1)/8 = 15 
     * 
     * Formula Derivation using AP:
     * a = (n + (n-1)/2) - 2 [in this case: 7+7/2-2 = 8]
     * a_m = 2
     * m = (n-1)/2
     * S_m = m/2(2a+(m-1)d) = (n-1)/2(2+(n-1)/2-2+2) = (n-1)(3n-1)/8
    */
    public static void deleteDuplicatesMax(Array arr, int n) {
        int totalMovements = 0;
        int m = 0;
        if(n % 2 == 1) {
            m = n-2;
        } else {
            m = n-1;
        }

        for(int i = 1; i <= m; i+=2) {
            totalMovements += arr.del(i);
        }

        System.out.println("Total movements: "+totalMovements);
    }

    // main
    public static void main(String[] args) {
        int n = 4;
        // Exercise 1(i) - Store 1,2,2,3,4,4,5,6,6...n in the Array in minimum movements
        // Array arr = new Array(3*n/2);
        // storeDoubleEvenSingleOddMin(arr, n);
        // System.out.println(arr.toString());

        // // Exericise 1(ii) - Store 1,2,2,3,4,4,5,6,6...n in the Array in maximum movements
        // Array arr2 = new Array(3*n/2);
        // storeDoubleEvenSingleOddMax(arr2, n);
        // System.out.println(arr2.toString());

        // // Exercise 2 - Store 1,2,2,3,4,4,5,6,6...n in the Array using Index Formula
        // Array arr3 = new Array(3*n/2);
        // storeDoubleEvenSingleOddIndex(arr3, n);
        // System.out.println(arr3.toString());

        // // Theory Exam Question - Store 2,2,4,4,6,6,8,8,10,10,1,3,5,7,9 in the Array
        // Array arr4 = new Array(3*n/2);
        // storeDoubleEvenThenOdd(arr4, n);
        // System.out.println(arr4.toString());

        // // Exercise 3(i) - Delete Duplicates from DoubleEvenThenOdd Series in Minimum Movements
        // System.out.println("Delete Duplicates Min");
        // deleteDuplicatesMin(arr, n);
        // System.out.println(arr.toString());

        // // Exercise 3(ii) - Delete Duplicates from DoubleEvenThenOdd Series in Maximum Movements
        // System.out.println("Delete Duplicates Max");
        // deleteDuplicatesMax(arr2, n);
        // System.out.println(arr2.toString());

        // // Theory Exam Question - Store 1,1,3,3,5,5,7,7,2,4,6,8 in the Array
        // Array arr5 = new Array(3*n/2);
        // storeDoubleOddThenEven(arr5, n);
        // System.out.println(arr5.toString());

        // Theory Exam Question - Store 1,1,1,1,1,2,2,2,2,2,3,3,3,3,3....n,n,n,n,n in the Array
        // Array arr6 = new Array(5*n);
        // storePentaNaturalSeriesMax(arr6, n);
        // System.out.println(arr6.toString());

        // Theory Exam Question - Store 1,2,2,2,2,3,3,3,3,3,3,3,3,3,....,n,n,n,n,n^2 in the Array
        // Array arr7 = new Array(n*(n+1)*(2*n+1)/6);
        // storeInNumsOfPower(arr7, n);
        // System.out.println(arr7.toString());

        // // Theory Exam Question - Delete Duplicates from storeInNumsOfPower Series in Minimum Movements
        // deleteInNumsPower(arr7, n);
        // System.out.println(arr7.toString());
    }
}