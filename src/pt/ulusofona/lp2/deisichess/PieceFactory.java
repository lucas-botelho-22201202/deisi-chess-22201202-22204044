package pt.ulusofona.lp2.deisichess;

import pt.ulusofona.lp2.deisichess.pieces.*;

import java.io.IOException;

public class PieceFactory {

    public static Piece createPieceFromFileLine(String pieceLine) throws InvalidGameInputException, IOException {
        var lineElements = pieceLine.split(":");
        var isCorrectPieceLine = lineElements.length == GameManager.NUM_OF_PIECE_PARAMETERS_ON_FILE;

        if (!isCorrectPieceLine) {
            var problem = lineElements.length > GameManager.NUM_OF_PIECE_PARAMETERS_ON_FILE
                    ? InvalidGameInputException.DADOS_A_MAIS
                    : InvalidGameInputException.DADOS_A_MENOS;

            throw new InvalidGameInputException(
                    problem,
                    lineElements.length,
                    InvalidGameInputException.DADOS_ESPERADOS_PECA);
        }

        var uId = Integer.parseInt(lineElements[0]);
        var type = Integer.parseInt(lineElements[1]);
        var team = Integer.parseInt(lineElements[2]);

        var isInvalidTeam = team != Piece.BLACK_TEAM && team !=  Piece.WHITE_TEAM;
        if (isInvalidTeam){
            throw new IOException();
        }

        var nickName = lineElements[3];

        return createPiece(type, uId, team, nickName);

    }

    public static Piece createPiece(int type, int uId, int team, String nickName) {
       return switch (type) {
            case 0 -> new Rei(uId, type, team, nickName);
            case 1 -> new Rainha(uId, type, team, nickName);
            case 2 -> new PoneiMagico(uId, type, team, nickName);
            case 3 -> new PadreDaVila(uId, type, team, nickName);
            case 4 -> new TorreHorizontal(uId, type, team, nickName);
            case 5 -> new TorreVertical(uId, type, team, nickName);
//            case 6 -> new HomerSimpson(uId, type, team, nickName);
            case 7 -> new Joker(uId, type, team, nickName);
//            default -> throw new IOException();
            default -> new Rainha(uId, type, team, nickName);

        };
    }
}
