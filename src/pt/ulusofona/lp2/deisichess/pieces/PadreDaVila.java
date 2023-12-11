package pt.ulusofona.lp2.deisichess.pieces;

import pt.ulusofona.lp2.deisichess.behaviour.Behaviour;
import pt.ulusofona.lp2.deisichess.behaviour.BehaviourData;
import pt.ulusofona.lp2.deisichess.behaviour.BehaviourDiagonal;
import pt.ulusofona.lp2.deisichess.InvalidBehaviourException;

import java.util.ArrayList;

public class PadreDaVila extends Piece {
    static final String BLACK_PNG = "BishopGrey.png";
    static final String WHITE_PNG = "BishopBeije.png";
    static final String NAME = "Padre da Vila";

    static final int DEFAULT_OFFSET = 3;

    public PadreDaVila(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, PadreDaVila.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = PadreDaVila.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = PadreDaVila.WHITE_PNG;
        }

        points = 3;
        addBehaviour(new BehaviourDiagonal());
    }

    @Override
    public void update(int roundNumber) {

    }

    @Override
    public boolean wantsToSubscribe() {
        return false;
    }
}
