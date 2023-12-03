package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class BehaviourHorizontal extends Behaviour {

    @Override
    public boolean isDiagonal(BehaviourData behaviorData) {
        return false;
    }

    @Override
    public boolean isHorizontal(BehaviourData behaviorData) {
        return true;
    }

    @Override
    public boolean isVertical(BehaviourData behaviorData) {
        return false;
    }

    @Override
    public void simulateBehaviour(BehaviourData behaviorData, ArrayList<Piece> boardPieces) {
        switch (this.getDirection()) {
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
        }
    }


    @Override
    public boolean isValid(BehaviourData behaviorData) {
        var isValidHorizontal = behaviorData.xStart != behaviorData.xEnd;
        var isValidVertical = behaviorData.yStart != behaviorData.yEnd;
        return isValidHorizontal && !isValidVertical && isDifferentThanStartingPosition(behaviorData);
    }


    @Override
    public void calculateDirection(BehaviourData behaviorData) {
        var isLeft = BehaviourHorizontal.isLeft(behaviorData.xStart, behaviorData.xEnd);
        setDirection( isLeft ? Direction.LEFT : Direction.RIGHT);
    }



    public static boolean isLeft(int xStart, int xEnd){
        return xEnd < xStart;
    }

    public static boolean isRight(int xStart, int xEnd){
        return xEnd > xStart;
    }
}
