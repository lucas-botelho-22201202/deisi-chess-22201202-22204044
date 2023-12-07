package pt.ulusofona.lp2.deisichess.behaviour;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.pieces.Piece;

import java.util.ArrayList;

public class BehaviourHorizontal extends Behaviour {

    @Override
    public boolean isDiagonal() {
        return false;
    }

    @Override
    public boolean isHorizontal() {
        return true;
    }

    @Override
    public boolean isVertical() {
        return false;
    }

    @Override
    public boolean hasCollision(BehaviourData behaviorData, ArrayList<Piece> boardPieces) {
        setInitialVirtualPosition(behaviorData);

        while (!isOneSlotBehindDestination(virtualX, virtualY, behaviorData.xEnd, behaviorData.yEnd)) {

            switch (this.getDirection()) {
                case LEFT -> moveLeft();
                case RIGHT -> moveRight();
            }

            var hasCollided = Board.getPieceAt(virtualX, virtualY, boardPieces) != null;

            if (hasCollided) {
                return true;
            }
        }

        return false;
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
        setDirection(isLeft ? Direction.LEFT : Direction.RIGHT);
    }


    public static boolean isLeft(int xStart, int xEnd) {
        return xEnd < xStart;
    }

    public static boolean isRight(int xStart, int xEnd) {
        return xEnd > xStart;
    }
}
