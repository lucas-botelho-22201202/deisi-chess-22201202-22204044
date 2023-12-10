package pt.ulusofona.lp2.deisichess.pieces;

import pt.ulusofona.lp2.deisichess.InvalidBehaviourException;
import pt.ulusofona.lp2.deisichess.PieceFactory;
import pt.ulusofona.lp2.deisichess.behaviour.Behaviour;
import pt.ulusofona.lp2.deisichess.behaviour.BehaviourData;
import pt.ulusofona.lp2.deisichess.behaviour.BehaviourDiagonal;

import java.util.ArrayList;

public class Joker extends Piece {
    static final String BLACK_PNG = "JokerGrey.png";
    static final String WHITE_PNG = "JokerBeije.png";
    static final int DEFAULT_OFFSET = 0;

    private Piece impersonated;

    public Joker(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, Joker.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = Joker.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = Joker.WHITE_PNG;
        }

        points = 4;
    }

    private void impersonate(int roundNum){
        var pieceType = (roundNum % 6) + 1;
        impersonated = PieceFactory.createPiece(pieceType, getUniqueId(), getTeam(), getNickName());

        movementRange = impersonated.movementRange;
        resetBehaviours();
        for (Behaviour behaviour : impersonated.getBehaviours()) {
            addBehaviour(behaviour);
        }
    }

    @Override
    public void update(int roundNumber) {
        impersonate(roundNumber);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();

        String typeName = switch (impersonated.getType()) {
            case 1 -> "Rainha";
            case 2 -> "Ponei Magico";
            case 3 -> "Padre da Vila";
            case 4 -> "Torre Horizontal";
            case 5 -> "Torre Vertical";
            case 6 -> "Homer Simpson";
            default -> "";
        };

        sb.append(getUniqueId()).append(" | ")
                .append("Joker/").append(typeName).append(" | ")
                .append(points).append(" | ")
                .append(getTeam()).append(" | ")
                .append(getNickName()).append(" @ ");

        if (getStatus().equals(Piece.PIECE_IS_CAPTURED)) {
            sb.append("(n/a)");
        } else {
            sb.append("(").append(getX()).append(", ").append(getY()).append(")");
        }

        return sb.toString();
    }

    @Override
    public boolean wantsToSubscribe() {
        return true;
    }
}
