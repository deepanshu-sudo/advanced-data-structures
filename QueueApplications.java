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

    // Main method
    public static void main(String[] args) {
        // TestRailroad Car Re-Arrangement Problem 
        int inputOrder[] = {5, 8, 1, 7, 4, 2, 9, 6, 3};
        int n = inputOrder.length;
        int k = 2;
        railRoad(inputOrder, n, k);
    }
}
