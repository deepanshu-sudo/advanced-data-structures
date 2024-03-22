/**
 * This class has the methods for the following applications of a queue:
 * 1. Railroad Car Re-Arrangement
 * 2. Image Component Labeling
 * 3. Machine Shop Simulation
 */
public class QueueApplications {
    /**
     * Railroad Car Re-Arrangement Problem:
     * Let there be n cars that are numbered from 1 to n on an input line such that they are not in sequence from right to left i.e. [581742963]
     * There are k holding tracks to hold cars temporarily. The c ars can be moved from the input line to the holding tracks and then from the holding tracks to the output line.
     * The problem is to rearrange the cars from the input line to the output line such that the cars are in sequence from right to left i.e. [987654321]
     * For a car c,if it is feasible to keep on more than one holding tracks having some cars on them, then it will be keep on having largest car no on them that is < c.
     * @param inputOrder Array of cars
     * @param n Number of cars
     * @param k Number of holding tracks
     * @return boolean whether the cars are arranged or not
     * 
     * Algorithm Logic
     * 
     * Initialization:
     *      - The algorithm initializes k queues to represent the holding tracks (track[k]). 
     *      - It sets nextCarToOutput to 1, indicating the next car expected on the output track.
     * 
     * Iteration:
     *      The algorithm iterates through the input order of cars from right to left (i = n-1 to 0). For each car:
     * 
     *      - Direct Output Check:
     *          -- If the current car's number matches the nextCarToOutput, it is directly moved to the output track. nextCarToOutput is then incremented.
     * 
     *      - Output from Holding Tracks:
     *          -- The algorithm checks each holding track. If a track's top car aligns with nextCarToOutput, that car is moved to the output track. This process repeats for any subsequent cars in holding tracks that match the output sequence and nextCarToOutput is accordingly incremented.
     * 
     *      - Placement on a Holding Track:
     *          -- If the car can't be directly moved to the output track:
     *          -- The algorithm finds the most suitable holding track (the one with the largest car number that's still smaller than the current car).
     *          -- If no suitable holding track exists, the algorithm returns False, indicating failure.
     *          -- Otherwise, the car is placed on the identified holding track.
     * 
     * Success: Upon iterating through all the cars in the inputOrder, the algorithm returns True, signifying successful rearrangement.
     */
    @SuppressWarnings("unchecked")
    public static boolean railRoad(int[] inputOrder, int n, int k) {
        ArrayQueue<Integer> track[] = new ArrayQueue[k];
        for(int i = 0; i < k; i++) {
            track[i] = new ArrayQueue<Integer>(n);
        }

        int nextCarToOutput = 1;
        for(int i = n-1; i >= 0; i--) {
            if(inputOrder[i] == nextCarToOutput) {
                System.out.println("Move Car " + inputOrder[i] + " from input line to output line");
                nextCarToOutput++;

                for(int j = 0; j < k; j++) {
                    while(!track[j].isEmpty() && track[j].getFront() == nextCarToOutput) {
                        System.out.println("Move Car " + track[j].remove() + " from holding track " + (j+1) + " to output line");
                        nextCarToOutput++;
                        j = 0;
                    }
                }
            } else {
                int c = inputOrder[i];
                int bestTrack = -1;
                int bestLast = 0; 

                for(int j = 0; j < k; j++) {
                    if(!track[j].isEmpty()) {
                        int last = track[j].getRear();
                        if(c > last && last > bestLast) {
                            bestLast = last;
                            bestTrack = j;
                            break;
                        }
                    } else {
                        bestTrack = j;
                        break;
                    }
                }
                if(bestTrack == -1) {
                    return false;
                }
                track[bestTrack].insert(c);
                System.out.println("Move Car " + c + " from input line to holding track " + (bestTrack+1));
            }
        }

        return true;
    }

    /**
     * Image Component Labelling:
     * - A digital binary image is mxm matrix of pixels, where each pixel is either 0 or 1.
     * - A pixel with value 1 is called component pixel. Two adjacent component pixels are pixels of the  same image component .
     * - The objective of this problem is to label the component pixels so that two pixels get the same label iff they are pixels of same image component. 
     * - The algorithm uses a queue to label the component pixels in the image.
     * @param pixel Binary image matrix
     * @param m Number of rows
     * 
     * Algorithm:
     * Initialization:
     *     - The algorithm initializes a queue to store the component pixels.
     *     - It sets id to 1, which represents the label of the image component.
     * 
     * Outer Iteration:
     *      - The algorithm iterates through each pixel in the image matrix.
     * 
     * Component Discovery:
     *      - If the current pixel is a component pixel (value 1), the algorithm assigns a new label (id) to the component pixel. Then the id is incremented.
     *      - The component pixel is then added to the queue.
     * 
     * Flood Fill (Nearest Neighbour):
     *      - A while loop continues until the queue is not empty:
     *      - The algorithm removes a pixel from the queue.
     *      - It checks the four adjacent pixels (up, down, left, right) of the current pixel.
     *      - If an adjacent pixel is a component pixel, the algorithm assigns the current label (id) to that pixel and adds it to the queue.
     * 
     * Iteration & New Components:
     *      - The algorithm repeats the above steps for all pixels in the image matrix.
     *      - The algorithm assigns a unique label to each image component.
     *      - The algorithm prints the labeled image matrix.
     */
    public static void imageComponentLabeling(int[][] pixel, int m) {
        ArrayQueue<Point> queue = new ArrayQueue<Point>(m*m);
        int id = 1;

        for(int r = 0; r < m; r++) {
            for(int c = 0; c < m; c++) {
                if(pixel[r][c] == 1) {
                    pixel[r][c] = ++id;
                    queue.insert(new Point(r, c));

                    while(!queue.isEmpty()) {
                        Point p = queue.remove();
                        int row = p.x;
                        int col = p.y;

                        if(col < m-1 && pixel[row][col+1] == 1) { // Right
                            pixel[row][col+1] = id;
                            queue.insert(new Point(row, col+1));
                        }

                        if(row < m-1 && pixel[row+1][col] == 1) { // Down
                            pixel[row+1][col] = id;
                            queue.insert(new Point(row+1, col));
                        }

                        if(col > 0 && pixel[row][col-1] == 1) { // Left
                            pixel[row][col-1] = id;
                            queue.insert(new Point(row, col-1));
                        }

                        if(row > 0 && pixel[row-1][col] == 1) { // Up
                            pixel[row-1][col] = id;
                            queue.insert(new Point(row-1, col));
                        }
                    }
                }
            }
        }

        for(int r = 0; r < m; r++) {
            for(int c = 0; c < m; c++) {
                System.out.print(pixel[r][c] + " ");
            }
            System.out.println();
        }
    }

    // Main method
    public static void main(String[] args) {
        // TestRailroad Car Re-Arrangement Problem 
        int inputOrder[] = {5, 8, 1, 7, 4, 2, 9, 6, 3};
        int n = inputOrder.length;
        int k = 2;
        System.out.println("Railroad Car Re-Arrangement: ");
        railRoad(inputOrder, n, k);

        // Test Image Component Labeling
        System.out.println("\nImage Component Labeling: ");
        int pixel[][] = {
            {1, 0, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
        };
        imageComponentLabeling(pixel, 5);
    }
}
