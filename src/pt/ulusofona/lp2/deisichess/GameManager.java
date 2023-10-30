package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameManager {

    static final int NUM_OF_PIECE_PARAMETERS_FROM_FILE = 4;
    static final int MAX_MOVS = 10;
    public static int numMoves = 0;
    Board board = new Board();
    Statistic statistic = new Statistic();

    public GameManager() {
    }

    public boolean loadGame(File file) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            board.setBoardSize(Integer.parseInt(reader.readLine()));
            board.setAmountOfPieces(Integer.parseInt(reader.readLine()));

            if (!board.buildPiecesFromFile(reader, board.getAmountOfPieces())) {
                return false;
            }

            return board.buildBoardFromFile(reader);

        } catch (Exception e) {
            return false;
        }
    }

    public int getBoardSize() {
        return board.getBoardSize();
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        if (!board.isValidCoordinate(x0, y0) || !board.isValidCoordinate(x1, y1)) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var sourcePiece = board.getPieceAt(x0, y0);
        if (sourcePiece == null) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var isInvalidXMove = sourcePiece.isInvalidXMove(x0, x1);
        var isInvalidYMove = sourcePiece.isInvalidYMove(y0, y1);

        if (isInvalidXMove || isInvalidYMove) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var triedToMoveOtherTeamsPiece = sourcePiece.getTeam() != getCurrentTeamID();
        if (triedToMoveOtherTeamsPiece) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var destinationPiece = board.getPieceAt(x1, y1);
        if (destinationPiece != null) {
            var isSameTeam = sourcePiece.getTeam() == destinationPiece.getTeam();
            if (isSameTeam) {
                statistic.increaseCountInvalidMoves(getCurrentTeamID());
                return false;
            }

            board.eatPiece(destinationPiece);
            statistic.increaseCountCapture(getCurrentTeamID());
        }

        board.placePieceAt(sourcePiece, x1, y1);
        statistic.increaseCountValidMoves(getCurrentTeamID());
        board.switchPlayingTeam();
        return true;
    }

    public String[] getSquareInfo(int x, int y) {
        var piece = board.getPieceAt(x, y);
        if (piece != null) {
            return board.squareInfoToArray(piece);
        }

        return new String[0];
    }

    public String[] getPieceInfo(int ID) {
        var piece = board.getPiecesById(ID);

        return piece == null ? new String[0] : piece.infoToArray();
    }

    public String getPieceInfoAsString(int ID) {
        var piece = board.getPiecesById(ID);
        return piece == null ? "" : piece.toString();
    }

    public int getCurrentTeamID() {
        return board.getCurrentTeamId();
    }

    public boolean gameOver() {
        var blackTeamPiecesCount = 0;
        var whiteTeamPiecesCount = 0;

        for (Piece piece : board.getBoardPieces()) {
            if (piece.getTeam() == Piece.BLACK_TEAM) {
                blackTeamPiecesCount++;
            } else {
                whiteTeamPiecesCount++;
            }
        }

        var isDraw = blackTeamPiecesCount == 1 && whiteTeamPiecesCount == 1;
        if (isDraw) {
            statistic.setWinningTeam(-1);
            return true;
        }

        var blackTeamWon = whiteTeamPiecesCount == 0 && blackTeamPiecesCount > 0;
        if (blackTeamWon) {
            statistic.setWinningTeam(Piece.BLACK_TEAM);
            return true;
        }

        var whiteTeamWon = blackTeamPiecesCount == 0 && whiteTeamPiecesCount > 0;
        if (whiteTeamWon) {
            statistic.setWinningTeam(Piece.WHITE_TEAM);
            return true;
        }

        var maxMovesReached = GameManager.numMoves == GameManager.MAX_MOVS;
        return maxMovesReached;
    }

    public ArrayList<String> getGameResults() {
        var statsLines = statistic.toString().split("\n");

        return new ArrayList<>(Arrays.asList(statsLines));
    }

    public JPanel getAuthorsPanel() {
        // Implemente o código para obter o painel dos autores do jogo e retorne-o como um objeto JPanel.
        // Exemplo:
        return null; // Substitua null pelo painel real dos autores.
    }


}
