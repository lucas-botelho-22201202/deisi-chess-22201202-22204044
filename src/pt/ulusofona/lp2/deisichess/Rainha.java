package pt.ulusofona.lp2.deisichess;

public class Rainha extends Piece{
    static final String BLACK_PIECE_PNG = "Grey_Queen.png";
    static final String WHITE_PIECE_PNG = "Beige_Queen.png";
    public Rainha(int uniqueId, int type, int team, String nickName) {
        setUniqueId(uniqueId);
        setType(type);
        setTeam(team);
        setNickName(nickName);

        switch (team) {
            case Team.BLACK_TEAM -> {
                setPng(Rainha.BLACK_PIECE_PNG);
            }
            case Team.WHITE_TEAM -> {
                setPng(Rainha.WHITE_PIECE_PNG);
            }
        }
    }

    @Override
    protected boolean isInvalidXMove(int x0, int x1) {
        return x0 - x1 > 5 || x1 - x0 > 5;
    }

    @Override
    protected boolean isInvalidYMove(int y0, int y1) {
        return y0 - y1 > 5 || y1 - y0 > 5;
    }
}
