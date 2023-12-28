package pt.ulusofona.lp2.deisichess.behaviour;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.pieces.Piece;

import java.util.ArrayList;

public class BehaviourHorizontal extends Behaviour {

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


    @Override
    public ArrayList<ArrayList<Integer>> forseeMovements(BehaviourData behaviorData, ArrayList<Piece> boardPieces, int range, int boardSize) {

        var directions = new ArrayList<Direction>();
        directions.add(Direction.LEFT);
        directions.add(Direction.RIGHT);
        setDirection(Direction.LEFT);

        var possibleMovements = new ArrayList<ArrayList<Integer>>();
        for (Direction direction : directions) {

            setInitialVirtualPosition(behaviorData);

            for (int i = 1; i <= range; i++) {

                switch (this.getDirection()) {
                    case LEFT -> moveLeft();
                    case RIGHT -> moveRight();
                }

                if (Board.isValidCoordinate(virtualX, virtualY, boardSize)) {
                    var xyPair = new ArrayList<Integer>();
                    xyPair.add(virtualX);
                    xyPair.add(virtualY);
                    possibleMovements.add(xyPair);
                }
            }

            switch (direction) {
                case LEFT -> setDirection(Direction.RIGHT);
                case RIGHT -> setDirection(Direction.LEFT);
            }
        }

        return possibleMovements;
    }

    public static boolean isLeft(int xStart, int xEnd) {
        return xEnd < xStart;
    }
}
