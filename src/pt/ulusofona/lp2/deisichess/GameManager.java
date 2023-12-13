package pt.ulusofona.lp2.deisichess;

import pt.ulusofona.lp2.deisichess.observer.Observer;
import pt.ulusofona.lp2.deisichess.observer.Subject;
import pt.ulusofona.lp2.deisichess.pieces.*;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameManager extends Subject {
    static final int NUM_OF_PIECE_PARAMETERS_ON_FILE = 4;
    static final int MAX_MOVS = 10;

    private List<Observer> observers = new ArrayList<>();
    private List<String> nameOfPiecesCaptured = new ArrayList<>();
    private int numMoves = 0;
    private int roundNum = 0;
    private Board board = new Board();
    private Statistic statistic = new Statistic();
    private Stack<GameState> gameStates = new Stack<>();
    public GameManager() {
    }

    public List<String> getNameOfPiecesCaptured() {
        return nameOfPiecesCaptured;
    }

    public Board getBoard() {
        return this.board;
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

            registerAllObservers(board.pieces());
            notifyObservers();

        } catch (InvalidGameInputException e) {
            throw new InvalidGameInputException(e);
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private void registerAllObservers(List<Piece> pieces) {
        for (Observer piece : pieces) {
            if (piece.wantsToSubscribe()) {
                registerObserver(piece);
            }
        }
    }

    public int getBoardSize() {
        return board.getBoardSize();
    }

    public boolean move(int x0, int y0, int x1, int y1) {

        gameStates.push(new GameState((Board) board.clone(), (Statistic) statistic.clone()));
        if (!Board.isValidCoordinate(x0, y0, getBoardSize()) || !Board.isValidCoordinate(x1, y1, getBoardSize())) {
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
            piecePlaying.increaseInvalidMoves();
            return false;
        }

        if (!piecePlaying.isValidMove(board.pieces(), x1, y1)) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            piecePlaying.increaseInvalidMoves();
            return false;
        }

        var pieceAtDestination = Board.getPieceAt(x1, y1, board.pieces());
        if (pieceAtDestination != null) {

            if (!isValidCapture(piecePlaying, pieceAtDestination)) {
                statistic.increaseCountInvalidMoves(getCurrentTeamID());
                piecePlaying.increaseInvalidMoves();
                return false;
            }

            piecePlaying.capture(pieceAtDestination);
            piecePlaying.increaseNumCaptures();
            resetNumMoves(); //resets to -1 instead of 0
            statistic.increaseCountCapture(getCurrentTeamID());
        }

        piecePlaying.move(x1, y1);
        statistic.increaseCountValidMoves(getCurrentTeamID());
        piecePlaying.increaseValidMoves();
        board.switchPlayingTeam();
        roundNum++;
        notifyObservers();
        return true;
    }

    private boolean isValidCapture(Piece piecePlaying, Piece pieceAtDestination) {

        var isFriendlyFire = piecePlaying.getTeam() == pieceAtDestination.getTeam();
        if (isFriendlyFire) {
            return false;
        }

        var isSameType = piecePlaying.getType() == pieceAtDestination.getType();
        if (isSameType){
            return pieceAtDestination.canEatSameType();
        }

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
        var piece = board.getPieceById(ID);
        return piece == null ? new String[0] : piece.infoToArray();
    }

    public String getPieceInfoAsString(int ID) {
        var piece = board.getPieceById(ID);
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
        return new AuthorsPanelBuilder().getCustomJPanel();
    }

    public String getPieceIDInEachSquare() {
        int boardSize = board.getBoardSize();
        String result = "";

        for (int row = 0; row < boardSize; row++) {
            String line = "";
            for (int column = 0; column < boardSize; column++) {
                String id = "0";
                if (getSquareInfo(column, row) != null && getSquareInfo(column, row).length > 0) {
                    id = getSquareInfo(column, row)[0];
                }

                if (column == boardSize - 1) {
                    line += id;
                } else {
                    line += id + ":";
                }
            }
            result += line + "\n";
        }
        return result;
    }

    public boolean isGameOver() {
        return statistic.getWinningTeam() != -1;
    }

    public void saveGame(File file) throws IOException {
        if (!isGameOver()) {
            int boardSize = board.getBoardSize();
            int numPieces = board.getAmountOfPieces();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(boardSize + "\n" + numPieces);
                writer.newLine();
                for (int i = 0; i < numPieces; i++) {
                    Piece piece = board.getPieceById(i + 1);
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
        if (!gameStates.empty()) {
            var previousState = gameStates.pop();

            if (previousState != null) {
                this.board = previousState.getBoard();
                this.statistic = previousState.getStatistic();
            }
        }
    }

    public java.util.List<Comparable> getHints(int x, int y) {
        var piece = Board.getPieceAt(x, y, board.pieces());

        var isValidPiece = piece != null && piece.getTeam() == getCurrentTeamID();
        if (isValidPiece) {
            var movements = piece.forseeMovements(board.pieces(), board.getBoardSize());

            var hintList = new ArrayList<Comparable>();

            for (ArrayList<Integer> position : movements) {

                if (piece.isValidMove(board.pieces(), position.get(0), position.get(1))) {
                    var pieceAtDestination = Board.getPieceAt(position.get(0), position.get(1), board.pieces());
                    if (pieceAtDestination != null) {
                        if (isValidCapture(piece, pieceAtDestination)) {
                            hintList.add(new Hint(pieceAtDestination.getPoints(), position.get(0), position.get(1)));
                        }
                    } else {
                        hintList.add(new Hint(position.get(0), position.get(1)));
                    }
                }
            }

            Collections.sort(hintList);

            return hintList;
        }

        return null;
    }

    public Map<String, String> customizeBoard() {
        return new HashMap<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(roundNum);
        }
    }
}
