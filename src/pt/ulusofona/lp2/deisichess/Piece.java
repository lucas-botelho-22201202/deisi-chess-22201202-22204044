package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public abstract class Piece {
    //region constants
    static final int BLACK_TEAM = 10;
    static final int WHITE_TEAM = 20;
    static final String PIECE_IS_CAPTURED = "capturado";
    static final String PIECE_IN_GAME = "em jogo";
    //endregion

    //region fields
    private int uniqueId;
    private int type;
    private int team;
    private String nickName;
    private int x;
    private int y;
    private String status = Piece.PIECE_IS_CAPTURED; // em jogo ou capturado
    protected String png;
    protected int movementRange;
    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    //endregion

    public Piece(int uniqueId, int type, int team, String nickName, int movementRange) {
        this.uniqueId = uniqueId;
        this.type = type;
        this.team = team;
        this.nickName = nickName;
        this.movementRange = movementRange;
    }

    public abstract boolean isValidMove(ArrayList<Piece> boardPieces, int x, int y);


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

    public String infoToFile(){
        String pieceID = String.valueOf(uniqueId);
        String pieceType = String.valueOf(type);
        String pieceTeam = String.valueOf(team);
        String pieceNickName = getNickName();
        int x = getX();
        int y = getY();

        String result = pieceID + ":" + pieceType + ":" + pieceTeam + ":" + pieceNickName + ": (" + x + ", " + y + ")";

        return result;
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


    public void moveX(int distance) {
        this.x += distance;
    }

    public void moveY(int distance) {
        this.y += distance;
    }

    protected void addBehaviour(Behaviour behaviour) {
        behaviours.add(behaviour);
    }

    public ArrayList<Behaviour> getBehaviours() {
        return behaviours;
    }

}
