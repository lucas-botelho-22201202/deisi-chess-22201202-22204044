package pt.ulusofona.lp2.deisichess;

public class Piece {

    static final int blackTeam = 0;
    static final int whiteTeam = 1;

    private int uniqueId;
    private int type;
    private int team;
    private String nickName;
    private String status; // em jogo ou capturado
    private int x;
    private int y;


    public Piece(int uniqueId, int type, int team, String nickName) {
        this.uniqueId = uniqueId;
        this.type = type;
        this.team = team;
        this.nickName = nickName;
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

    public String getStatus(){return status;}

    public String[] infoToArray() {
        String[] properties = new String[5];
        properties[0] = String.valueOf(uniqueId);
        properties[1] = String.valueOf(type);
        properties[2] = String.valueOf(team);
        properties[3] = getNickName();
        properties[4] = getStatus(); // GN
        properties[5] = String.valueOf(getX()); //GN
        properties[6] = String.valueOf(getY()); //GN
        // properties[4] = png;

        return properties;
    }

    @Override
    public String toString() {
        return uniqueId + " | " + type + " | " + team + " | " + nickName + " @ (" + x + ", " + y + ")";
    }
}
