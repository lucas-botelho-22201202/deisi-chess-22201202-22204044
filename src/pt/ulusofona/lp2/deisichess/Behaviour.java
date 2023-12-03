package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public abstract class Behaviour {

    private Direction direction;

    protected int virtualX;
    protected int virtualY;

    public abstract boolean isDiagonal(BehaviourData behaviorData);

    public abstract boolean isHorizontal(BehaviourData behaviorData);

    public abstract boolean isVertical(BehaviourData behaviorData);

    public abstract void simulateBehaviour(BehaviourData behaviorData, ArrayList<Piece> boardPieces);

    public abstract boolean isValid(BehaviourData behaviorData);

    public abstract void calculateDirection(BehaviourData behaviorData);

    public boolean hasCollision(BehaviourData behaviorData, ArrayList<Piece> boardPieces){
        virtualX = behaviorData.xStart;
        virtualY = behaviorData.yStart;

        for (int i = 0; i < behaviorData.calculateDistance(); i++) {

            simulateBehaviour(behaviorData, boardPieces);

            var hasCollided = Board.getPieceAt(virtualX, virtualY, boardPieces) != null;

            if (hasCollided){
                return true;
            }
        }

        return false;
    }

    protected boolean isDifferentThanStartingPosition(BehaviourData behaviorData) {
        return behaviorData.xStart != behaviorData.xEnd || behaviorData.yStart != behaviorData.yEnd;
    }

    public boolean isInMovementRange(BehaviourData behaviorData, int movementRange) {
        var isXMovementShorterThanMaximumRange = Math.abs(behaviorData.xEnd - behaviorData.xStart) <= movementRange;
        var isYMovementShorterThanMaximumRange = Math.abs(behaviorData.yEnd - behaviorData.yStart) <= movementRange;
        return isXMovementShorterThanMaximumRange && isYMovementShorterThanMaximumRange;
    }

    public static Behaviour getValidMovementBehaviour(BehaviourData behaviourData, ArrayList<Behaviour> behaviours, int movementRange) throws InvalidBehaviourException {
        for (Behaviour behaviour : behaviours) {
            var isValidBehavirour = behaviour.isValid(behaviourData);
            var isValidRangeOfMovement = behaviour.isInMovementRange(behaviourData, movementRange);
            if (isValidBehavirour && isValidRangeOfMovement) {
                return behaviour;
            }
        }

        throw new InvalidBehaviourException();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    protected void moveUp() {
        virtualY -= 1;
    }

    protected void moveDown() {
        virtualY += 1;
    }

    protected void moveLeft() {
        virtualX -= 1;
    }

    protected void moveRight() {
        virtualX += 1;
    }

}
