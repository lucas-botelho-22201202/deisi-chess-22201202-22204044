package pt.ulusofona.lp2.deisichess.pieces;

import pt.ulusofona.lp2.deisichess.behaviour.*;

public class Teleporter extends Piece {
    static final String BLACK_PNG = ""; // inserir imagem
    static final String WHITE_PNG = ""; // inserir imagem
    static final int DEFAULT_OFFSET = -1;
    public static final String NAME = "Teleporter";


    public Teleporter(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, Teleporter.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = Teleporter.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = Teleporter.WHITE_PNG;
        }
        points = 20;

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

}
