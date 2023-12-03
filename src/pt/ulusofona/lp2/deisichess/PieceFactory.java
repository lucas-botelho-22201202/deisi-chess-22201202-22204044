package pt.ulusofona.lp2.deisichess;

public class PieceFactory {
    public PieceFactory() {
    }

    public static Piece CreatePiece(String pieceLine) throws InvalidGameInputException{
        var lineElements = pieceLine.split(":");
        var isPieceFileLine = lineElements.length == GameManager.NUM_OF_PIECE_PARAMETERS_ON_FILE;

        if (!isPieceFileLine) {
            throw new InvalidGameInputException();
        }

        var uId = Integer.parseInt(lineElements[0]);
        var type = Integer.parseInt(lineElements[1]);
        var team = Integer.parseInt(lineElements[2]);
        var nickName = lineElements[3];

        return switch (type) {
//            case 0 -> new Rei(uId, type, team, nickName);
            case 1 -> new Rainha(uId, type, team, nickName);
//            case 2 -> new PoneiMagico(uId, type, team, nickName);
//            case 3 -> new PadreDaVila(uId, type, team, nickName);
//            case 4 -> new TorreHorizontal(uId, type, team, nickName);
//            case 5 -> new TorreVertical(uId, type, team, nickName);
//            case 6 -> new HomerSimpson(uId, type, team, nickName);
//            case 7 -> new Joker(uId, type, team, nickName);
//            default -> throw new InvalidGameInputException(); todo change back to this instead of line below
            default -> new Rainha(uId, type, team, nickName);
        };

    }
}
