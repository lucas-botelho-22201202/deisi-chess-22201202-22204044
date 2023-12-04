package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class TorreVertical extends Piece {
    static final String BLACK_PNG = "VertTowerGrey.png";
    static final String WHITE_PNG = "VertTowerBeije.png";
    static final int DEFAULT_OFFSET = 1000;

    public TorreVertical(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, TorreVertical.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = TorreVertical.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = TorreVertical.WHITE_PNG;
        }

        addBehaviour(new BehaviourVertical());
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
