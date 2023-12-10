package pt.ulusofona.lp2.deisichess.pieces;

import pt.ulusofona.lp2.deisichess.behaviour.*;
import pt.ulusofona.lp2.deisichess.InvalidBehaviourException;

import java.util.ArrayList;

public class Rainha extends Piece {
    static final String BLACK_PNG = "Grey_Queen.png";
    static final String WHITE_PNG = "Beige_Queen.png";
    static final int DEFAULT_OFFSET = 5;

    public Rainha(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, Rainha.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = Rainha.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = Rainha.WHITE_PNG;
        }
        points = 4;

        addBehaviour(new BehaviourDiagonal());
        addBehaviour(new BehaviourHorizontal());
        addBehaviour(new BehaviourVertical());
    }

//    @Override
//    public boolean isValidMove(ArrayList<Piece> boardPieces, int x, int y) {
//        var behaviourData = new BehaviourData(getX(), getY(), x, y);
//        Behaviour behaviour;
//
//        try {
//            behaviour = Behaviour.getValidMovementBehaviour(behaviourData, getBehaviours(), movementRange);
//        } catch (InvalidBehaviourException e) {
//            return false;
//        }
//
//        behaviour.calculateDirection(behaviourData);
//
//
//        return !behaviour.hasCollision(behaviourData, boardPieces);
//    }

    @Override
    public void update(int roundNumber) {

    }

    @Override
    public boolean wantsToSubscribe() {
        return false;
    }
}
