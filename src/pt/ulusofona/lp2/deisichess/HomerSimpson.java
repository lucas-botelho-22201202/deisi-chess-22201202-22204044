package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Piece {
    static final String BLACK_PIECE_PNG = "Grey_Homer.png";
    static final String WHITE_PIECE_PNG = "Beige_Homer.png";
    static final String SLEEP_BLACK_PIECE_PNG = "Grey_HomerSleep.png";
    static final String SLEEP_WHITE_PIECE_PNG = "Beige_HomerSleep.png";
    public HomerSimpson(int uniqueId, int type, int team, String nickName) {
        setUniqueId(uniqueId);
        setType(type);
        setTeam(team);
        setNickName(nickName);

        switch (team) {
            case Piece.BLACK_TEAM -> {
                setPng(HomerSimpson.BLACK_PIECE_PNG);
            }
            case Piece.WHITE_TEAM -> {
                setPng(HomerSimpson.WHITE_PIECE_PNG);
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
