package pt.ulusofona.lp2.deisichess.pieces;

import pt.ulusofona.lp2.deisichess.behaviour.*;

public class Portal extends Piece {
    static final String BLACK_PNG = "VertTowerGrey.png";
    static final String WHITE_PNG = "VertTowerBeije.png";
    static final int DEFAULT_OFFSET = 1000;
    public static final String NAME = "Portal";


    public Portal(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, Portal.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = Portal.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = Portal.WHITE_PNG;
        }
        points = 20;

        addBehaviour(new BehaviourPortal());
    }

    @Override
    public void update(int roundNumber) {

    }

    @Override
    public boolean wantsToSubscribe() {
        return false;
    }

}
