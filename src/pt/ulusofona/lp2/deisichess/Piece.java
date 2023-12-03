package pt.ulusofona.lp2.deisichess;

public abstract class Piece {
    static final String PIECE_IS_CAPTURED = "capturado";
    static final String PIECE_IN_GAME = "em jogo";
    private int uniqueId;
    private int type;
    private int team;
    private String nickName;
    private String status = Piece.PIECE_IS_CAPTURED; // em jogo ou capturado
    private int x = -1;
    private int y = -1;
    private String png;

    protected abstract boolean isInvalidXMove(int x0, int x1);
    protected abstract boolean isInvalidYMove(int y0, int y1);

    protected void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    protected void setType(int type) {
        this.type = type;
    }

    protected void setTeam(int team) {
        this.team = team;
    }

    protected void setNickName(String nickName) {
        this.nickName = nickName;
    }

    protected void setPng(String png) {
        this.png = png;
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
        properties[5] = status.equals(Piece.PIECE_IS_CAPTURED) ? "" : String.valueOf(getX());
        properties[6] = status.equals(Piece.PIECE_IS_CAPTURED) ? "" : String.valueOf(getY());

        return properties;
    }

    public void kill() {
        this.x = -1;
        this.y = -1;
        this.setStatus(PIECE_IS_CAPTURED);
    }

    public void setPieceInBoard(int x, int y) {
        this.setStatus(Piece.PIECE_IN_GAME);
        this.setX(x);
        this.setY(y);
    }

    public void tryMoveTo(int targetX, int targetY) throws InvalidMoveException {
        if (isInvalidXMove(getX(), targetX) || isInvalidYMove(getY(), targetY)) {
            throw new InvalidMoveException();
        }

        this.setX(targetX);
        this.setY(targetY);
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
}
