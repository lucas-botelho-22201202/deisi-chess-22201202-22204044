package pt.ulusofona.lp2.deisichess;

import java.io.BufferedReader;
import java.util.ArrayList;

public class Board {
    private int boardSize;
    private int amountOfPieces;
    private ArrayList<Piece> boardPieces = new ArrayList<>();
    private int currentTeamId = 0;

    private int blackTeamPiecesCount;
    private int whiteTeamPiecesCount;

    public int getBlackTeamPiecesCount() {
        return blackTeamPiecesCount;
    }

    public int getWhiteTeamPiecesCount() {
        return whiteTeamPiecesCount;
    }

    public void setCurrentTeamId(int currentTeamId) {
        this.currentTeamId = currentTeamId;
    }

    public int getCurrentTeamId() {
        return currentTeamId;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getAmountOfPieces() {
        return amountOfPieces;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void setAmountOfPieces(int amountOfPieces) {
        this.amountOfPieces = amountOfPieces;
    }

    public void addPiece(Piece piece) {
        boardPieces.add(piece);
    }

    public ArrayList<Piece> getBoardPieces() {
        return boardPieces;
    }

    public Piece getPiecesById(int Id) {
        for (Piece piece : this.getBoardPieces()) {
            if (piece.getUniqueId() == Id) {
                return piece;
            }
        }

        return null;
    }

    public boolean createPiecesFromFile(BufferedReader reader, int numPieces) {
        String line;
        try {
            for (int countLine = 1; countLine <= numPieces; countLine++) {
                line = reader.readLine();
                addPiece(PieceFactory.CreatePiece(line));
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean buildBoardFromFile(BufferedReader reader) {
        String line;
        int y = 0;

        try {
            while ((line = reader.readLine()) != null) {
                var lineElements = line.split(":");
                var isBoardFileLine = lineElements.length == this.getBoardSize();
                if (!isBoardFileLine) {
                    return false;
                }

                this.processBoardFileLine(lineElements, y);
                y++;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean squareHasPiece(int x, int y){
        for(int i = 0; i < getBoardPieces().size(); i++){
            Piece piece = getPiecesById(i);
            if(piece.getX() == x && piece.getY() == y){
                return true;
            }
        }

        return false;
    }

    public Piece getPieceAt(int x, int y){

        for (Piece piece : boardPieces) {
            if (piece.getX() == x && piece.getY() == y){
                return piece;
            }
        }

        return null;
    }

    public String[] squareInfoToArray(Piece piece) {
        String[] properties = new String[5];
        properties[0] = String.valueOf(piece.getUniqueId());
        properties[1] = String.valueOf(piece.getType());
        properties[2] = String.valueOf(piece.getTeam());
        properties[3] = piece.getNickName();
        properties[4] = piece.getPng();

        return properties;
    }

    public boolean isValidCoordinate(int x, int y) {
        var isValidX = x >= 0 && x < this.getBoardSize();
        var isValidY = y >= 0 && y < this.getBoardSize();
        return isValidX && isValidY;
    }

    public void switchPlayingTeam(){
        if (this.getCurrentTeamId() == Piece.BLACK_TEAM){
            this.setCurrentTeamId(Piece.WHITE_TEAM);
        }else {
            this.setCurrentTeamId(Piece.BLACK_TEAM);
        }
    }

    public void countHowManyPiecesAreInGameForEachTeam() {
        this.blackTeamPiecesCount = 0;
        this.whiteTeamPiecesCount = 0;

        for (Piece piece : this.getBoardPieces()) {
            var pieceIsInGame = piece.getStatus().equals(Piece.PIECE_IN_GAME);
            if (pieceIsInGame) {
                if (piece.getTeam() == Piece.BLACK_TEAM) {
                    blackTeamPiecesCount++;
                } else {
                    whiteTeamPiecesCount++;
                }
            }
        }
    }

    public void processBoardFileLine(String[] lineElements, int y) {
        var x = 0;

        for (String lineElement : lineElements) {

            var lineHasPieceId = Integer.parseInt(lineElement) != 0;
            if (lineHasPieceId) {
                Piece piece = getPiecesById(Integer.parseInt(lineElement));
                if (piece != null) {
                    piece.setPieceInBoard(x, y);
                }
            }

            x++;
        }
    }

}
