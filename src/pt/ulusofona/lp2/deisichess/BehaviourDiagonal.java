package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.beans.BeanHelper;

import java.util.ArrayList;

public class BehaviourDiagonal extends Behaviour {

    @Override
    public boolean isDiagonal() {
        return true;
    }

    @Override
    public boolean isHorizontal() {
        return false;
    }

    @Override
    public boolean isVertical() {
        return false;
    }

    @Override
    public boolean hasCollision(BehaviourData behaviorData, ArrayList<Piece> boardPieces) {

        virtualX = behaviorData.xStart;
        virtualY = behaviorData.yStart;

        while (virtualX != behaviorData.xEnd || virtualY != behaviorData.yEnd) {

            switch (this.getDirection()) {
                case UP_LEFT -> moveUpLeft();
                case UP_RIGHT -> moveUpRight();
                case DOWN_LEFT -> moveDownLeft();
                case DOWN_RIGHT -> moveDownRight();

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
        var isValidDiagonal = Math.abs(behaviorData.xEnd - behaviorData.xStart) == Math.abs(behaviorData.yEnd - behaviorData.yStart);
        return isValidDiagonal && isDifferentThanStartingPosition(behaviorData);
    }

    @Override
    public void calculateDirection(BehaviourData behaviorData) {
        var isUp = BehaviourVertical.isUp(behaviorData.yStart, behaviorData.yEnd);
        var isLeft = BehaviourHorizontal.isLeft(behaviorData.xStart, behaviorData.xEnd);

        if (isUp) {
            setDirection(isLeft ? Direction.UP_LEFT : Direction.UP_RIGHT);
        } else {
            setDirection(isLeft ? Direction.DOWN_LEFT : Direction.DOWN_RIGHT);
        }

    }

    //region static methods
    public void moveUpLeft() {
        moveUp();
        moveLeft();
    }

    public void moveUpRight() {
        moveUp();
        moveRight();
    }

    public void moveDownLeft() {
        moveLeft();
        moveDown();
    }

    public void moveDownRight() {
        moveRight();
        moveDown();
    }
    //endregion

}
