package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class PoneiMagico extends Piece {
    static final String BLACK_PNG = "PoneiGrey.png";
    static final String WHITE_PNG = "PoneiBeije.png";
    static final int DEFAULT_OFFSET = 2;

    public PoneiMagico(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, PoneiMagico.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = PoneiMagico.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = PoneiMagico.WHITE_PNG;
        }

        addBehaviour(new BehaviourLShape());
    }

    @Override
    public boolean isValidMove(ArrayList<Piece> boardPieces, int x, int y) {
        var behaviourData = new BehaviourData(getX(), getY(), x, y);
        Behaviour behaviour;

        try {
            behaviour = Behaviour.getValidMovementBehaviour(behaviourData, getBehaviours(), movementRange);
        } catch (InvalidBehaviourException e) {
            return false;
        }

        behaviour.calculateDirection(behaviourData);


        return !behaviour.hasCollision(behaviourData, boardPieces);
    }
}
