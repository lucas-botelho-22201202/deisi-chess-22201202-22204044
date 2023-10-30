package pt.ulusofona.lp2.deisichess;

public class Piece {

    static final int BLACK_TEAM = 0;
    static final int WHITE_TEAM = 1;
    static final String BLACK_PIECE_NAME = "Grey_Pawn.png";
    static final String WHITE_PIECE_PNG = "Beige_Pawn.png";
    static final String PIECE_IS_CAPTURED = "capturado";
    static final String PIECE_IN_GAME = "em jogo";
    private int uniqueId;
    private int type;
    private int team;
    private String nickName;
    private String status = "em jogo"; // em jogo ou capturado
    private int x;
    private int y;
    private String png;


    public Piece(int uniqueId, int type, int team, String nickName) {
        this.uniqueId = uniqueId;
        this.type = type;
        this.team = team;
        this.nickName = nickName;

        switch (team) {
            case Piece.BLACK_TEAM -> {
                this.png = Piece.BLACK_PIECE_NAME;
            }
            case Piece.WHITE_TEAM -> {
                this.png = Piece.WHITE_PIECE_PNG;
            }
        }
    }

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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getPng() {
        return png;
    }

    public String[] infoToArray() {
        String[] properties = new String[7];
        properties[0] = String.valueOf(uniqueId);
        properties[1] = String.valueOf(type);
        properties[2] = String.valueOf(team);
        properties[3] = getNickName();
        properties[4] = getStatus();
        properties[5] = String.valueOf(getX());
        properties[6] = String.valueOf(getY());

        return properties;
    }

    public boolean isInvalidXMove(int x0, int x1) {
        return x0 - x1 > 1 || x1 - x0 > 1;
    }

    public boolean isInvalidYMove(int y0, int y1) {
        return y0 - y1 > 1 || y1 - y0 > 1;
    }

    public void killPiece() {
        this.x = -1;
        this.y = -1;
        this.setStatus("capturado");
    }


    @Override
    public String toString() {
        var sb = new StringBuilder();

        sb.append(uniqueId).append(" | ")
                .append(type).append(" | ")
                .append(team).append(" | ")
                .append(nickName).append(" @ ");

        if (status.equals(Piece.PIECE_IS_CAPTURED)){
            sb.append("(n/a)");
        }else{
            sb.append("(").append(x).append(", ").append(y).append(")");
        }

        return sb.toString();
    }
}
