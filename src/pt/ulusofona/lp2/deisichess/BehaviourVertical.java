package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class BehaviourVertical extends Behaviour {


    @Override
    public boolean isDiagonal(BehaviourData behaviorData) {
        return false;
    }

    @Override
    public boolean isHorizontal(BehaviourData behaviorData) {
        return false;
    }

    @Override
    public boolean isVertical(BehaviourData behaviorData) {
        return true;
    }

    @Override
    public void simulateBehaviour(BehaviourData behaviorData, ArrayList<Piece> boardPieces) {
        switch (this.getDirection()) {
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
    }

    @Override
    public boolean isValid(BehaviourData behaviorData) {
        var isValidVertical = behaviorData.yStart != behaviorData.yEnd;
        var isValidHorizontal = behaviorData.xStart != behaviorData.xEnd;
        return isValidVertical && !isValidHorizontal && isDifferentThanStartingPosition(behaviorData);
    }


    @Override
    public void calculateDirection(BehaviourData behaviorData) {
        var isUp = BehaviourVertical.isUp(behaviorData.yStart, behaviorData.yEnd);
        setDirection(isUp ? Direction.UP : Direction.DOWN);
    }

    public static boolean isUp(int yStart, int yEnd) {
        return yEnd < yStart;
    }

    public static boolean isDown(int yStart, int yEnd) {
        return yEnd < yStart;
    }

}
