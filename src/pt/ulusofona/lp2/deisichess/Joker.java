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
            case Piece.BLACK_TEAM -> {
                setPng(Joker.BLACK_PIECE_PNG);
            }
            case Piece.WHITE_TEAM -> {
                setPng(Joker.WHITE_PIECE_PNG);
            }
        }
    }
}
