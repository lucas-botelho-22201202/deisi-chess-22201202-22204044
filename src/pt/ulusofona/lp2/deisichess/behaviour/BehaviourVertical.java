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

    @Override
    public ArrayList<ArrayList<Integer>> forseeMovements(BehaviourData behaviorData, ArrayList<Piece> boardPieces, int range, int boardSize) {

        var directions = new ArrayList<Direction>();
        directions.add(Direction.UP);
        directions.add(Direction.DOWN);
        setDirection(Direction.UP);

        var possibleMovements = new ArrayList<ArrayList<Integer>>();
        for (Direction direction : directions) {

            setInitialVirtualPosition(behaviorData);

            for (int i = 1; i <= range; i++) {

                switch (this.getDirection()) {
                    case UP -> moveUp();
                    case DOWN -> moveDown();
                }

                if (Board.isValidCoordinate(virtualX, virtualY, boardSize)) {
                    var xyPair = new ArrayList<Integer>();
                    xyPair.add(virtualX);
                    xyPair.add(virtualY);
                    possibleMovements.add(xyPair);
                }
            }

            switch (direction) {
                case UP -> setDirection(Direction.DOWN);
                case DOWN -> setDirection(Direction.UP);
            }
        }

        return possibleMovements;
    }

    public static boolean isUp(int yStart, int yEnd) {
        return yEnd < yStart;
    }

    public static boolean isDown(int yStart, int yEnd) {
        return yEnd < yStart;
    }

}
