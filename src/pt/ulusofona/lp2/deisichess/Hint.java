package pt.ulusofona.lp2.deisichess;

public class Hint implements Comparable<Hint> {

    private int points;
    private int x;
    private int y;

    public Hint(int points, int x, int y) {
        this.points = points;
        this.x = x;
        this.y = y;
    }

    public Hint(int x, int y) {
        this.x = x;
        this.y = y;
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Hint o) {
        return Integer.compare(o.getPoints(), this.points);
    }


    @Override
    public String toString() {
        return "(" + x + ","+y+") -> " + points;
    }
}
