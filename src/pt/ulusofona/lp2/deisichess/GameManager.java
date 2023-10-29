package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class GameManager {

    static final int NUM_OF_PIECE_PARAMETERS_FROM_FILE = 4;
    static final int MAX_MOVS = 10;
    public static int numMoves = 0;
    Board board;
    private int currentTeamId = 0;


    public GameManager() {
        this.board = new Board();
    }

    public boolean loadGame(File file) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            if (!board.setBoardSizeFromString(reader.readLine())) {
                return false;
            }

            if (!board.setAmountOfPiecesFromString(reader.readLine())) {
                return false;
            }

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
            return false;
        }

        var sourcePiece = board.getPieceAt(x0, y0);
        if (sourcePiece == null) {
            return false;
        }

        var destinationPiece = board.getPieceAt(x1, y1);
        if (destinationPiece != null) {

            var isSameTeam = sourcePiece.getTeam() == destinationPiece.getTeam();
            if (isSameTeam) {
                return false;
            }

            board.eatPiece(destinationPiece);
        }

        board.placePieceAt(sourcePiece, x1, y1);

        return true;
    }

    public String[] getSquareInfo(int x, int y) {
        var piece = board.getPieceAt(x, y);
        if(piece != null){
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
        return this.currentTeamId;
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

        var blackTeamLost = blackTeamPiecesCount == 0 && whiteTeamPiecesCount > 0;
        var whiteTeamLost = whiteTeamPiecesCount == 0 && blackTeamPiecesCount > 0;
        var isDraw = blackTeamPiecesCount == 1 && whiteTeamPiecesCount == 1;
        var maxMovesReached = GameManager.numMoves == GameManager.MAX_MOVS;

        return blackTeamLost || whiteTeamLost || isDraw || maxMovesReached;
    }

    public ArrayList<String> getGameResults() {
        // Implemente o código para obter os resultados do jogo e retorne-os como uma ArrayList de Strings.
        // Exemplo:
        return new ArrayList<>(); // Substitua new ArrayList<>() pelos resultados reais.
    }

    public JPanel getAuthorsPanel() {
        // Implemente o código para obter o painel dos autores do jogo e retorne-o como um objeto JPanel.
        // Exemplo:
        return null; // Substitua null pelo painel real dos autores.
    }


}
