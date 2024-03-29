/**
 * Create a Point class 
 */
public class Point {
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