package pt.ulusofona.lp2.deisichess;

public class TorreVertical extends Piece {
    static final String BLACK_PIECE_PNG = "Grey_VertTower.png";
    static final String WHITE_PIECE_PNG = "Beige_VertTower.png";
    public TorreVertical(int uniqueId, int type, int team, String nickName) {
        setUniqueId(uniqueId);
        setType(type);
        setTeam(team);
        setNickName(nickName);

        switch (team) {
            case Piece.BLACK_TEAM -> {
                setPng(TorreVertical.BLACK_PIECE_PNG);
            }
            case Piece.WHITE_TEAM -> {
                setPng(TorreVertical.WHITE_PIECE_PNG);
            }
        }
    }
}
