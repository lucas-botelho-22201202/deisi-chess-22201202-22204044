package pt.ulusofona.lp2.deisichess;

public class Rei extends Piece {

    static final String BLACK_PIECE_PNG = "Grey_Pawn.png";
    static final String WHITE_PIECE_PNG = "Beige_Pawn.png";
    public Rei(int uniqueId, int type, int team, String nickName) {
        setUniqueId(uniqueId);
        setType(type);
        setTeam(team);
        setNickName(nickName);

        switch (team) {
            case Piece.BLACK_TEAM -> {
               setPng(Rei.BLACK_PIECE_PNG);
            }
            case Piece.WHITE_TEAM -> {
                setPng(Rei.WHITE_PIECE_PNG);
            }
        }
    }
}
