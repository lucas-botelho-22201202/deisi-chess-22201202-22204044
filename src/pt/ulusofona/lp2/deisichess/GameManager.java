package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameManager {

    static final int NUM_OF_PIECE_PARAMETERS_ON_FILE = 4;
    static final int MAX_MOVS = 10;
    private int numMoves = 0;
    private Board board = new Board();
    private Statistic statistic = new Statistic();

    public GameManager() {
    }

    public void increaseNumMoves() {
        this.numMoves++;
    }

    public void resetNumMoves() {
        this.numMoves = -1;
    }

    public void loadGame(File file) throws InvalidGameInputException, IOException {
        if (file == null) {
            throw new IOException();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            board.setBoardSize(Integer.parseInt(reader.readLine()));
            board.setAmountOfPieces(Integer.parseInt(reader.readLine()));
            board.createPiecesFromFile(reader, board.getAmountOfPieces());

        } catch (InvalidGameInputException e) {
            throw new InvalidGameInputException();
        } catch (Exception e) {
            throw new IOException();
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

            destinationPiece.killPiece();
            resetNumMoves(); //resets to -1 instead of 0
            statistic.increaseCountCapture(getCurrentTeamID());
        }

        sourcePiece.moveTo(x1, y1);
        statistic.increaseCountValidMoves(getCurrentTeamID());
        board.switchPlayingTeam();
        this.increaseNumMoves();
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
        board.countHowManyPiecesAreInGameForEachTeam();

        var isDraw = board.getBlackTeamPiecesCount() == 1 && board.getWhiteTeamPiecesCount() == 1;
        if (isDraw) {
            statistic.setWinningTeam(-1);
            return true;
        }

        var blackTeamWon = board.getWhiteTeamPiecesCount() == 0 && board.getBlackTeamPiecesCount() > 0;
        if (blackTeamWon) {
            statistic.setWinningTeam(Piece.BLACK_TEAM);
            return true;
        }

        var whiteTeamWon = board.getBlackTeamPiecesCount() == 0 && board.getWhiteTeamPiecesCount() > 0;
        if (whiteTeamWon) {
            statistic.setWinningTeam(Piece.WHITE_TEAM);
            return true;
        }


        var maxMovesReached = this.numMoves == GameManager.MAX_MOVS;
        if (statistic.getNumTotalCaptures() > 0) {
            return maxMovesReached;
        }

        return false;
    }

    public ArrayList<String> getGameResults() {
        var statsLines = statistic.toString().split("\n");

        return new ArrayList<>(Arrays.asList(statsLines));
    }

    public JPanel getAuthorsPanel() {
        return new AuthorsPanelBuilder().GetCustomJPanel();
    }

    public void saveGame(File file) throws IOException {

    }

    public void undo() {
    }

    public java.util.List<Comparable> getHints(int x, int y) {
        return null;
    }

    public Map<String, String> customizeBoard() {
        return new HashMap<>();
    }
}
