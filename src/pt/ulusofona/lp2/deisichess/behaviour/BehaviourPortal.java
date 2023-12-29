package pt.ulusofona.lp2.deisichess.behaviour;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.GameManager;
import pt.ulusofona.lp2.deisichess.pieces.Piece;

import java.util.ArrayList;

public class BehaviourPortal extends Behaviour {

    @Override
    public boolean hasCollision(BehaviourData behaviorData, ArrayList<Piece> boardPieces) {

        Piece originPiece = Board.getPieceAt(behaviorData.xStart, behaviorData.yStart, boardPieces);
        Piece destinationPiece = Board.getPieceAt(behaviorData.xEnd, behaviorData.yEnd, boardPieces);

        return (destinationPiece != null) && originPiece.getTeam() == destinationPiece.getTeam();
    }

    @Override
    public boolean isValid(BehaviourData behaviorData) {

        var isInitialSquareWhite = (behaviorData.yStart % 2 == 0 && behaviorData.xStart % 2 == 0) || (behaviorData.yStart % 2 != 0 && behaviorData.xStart % 2 != 0);
        var isDestinySquareWhite = (behaviorData.yEnd % 2 == 0 && behaviorData.xEnd % 2 == 0) || (behaviorData.yEnd % 2 != 0 && behaviorData.xEnd % 2 != 0);

        return  ((isInitialSquareWhite && isDestinySquareWhite) || (!isInitialSquareWhite && !isDestinySquareWhite))  &&
                isDifferentThanStartingPosition(behaviorData);
    }


    @Override
    public void calculateDirection(BehaviourData behaviorData) {

    }

    @Override
    public ArrayList<ArrayList<Integer>> forseeMovements(BehaviourData behaviorData, ArrayList<Piece> boardPieces, int range, int boardSize) {

        var possibleMovements = new ArrayList<ArrayList<Integer>>();

        setInitialVirtualPosition(behaviorData);

        for (int row = 0;row < boardSize; row++) {

            for (int column = 0; column < boardSize; column++) {

                if(Board.getPieceAt(row, column, boardPieces) == null){

                    if(isValid(behaviorData)){
                        var xyPair = new ArrayList<Integer>();
                        xyPair.add(virtualX);
                        xyPair.add(virtualY);
                        possibleMovements.add(xyPair);
                    }
                }
            }
        }

        return possibleMovements;
    }

}
