package pt.ulusofona.lp2.deisichess;

public class PadreDaVila extends Piece {
    static final String BLACK_PIECE_PNG = "Grey_Bispo.png";
    static final String WHITE_PIECE_PNG = "Beige_Bispo.png";
    public PadreDaVila(int uniqueId, int type, int team, String nickName) {
        setUniqueId(uniqueId);
        setType(type);
        setTeam(team);
        setNickName(nickName);

        switch (team) {
            case Team.BLACK_TEAM -> {
                setPng(PadreDaVila.BLACK_PIECE_PNG);
            }
            case Team.WHITE_TEAM -> {
                setPng(PadreDaVila.WHITE_PIECE_PNG);
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
