package pt.ulusofona.lp2.deisichess.behaviour;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.pieces.Piece;

import java.util.ArrayList;

public class BehaviourLShape extends Behaviour {
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
        return false;
    }

    @Override
    public boolean hasCollision(BehaviourData behaviorData, ArrayList<Piece> boardPieces) {
        setInitialVirtualPosition(behaviorData);

        if (hasCollisionOnDirection(behaviorData, boardPieces)) {
            switchMovementDirection(behaviorData);

            return hasCollisionOnDirection(behaviorData, boardPieces);
        }

        return false;
    }

    private boolean hasCollisionOnDirection(BehaviourData behaviorData, ArrayList<Piece> boardPieces) {
        if (hasCollisionOnCurrentSection(behaviorData, boardPieces)) {
            return true;
        }

        switchMovementSection();

        return hasCollisionOnCurrentSection(behaviorData, boardPieces);
    }

    private void simulateMovement() {
        switch (direction) {
            case UP_LEFT, UP_RIGHT -> moveUp();
            case DOWN_LEFT, DOWN_RIGHT -> moveDown();
            case LEFT_DOWN, LEFT_UP -> moveLeft();
            case RIGHT_DOWN, RIGHT_UP -> moveRight();
            case UP -> moveUp();
            case DOWN -> moveDown();
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
        }
    }

    private void simulateMovement(Direction direction) {
        switch (direction) {
            case UP_LEFT, UP_RIGHT -> moveUp();
            case DOWN_LEFT, DOWN_RIGHT -> moveDown();
            case LEFT_DOWN, LEFT_UP -> moveLeft();
            case RIGHT_DOWN, RIGHT_UP -> moveRight();
            case UP -> moveUp();
            case DOWN -> moveDown();
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
        }
    }

    private void switchMovementDirection(BehaviourData behaviorData) {

        setInitialVirtualPosition(behaviorData);

        switch (direction) {
            case UP_LEFT -> this.setDirection(Direction.LEFT_UP);
            case UP_RIGHT -> this.setDirection(Direction.RIGHT_UP);
            case DOWN_LEFT -> this.setDirection(Direction.LEFT_DOWN);
            case DOWN_RIGHT -> this.setDirection(Direction.RIGHT_DOWN);
        }
    }

    private void switchMovementSection() {
        switch (this.getDirection()) {
            case UP_LEFT, DOWN_LEFT -> this.setDirection(Direction.LEFT);
            case UP_RIGHT, DOWN_RIGHT -> this.setDirection(Direction.RIGHT);
            case LEFT_UP, RIGHT_UP -> this.setDirection(Direction.UP);
            case LEFT_DOWN, RIGHT_DOWN -> this.setDirection(Direction.DOWN);

        }
    }

    private boolean hasCollisionOnCurrentSection(BehaviourData behaviorData, ArrayList<Piece> boardPieces) {
        for (int i = 1; i <= behaviorData.calculateDistance(); i++) {

            simulateMovement();

            var hasCollided = Board.getPieceAt(virtualX, virtualY, boardPieces) != null;

            var isLastStep = i == behaviorData.calculateDistance();
            var ateEnemyPiece = hasCollided && isLastStep && isFinalDirection();
            if (ateEnemyPiece) {
                return false;
            }

            if (hasCollided) {
                return true;
            }

        }

        return false;
    }

    private boolean isFinalDirection() {
        switch (direction) {
            case LEFT, RIGHT, UP, DOWN -> {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isValid(BehaviourData behaviorData) {
        var isValidDiagonal = Math.abs(behaviorData.xEnd - behaviorData.xStart) == Math.abs(behaviorData.yEnd - behaviorData.yStart);
        var isValidHorizontal = behaviorData.xStart != behaviorData.xEnd;
        var isValidVertical = behaviorData.yStart != behaviorData.yEnd;

        return isValidDiagonal &&
                isValidHorizontal &&
                isValidVertical &&
                isDifferentThanStartingPosition(behaviorData);
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
            }

            if (Board.isValidCoordinate(virtualX, virtualY, boardSize)) {
                var xyPair = new ArrayList<Integer>();
                xyPair.add(virtualX);
                xyPair.add(virtualY);
                possibleMovements.add(xyPair);
            }

            switch (d) {
                case UP_LEFT -> setDirection(Direction.UP_RIGHT);
                case UP_RIGHT -> setDirection(Direction.DOWN_LEFT);
                case DOWN_LEFT -> setDirection(Direction.DOWN_RIGHT);
            }
        }

        return possibleMovements;
    }

}
