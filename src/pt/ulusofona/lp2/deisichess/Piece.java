package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public abstract class Piece {
    //region constants
    static final int BLACK_TEAM = 10;
    static final int WHITE_TEAM = 20;
    static final String PIECE_IS_CAPTURED = "capturado";
    static final String PIECE_IN_GAME = "em jogo";
    //endregion

    //region sub class fields
    protected int uniqueId;
    protected int type;
    protected int team;
    protected String nickName;
    protected String png;
    protected int movementRange;
    //endregion

    private int x;
    private int y;
    private String status = Piece.PIECE_IS_CAPTURED; // em jogo ou capturado

    public abstract boolean isValidMovement(ArrayList<Piece> boardPieces, int x, int y);
    public abstract boolean simulateBehaviour(ArrayList<Piece> boardPieces, int x, int y);
    //region getters
    public int getUniqueId() {
        return uniqueId;
    }

    public int getType() {
        return type;
    }

    public int getTeam() {
        return team;
    }

    public String getNickName() {
        return nickName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getStatus() {
        return status;
    }

    public String getPng() {
        return png;
    }
//endregion
    //region setters

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setStatus(String status) {
        this.status = status;
    }
//endregion

    public String[] infoToArray() {
        String[] properties = new String[7];
        properties[0] = String.valueOf(uniqueId);
        properties[1] = String.valueOf(type);
        properties[2] = String.valueOf(team);
        properties[3] = getNickName();
        properties[4] = getStatus();
        properties[5] = status.equals(Piece.PIECE_IS_CAPTURED) ? "" : String.valueOf(getX());
        properties[6] = status.equals(Piece.PIECE_IS_CAPTURED) ? "" : String.valueOf(getY());

        return properties;
    }

    public void kill() {
        this.setStatus(PIECE_IS_CAPTURED);
    }

    public void initiateIngame(int x, int y) {
        this.setStatus(Piece.PIECE_IN_GAME);
        this.setX(x);
        this.setY(y);
    }

    public void move(int x, int y) {
        setX(x);
        setY(y);
    }


    @Override
    public String toString() {
        var sb = new StringBuilder();

        sb.append(uniqueId).append(" | ")
                .append(type).append(" | ")
                .append(team).append(" | ")
                .append(nickName).append(" @ ");

        if (status.equals(Piece.PIECE_IS_CAPTURED)) {
            sb.append("(n/a)");
        } else {
            sb.append("(").append(x).append(", ").append(y).append(")");
        }

        return sb.toString();
    }

    protected boolean isXMovement(int x, int y) {
        return getX() != x;
    }

    protected boolean isYMovement(int x, int y) {
        return getY() != y;
    }

    private boolean isDifferentThanStartingPosition(int x, int y) {
        return getX() != x || getY() != y;
    }

    public boolean validateMovement(ArrayList<Piece> boardPieces, int x, int y) {
        return isDifferentThanStartingPosition(x, y) && isValidMovement(boardPieces, x, y);
    }

    protected boolean isValidOffset(int x, int y){
        var isValidXOffset = Math.abs(x - getX()) <= movementRange;
        var isValidYOffset = Math.abs(y - getY()) <= movementRange;
        return isValidXOffset && isValidYOffset;
    }


}
