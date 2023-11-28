package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Team {

    static final int BLACK_TEAM = 10;
    static final int WHITE_TEAM = 20;

    private ArrayList<Piece> boardPieces = new ArrayList<>();

    public ArrayList<Piece> getPieces() {
        return boardPieces;
    }

    public void addPiece(Piece piece) {
        boardPieces.add(piece);
    }
}
