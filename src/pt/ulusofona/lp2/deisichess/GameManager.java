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
    private int numMovesWithoutCapture = 0;
    private int roundNum = 0;
    private Board board = new Board();
    private Statistic statistic = new Statistic();
    private Stack<GameState> gameStates = new Stack<>();

    public GameManager() {
    }

    public GameManager(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return this.board;
    }

    public void resetNumMovesWithoutCapture() {
        this.numMovesWithoutCapture = -1;
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
            if (reader.ready()) {
                board.setCurrentTeamId(Integer.parseInt(reader.readLine()));
                statistic.loadStatistics(reader);
                board.setPiecesStatusFromFile(reader);
            } else {
                board.setCurrentTeamId(Piece.BLACK_TEAM);
                statistic.resetStatistics();
            }

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
            resetNumMovesWithoutCapture();
            statistic.increaseCountCapture(getCurrentTeamID());
        }

        piecePlaying.move(x1, y1);
        statistic.increaseCountValidMoves(getCurrentTeamID());
        piecePlaying.increaseValidMoves();
        board.switchPlayingTeam();
        roundNum++;
        numMovesWithoutCapture++;
        notifyObservers();
        return true;
    }

    private boolean isValidCapture(Piece piecePlaying, Piece pieceAtDestination) {

        var isFriendlyFire = piecePlaying.getTeam() == pieceAtDestination.getTeam();
        if (isFriendlyFire) {
            return false;
        }

        var isSameType = piecePlaying.getType() == pieceAtDestination.getType();
        if (isSameType) {
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

        var isBlackKingInGame = board.isKingInGame(Piece.BLACK_TEAM);
        var isWhiteKingInGame = board.isKingInGame(Piece.WHITE_TEAM);

        var isDraw = countBlackPiecesInGame == 1 && countWhitePiecesInGame == 1 && isBlackKingInGame && isWhiteKingInGame;
        if (isDraw || countBlackPiecesInGame == 1 && countWhitePiecesInGame == 1) {
            statistic.setWinningTeam(-1);
            return true;
        }

        var blackTeamWon = (countWhitePiecesInGame == 0 && countBlackPiecesInGame > 0) || (isBlackKingInGame && !isWhiteKingInGame);
        if (blackTeamWon) {
            statistic.setWinningTeam(Piece.BLACK_TEAM);
            return true;
        }

        var whiteTeamWon = (countBlackPiecesInGame == 0 && countWhitePiecesInGame > 0) || (!isBlackKingInGame && isWhiteKingInGame);
        if (whiteTeamWon) {
            statistic.setWinningTeam(Piece.WHITE_TEAM);
            return true;
        }

        return this.numMovesWithoutCapture == GameManager.MAX_MOVS;
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
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < boardSize; row++) {
            StringBuilder line = new StringBuilder();
            for (int column = 0; column < boardSize; column++) {
                String[] squareInfo = getSquareInfo(column, row);
                String id = (squareInfo != null && squareInfo.length > 0) ? squareInfo[0] : "0";

                line.append(id);
                if (column < boardSize - 1) {
                    line.append(":");
                }
            }
            result.append(line).append("\n");
        }
        return result.toString();
    }

    public void saveGame(File file) throws IOException {
        int boardSize = board.getBoardSize();
        int numPieces = board.getAmountOfPieces();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(boardSize + "\n" + numPieces);
            writer.newLine();
            writePiecesInfoToFile(writer);
            writer.write(getPieceIDInEachSquare());
            writeTeamIdToFile(writer);
            writeStatisticsToFile(writer);
            writer.newLine();
            writeIdCapturedPieces(writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeStatisticsToFile(BufferedWriter writer) throws IOException {
        writer.write(statistic.statisticsToFile());
    }

    private void writeIdCapturedPieces(BufferedWriter writer) throws IOException {
        for (Piece piece : board.pieces()) {
            if (piece.getStatus().equals(Piece.PIECE_IS_CAPTURED)) {
                writer.write(piece.getUniqueId() + "\n");
            }
        }
    }

    private void writeTeamIdToFile(BufferedWriter writer) throws IOException {
        writer.write(getCurrentTeamID() + "\n");
    }

    private void writePiecesInfoToFile(BufferedWriter writer) throws IOException {
        for (Piece piece : board.pieces()) {
            writer.write(piece.infoToFile() + "\n");
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GameManager otherGameManager = (GameManager) obj;
//        return numMovesWithoutCapture == otherGameManager.numMovesWithoutCapture &&
//                roundNum == otherGameManager.roundNum &&
//                Objects.equals(gameStates, otherGameManager.gameStates);
        return Objects.equals(board, otherGameManager.board) &&
                Objects.equals(statistic, otherGameManager.statistic);

    }
}
