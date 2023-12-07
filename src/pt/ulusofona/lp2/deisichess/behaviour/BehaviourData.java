package pt.ulusofona.lp2.deisichess.behaviour;

public class BehaviourData {
    int xStart;
    int yStart;
    int xEnd;
    int yEnd;

    public BehaviourData(int xStart, int yStart, int xEnd, int yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }


    public int calculateDistance() {
        int deltaX = Math.abs(xEnd - xStart);
        int deltaY = Math.abs(yEnd - yStart);

        return Math.max(deltaX, deltaY);
    }
}
