package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Piece {
    static final String BLACK_PIECE_PNG = "Grey_Ponei.png";
    static final String WHITE_PIECE_PNG = "Beige_Ponei.png";
    public PoneiMagico(int uniqueId, int type, int team, String nickName) {
        setUniqueId(uniqueId);
        setType(type);
        setTeam(team);
        setNickName(nickName);

        switch (team) {
            case Piece.BLACK_TEAM -> {
                setPng(PoneiMagico.BLACK_PIECE_PNG);
            }
            case Piece.WHITE_TEAM -> {
                setPng(PoneiMagico.WHITE_PIECE_PNG);
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
