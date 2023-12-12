package pt.ulusofona.lp2.deisichess.pieces;

import pt.ulusofona.lp2.deisichess.behaviour.*;
import pt.ulusofona.lp2.deisichess.InvalidBehaviourException;

import java.util.ArrayList;

public class Rei extends Piece {
    static final String BLACK_PNG = "KingGrey.png";
    static final String WHITE_PNG = "KingBeije.png";
    static final int DEFAULT_OFFSET = 1;
    static final String NAME = "Rei";


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

    @Override
    public void update(int roundNumber) {

    }

    @Override
    public boolean wantsToSubscribe() {
        return false;
    }

    @Override
    public boolean canEatSameType() {
        return true;
    }
}
