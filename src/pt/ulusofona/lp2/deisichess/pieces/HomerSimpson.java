package pt.ulusofona.lp2.deisichess.pieces;

import pt.ulusofona.lp2.deisichess.behaviour.*;

import java.util.ArrayList;

public class HomerSimpson extends Piece {
    static final String BLACK_PNG = "HomerGrey.png";
    static final String BLACK_PNG_SLEEP = "HomerSleepGrey.png";
    static final String WHITE_PNG_SLEEP = "HomerSleepBeije.png";
    static final String WHITE_PNG = "HomerBeije.png";
    static final String SLEEP_TEXT = "Doh! zzzzzz";
    static final int DEFAULT_OFFSET = 1;
    static final String NAME = "Homer Simpson";

    private boolean isAwake = false;

    public HomerSimpson(int uniqueId, int type, int team, String nickName) {
        super(uniqueId, type, team, nickName, HomerSimpson.DEFAULT_OFFSET);

        switch (team) {
            case Piece.BLACK_TEAM -> this.png = HomerSimpson.BLACK_PNG;
            case Piece.WHITE_TEAM -> this.png = HomerSimpson.WHITE_PNG;
        }
        points = 2;

        addBehaviour(new BehaviourDiagonal());
        addBehaviour(new BehaviourHorizontal());
        addBehaviour(new BehaviourVertical());
    }

    @Override
    public void update(int roundNumber) {

        isAwake = roundNumber % 3 != 0;

        if (getTeam() == Piece.BLACK_TEAM){
            setPng(isAwake ? HomerSimpson.BLACK_PNG : HomerSimpson.BLACK_PNG_SLEEP);
        }
        else {
            setPng(isAwake ? HomerSimpson.WHITE_PNG : HomerSimpson.WHITE_PNG_SLEEP);
        }
    }

    @Override
    public boolean wantsToSubscribe() {
        return true;
    }

    @Override
    public String toString() {
        return isAwake ? super.toString() : HomerSimpson.SLEEP_TEXT;
    }

    @Override
    public ArrayList<ArrayList<Integer>> forseeMovements(ArrayList<Piece> boardPieces, int boardSize) {
        return isAwake ? super.forseeMovements(boardPieces, boardSize) : new ArrayList<>();
    }

    @Override
    public boolean isValidMove(ArrayList<Piece> boardPieces, int x, int y) {
        return isAwake && super.isValidMove(boardPieces, x, y);
    }

}
