package pt.ulusofona.lp2.deisichess;

public class Rei extends Piece {

    static final String BLACK_PIECE_PNG = "Grey_King.png";
    static final String WHITE_PIECE_PNG = "Beige_King.png";

    public Rei(int uniqueId, int type, int team, String nickName) {
        setUniqueId(uniqueId);
        setType(type);
        setTeam(team);
        setNickName(nickName);

        switch (team) {
            case Team.BLACK_TEAM -> {
                setPng(Rei.BLACK_PIECE_PNG);
            }
            case Team.WHITE_TEAM -> {
                setPng(Rei.WHITE_PIECE_PNG);
            }
        }
    }

    @Override
    protected boolean isInvalidXMove(int x0, int x1) {
        return x0 - x1 > 1 || x1 - x0 > 1;
    }

    @Override
    protected boolean isInvalidYMove(int y0, int y1) {
        return y0 - y1 > 1 || y1 - y0 > 1;
    }
}
