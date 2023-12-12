package pt.ulusofona.lp2.deisichess.pieces;

import pt.ulusofona.lp2.deisichess.behaviour.BehaviourLShape;

public class PoneiMagico extends Piece {
    static final String BLACK_PNG = "PoneiGrey.png";
    static final String WHITE_PNG = "PoneiBeije.png";
    public  static final String NAME = "Ponei Mágico";

    static final int DEFAULT_OFFSET = 2;

    public PoneiMagico(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, PoneiMagico.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = PoneiMagico.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = PoneiMagico.WHITE_PNG;
        }
        points = 5;

        addBehaviour(new BehaviourLShape());
    }

    @Override
    public void update(int roundNumber) {

    }

    @Override
    public boolean wantsToSubscribe() {
        return false;
    }

}
