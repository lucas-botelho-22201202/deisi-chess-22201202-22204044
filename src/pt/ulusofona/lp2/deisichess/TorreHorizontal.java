package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class TorreHorizontal extends Piece {
    static final String BLACK_PNG = "HorTowerGrey.png";
    static final String WHITE_PNG = "HorTowerBeije.png";
    static final int DEFAULT_OFFSET = 1000;

    public TorreHorizontal(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, TorreHorizontal.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = TorreHorizontal.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = TorreHorizontal.WHITE_PNG;
        }

        addBehaviour(new BehaviourHorizontal());
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
