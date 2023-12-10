package pt.ulusofona.lp2.deisichess.pieces;

import pt.ulusofona.lp2.deisichess.behaviour.*;
import pt.ulusofona.lp2.deisichess.InvalidBehaviourException;

import java.util.ArrayList;

public class Rei extends Piece {
    static final String BLACK_PNG = "KingGrey.png";
    static final String WHITE_PNG = "KingBeije.png";
    static final int DEFAULT_OFFSET = 1;

    public Rei(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, Rei.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = Rei.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = Rei.WHITE_PNG;
        }
        points = 1000;

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
