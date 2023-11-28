package pt.ulusofona.lp2.deisichess;

public class Joker extends Piece {
    static final String BLACK_PIECE_PNG = "Grey_Joker.png";
    static final String WHITE_PIECE_PNG = "Beige_Joker.png";

    public Joker(int uniqueId, int type, int team, String nickName) {
        setUniqueId(uniqueId);
        setType(type);
        setTeam(team);
        setNickName(nickName);

        switch (team) {
            case Team.BLACK_TEAM -> {
                setPng(Joker.BLACK_PIECE_PNG);
            }
            case Team.WHITE_TEAM -> {
                setPng(Joker.WHITE_PIECE_PNG);
            }
        }
    }

    @Override
    protected boolean isInvalidXMove(int x0, int x1) {
        return false;
    }

    @Override
    protected boolean isInvalidYMove(int y0, int y1) {
        return false;
    }

}
