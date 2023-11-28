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

            board.setSize(Integer.parseInt(reader.readLine()));
            var totalPiecesFromFile = Integer.parseInt(reader.readLine());
            board.createPiecesFromFile(reader, totalPiecesFromFile);
            board.buildBoardFromFile(reader);

        } catch (InvalidGameInputException e) {
            throw new InvalidGameInputException();
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public int getBoardSize() {
        return board.getSize();
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        if (!board.isValidCoordinate(x0, y0) || !board.isValidCoordinate(x1, y1)) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var piecePlaying = board.getPieceAt(x0, y0);
        if (piecePlaying == null) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var triedToMoveOtherTeamsPiece = piecePlaying.getTeam() != getCurrentTeamID();
        if (triedToMoveOtherTeamsPiece) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var pieceAtDestination = board.getPieceAt(x1, y1);
        if (pieceAtDestination != null) {
            var isSameTeam = piecePlaying.getTeam() == pieceAtDestination.getTeam();
            if (isSameTeam) {
                statistic.increaseCountInvalidMoves(getCurrentTeamID());
                return false;
            }

            pieceAtDestination.kill();
            resetNumMoves(); //resets to -1 instead of 0
            statistic.increaseCountCapture(getCurrentTeamID());
        }

        try {
            piecePlaying.tryMoveTo(x1, y1);
        } catch (InvalidMoveException e) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

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
        var isDraw = board.countBlackTeamPieces() == 1 && board.countWhiteTeamPieces() == 1;
        if (isDraw) {
            statistic.setWinningTeam(-1);
            return true;
        }

        var blackTeamWon = board.countWhiteTeamPieces() == 0 && board.countBlackTeamPieces() > 0;
        if (blackTeamWon) {
            statistic.setWinningTeam(Team.BLACK_TEAM);
            return true;
        }

        var whiteTeamWon = board.countBlackTeamPieces() == 0 && board.countWhiteTeamPieces() > 0;
        if (whiteTeamWon) {
            statistic.setWinningTeam(Team.WHITE_TEAM);
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
