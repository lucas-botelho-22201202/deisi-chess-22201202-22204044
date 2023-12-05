package pt.ulusofona.lp2.deisichess;

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
        virtualX = behaviorData.xStart;
        virtualY = behaviorData.yStart;

        var hasCollided = hasCollidedOnMovement(behaviorData, boardPieces);

        if (hasCollided) {
            switchDirectionOnCollision(behaviorData);
            if (hasCollidedOnMovement(behaviorData, boardPieces)) {
                return true;
            }
        }

        switchDirectionOnSuccess();
        return hasCollidedOnMovement(behaviorData, boardPieces);
    }

    private void simulateDirection() {
        switch (this.getDirection()) {
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

    private void switchDirectionOnCollision(BehaviourData behaviorData) {

        virtualX = behaviorData.xStart;
        virtualY = behaviorData.yStart;

        switch (this.getDirection()) {
            case UP_LEFT -> this.setDirection(Direction.LEFT_UP);
            case UP_RIGHT -> this.setDirection(Direction.RIGHT_UP);
            case DOWN_LEFT -> this.setDirection(Direction.LEFT_DOWN);
            case DOWN_RIGHT -> this.setDirection(Direction.RIGHT_DOWN);
        }
    }

    private void switchDirectionOnSuccess() {
        switch (this.getDirection()) {
            case UP_LEFT, DOWN_LEFT -> this.setDirection(Direction.LEFT);
            case UP_RIGHT, DOWN_RIGHT -> this.setDirection(Direction.RIGHT);
            case LEFT_UP, RIGHT_UP -> this.setDirection(Direction.UP);
            case LEFT_DOWN, RIGHT_DOWN -> this.setDirection(Direction.DOWN);

        }
    }

    private boolean hasCollidedOnMovement(BehaviourData behaviorData, ArrayList<Piece> boardPieces) {
        for (int i = 0; i < behaviorData.calculateDistance(); i++) {

            simulateDirection();

            var hasCollided = Board.getPieceAt(virtualX, virtualY, boardPieces) != null;

            if (hasCollided) {

                return true;
            }

        }

        return false;
    }

//    private boolean tryMove(BehaviourData behaviorData, ArrayList<Piece> boardPieces, int remainingDistance) {
//        if (remainingDistance == 0) {
//
//            switch (this.getDirection()) {
//                // Successfully moved without collision
//                case UP,DOWN,LEFT, RIGHT -> {
//                    return true;
//                }
//            }
//
//            switch (this.getDirection()) {
//                case UP_LEFT,DOWN_LEFT -> this.setDirection(Direction.LEFT);
//                case UP_RIGHT,DOWN_RIGHT -> this.setDirection(Direction.RIGHT);
//            }
//
//            // Try alternative direction recursively
//            return tryMove(behaviorData, boardPieces, behaviorData.calculateDistance());
//        }
//
//
//
//
//        if (hasCollided) {
//
//            if (hasAlreadyCollidedOnPreviousAttempt){
//                return false;
//            }
//
//            hasAlreadyCollidedOnPreviousAttempt = true;
//
//            // Collision occurred, reset position and try alternative direction
//            virtualX = behaviorData.xStart;
//            virtualY = behaviorData.yStart;
//
//
//
//            // Try alternative direction recursively
//            return tryMove(behaviorData, boardPieces, behaviorData.calculateDistance());
//        }
//
//        // Continue with the next step of the movement
//        return tryMove(behaviorData, boardPieces, remainingDistance - 1);
//    }


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

}
