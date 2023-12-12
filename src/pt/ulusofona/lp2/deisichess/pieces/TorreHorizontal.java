package pt.ulusofona.lp2.deisichess.pieces;

import pt.ulusofona.lp2.deisichess.behaviour.Behaviour;
import pt.ulusofona.lp2.deisichess.behaviour.BehaviourData;
import pt.ulusofona.lp2.deisichess.behaviour.BehaviourHorizontal;
import pt.ulusofona.lp2.deisichess.InvalidBehaviourException;

import java.util.ArrayList;

public class TorreHorizontal extends Piece {
    static final String BLACK_PNG = "HorTowerGrey.png";
    static final String WHITE_PNG = "HorTowerBeije.png";
    public static final String NAME = "TorreHor";

    static final int DEFAULT_OFFSET = 1000;

    public TorreHorizontal(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, TorreHorizontal.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = TorreHorizontal.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = TorreHorizontal.WHITE_PNG;
        }
        points = 3;

        addBehaviour(new BehaviourHorizontal());
    }

    @Override
    public void update(int roundNumber) {

    }

    @Override
    public boolean wantsToSubscribe() {
        return false;
    }


}
