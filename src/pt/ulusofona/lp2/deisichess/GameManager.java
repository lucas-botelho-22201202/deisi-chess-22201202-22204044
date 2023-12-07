package pt.ulusofona.lp2.deisichess;

import pt.ulusofona.lp2.deisichess.pieces.Piece;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameManager {
    static final int NUM_OF_PIECE_PARAMETERS_ON_FILE = 4;
    static final int MAX_MOVS = 10;
    private int numMoves = 0;
    private Board board = new Board();
    private Statistic statistic = new Statistic();
    private Stack<GameState> gameStates = new Stack<>();

    public GameManager() {
    }

    public Board getBoard() {
        return this.board;
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
            board.buildBoardFromFile(reader);

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

        gameStates.push(new GameState((Board) board.clone(), (Statistic) statistic.clone()));
        if (!board.isValidCoordinate(x0, y0) || !board.isValidCoordinate(x1, y1)) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var piecePlaying = Board.getPieceAt(x0, y0, board.pieces());
        if (piecePlaying == null) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var triedToMoveOtherTeamsPiece = piecePlaying.getTeam() != getCurrentTeamID();
        if (triedToMoveOtherTeamsPiece) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        if (!piecePlaying.isValidMove(board.pieces(), x1, y1)) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var pieceAtDestination = Board.getPieceAt(x1, y1, board.pieces());
        if (pieceAtDestination != null) {
            var isFriendlyFire = piecePlaying.getTeam() == pieceAtDestination.getTeam();
            if (isFriendlyFire) {
                statistic.increaseCountInvalidMoves(getCurrentTeamID());
                return false;
            }

            pieceAtDestination.kill();
            resetNumMoves(); //resets to -1 instead of 0
            statistic.increaseCountCapture(getCurrentTeamID());
        }

        piecePlaying.move(x1, y1);
        statistic.increaseCountValidMoves(getCurrentTeamID());
        board.switchPlayingTeam();
        this.increaseNumMoves();
        return true;
    }

    public String[] getSquareInfo(int x, int y) {
        var piece = Board.getPieceAt(x, y, board.pieces());
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
        var countBlackPiecesInGame = board.countPiecesIngame(Piece.BLACK_TEAM);
        var countWhitePiecesInGame = board.countPiecesIngame(Piece.WHITE_TEAM);

        var isDraw = countBlackPiecesInGame == 1 && countWhitePiecesInGame == 1;
        if (isDraw) {
            statistic.setWinningTeam(-1);
            return true;
        }

        var blackTeamWon = countWhitePiecesInGame == 0 && countBlackPiecesInGame > 0;
        if (blackTeamWon) {
            statistic.setWinningTeam(Piece.BLACK_TEAM);
            return true;
        }

        var whiteTeamWon = countBlackPiecesInGame == 0 && countWhitePiecesInGame > 0;
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

    public String getPieceIDInEachSquare(){
        int boardSize = board.getBoardSize();
        String result = "";

        for (int row = 0; row < boardSize; row++) {
            String line = "";
            for (int column = 0; column < boardSize; column++) {
                String id = "0";
                if(getSquareInfo(column, row) != null && getSquareInfo(column, row).length > 0){
                    id = getSquareInfo(column,row)[0];
                }

                if(column == boardSize-1){
                    line += id;
                }else{
                    line += id + ":";
                }
            }
            result += line +"\n";
        }
        return result;
    }

    public boolean isGameOver(){
        return statistic.getWinningTeam() != -1;
    }

    public void saveGame(File file) throws IOException {
        if (!isGameOver()){
            int boardSize = board.getBoardSize();
            int numPieces = board.getAmountOfPieces();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(boardSize + "\n" + numPieces);
                writer.newLine();
                for(int i = 0; i<numPieces;i++){
                    Piece piece = board.getPiecesById(i+1);
                    writer.write(piece.infoToFile() + "\n");
                }
                writer.write(getPieceIDInEachSquare());
                writer.write(getCurrentTeamID() + "\n");
                writer.write(statistic.statisticsToFile());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void undo() {
        var previousState = gameStates.pop();

        if (previousState != null) {
            this.board = previousState.getBoard();
            this.statistic = previousState.getStatistic();
        }
    }

    public java.util.List<Comparable> getHints(int x, int y) {
        return null;
    }

    public Map<String, String> customizeBoard() {
        return new HashMap<>();
    }
}
