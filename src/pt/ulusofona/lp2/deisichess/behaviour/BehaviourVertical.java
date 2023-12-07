package pt.ulusofona.lp2.deisichess.behaviour;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.pieces.Piece;

import java.util.ArrayList;

public class BehaviourVertical extends Behaviour {


    @Override
    public boolean isDiagonal() {
        return false;
    }

    @Override
    public boolean isHorizontal() {
        return false;
    }

    @Override
    public boolean isVertical() {
        return true;
    }

    @Override
    public boolean hasCollision(BehaviourData behaviorData, ArrayList<Piece> boardPieces) {
        setInitialVirtualPosition(behaviorData);

        while (!isOneSlotBehindDestination(virtualX, virtualY, behaviorData.xEnd, behaviorData.yEnd)) {

            switch (this.getDirection()) {
                case UP -> moveUp();
                case DOWN -> moveDown();
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
