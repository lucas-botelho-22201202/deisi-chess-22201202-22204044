package pt.ulusofona.lp2.deisichess.behaviour;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.pieces.Piece;

import java.lang.reflect.Array;
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

        setInitialVirtualPosition(behaviorData);

        while (!isOneSlotBehindDestination(virtualX, virtualY, behaviorData.xEnd, behaviorData.yEnd)) {
//        while (virtualX != behaviorData.xEnd || virtualY != behaviorData.yEnd) {

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

    @Override
    public ArrayList<ArrayList<Integer>> forseeMovements(BehaviourData behaviorData, ArrayList<Piece> boardPieces, int range, int boardSize) {

        var directions = new ArrayList<Direction>();
        directions.add(Direction.UP_LEFT);
        directions.add(Direction.UP_RIGHT);
        directions.add(Direction.DOWN_LEFT);
        directions.add(Direction.DOWN_RIGHT);
        setDirection(Direction.UP_LEFT);

        var possibleMovements = new ArrayList<ArrayList<Integer>>();
        for (Direction d : directions) {

            setInitialVirtualPosition(behaviorData);

            for (int i = 1; i <= range; i++) {

                switch (this.getDirection()) {
                    case UP_LEFT -> moveUpLeft();
                    case UP_RIGHT -> moveUpRight();
                    case DOWN_LEFT -> moveDownLeft();
                    case DOWN_RIGHT -> moveDownRight();

                }

                if (Board.isValidCoordinate(virtualX, virtualY, boardSize)) {
                    var xyPair = new ArrayList<Integer>();
                    xyPair.add(virtualX);
                    xyPair.add(virtualY);
                    possibleMovements.add(xyPair);
                }
            }

            switch (d) {
                case UP_LEFT -> setDirection(Direction.UP_RIGHT);
                case UP_RIGHT -> setDirection(Direction.DOWN_LEFT);
                case DOWN_LEFT -> setDirection(Direction.DOWN_RIGHT);
            }
        }

        return possibleMovements;
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
