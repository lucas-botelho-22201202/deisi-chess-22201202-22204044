package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Piece {
    static final String BLACK_PIECE_PNG = "Grey_Pawn.png";
    static final String WHITE_PIECE_PNG = "Beige_Pawn.png";
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
}
