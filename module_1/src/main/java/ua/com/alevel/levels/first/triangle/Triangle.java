package ua.com.alevel.levels.first.triangle;

public class Triangle {

    public Point a;
    public Point b;
    public Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static double calculateSquare(Point a, Point b, Point c) {
        return 0.5 * (Math.abs((b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y)));
    }
}
