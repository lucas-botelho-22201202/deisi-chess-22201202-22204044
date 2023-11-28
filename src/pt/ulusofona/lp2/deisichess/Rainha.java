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
            case Piece.BLACK_TEAM -> {
                setPng(Rainha.BLACK_PIECE_PNG);
            }
            case Piece.WHITE_TEAM -> {
                setPng(Rainha.WHITE_PIECE_PNG);
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

    @Override
    public void tryMoveTo(int x, int y) throws InvalidMoveException {

    }
}
