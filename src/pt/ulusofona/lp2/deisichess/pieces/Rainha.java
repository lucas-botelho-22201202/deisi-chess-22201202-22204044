package pt.ulusofona.lp2.deisichess.pieces;

import pt.ulusofona.lp2.deisichess.behaviour.*;

public class Rainha extends Piece {
    static final String BLACK_PNG = "Grey_Queen.png";
    static final String WHITE_PNG = "Beige_Queen.png";
    public static final String NAME = "Rainha";

    static final int DEFAULT_OFFSET = 5;

    public Rainha(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, Rainha.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = Rainha.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = Rainha.WHITE_PNG;
        }
        points = 8;

        addBehaviour(new BehaviourDiagonal());
        addBehaviour(new BehaviourHorizontal());
        addBehaviour(new BehaviourVertical());
    }

    @Override
    public void update(int roundNumber) {

    }

    @Override
    public boolean wantsToSubscribe() {
        return false;
    }

    @Override
    public boolean canEatSameType() {
        return false;
    }
}
