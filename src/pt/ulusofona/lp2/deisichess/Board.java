package pt.ulusofona.lp2.deisichess;

import java.io.BufferedReader;
import java.util.ArrayList;

public class Board {
    private int boardSize;
    private int amountOfPieces;
    private ArrayList<Piece> boardPieces = new ArrayList<>();

    private int currentTeamId = 0;

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

    public boolean setBoardSizeFromString(String line) {
        if (line.length() == 1 && Character.isDigit(line.charAt(0))) {
            this.setBoardSize(Integer.parseInt(line));
            return true;
        } else {
            return false;
        }
    }

    public boolean setAmountOfPiecesFromString(String line) {
        if (line.length() == 1 && Character.isDigit(line.charAt(0))) {
            this.setAmountOfPieces(Integer.parseInt(line));
            return true;
        } else {
            return false;
        }
    }

    public boolean buildPiecesFromFile(BufferedReader reader, int numPieces) {
        String line;
        try {
            for (int countLine = 1; countLine <= numPieces; countLine++) {
                line = reader.readLine();
                var lineElements = line.split(":");
                var isPieceFileLine = lineElements.length == GameManager.NUM_OF_PIECE_PARAMETERS_FROM_FILE;

                if (!isPieceFileLine) {
                    return false;
                }

                this.addPiece(
                        new Piece(
                                Integer.parseInt(lineElements[0]),
                                Integer.parseInt(lineElements[1]),
                                Integer.parseInt(lineElements[2]),
                                lineElements[3]
                        )
                );
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean buildBoardFromFile(BufferedReader reader) {

        String line;
        int row = 0;

        try {
            while ((line = reader.readLine()) != null) {
                var lineElements = line.split(":");
                var isBoardFileLine = lineElements.length == this.getBoardSize();
                if (!isBoardFileLine) {
                    break;
                }

                int col = 0;
                for (String lineElement : lineElements) {
                    if (Integer.parseInt(lineElement) != 0) {
                        var piece = this.getPiecesById(Integer.parseInt(lineElement));

                        if (piece != null) {
                            piece.setX(col);
                            piece.setY(row);
                        }
                    }
                    col++;
                }
                row++;
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

    public void eatPiece(Piece piece) {
        boardPieces.remove(piece);
        piece.setStatus("capturado");
    }

    public void placePieceAt(Piece piece, int x, int y) {
        piece.setX(x);
        piece.setY(y);
    }

    public void switchPlayingTeam(){
        if (this.getCurrentTeamId() == Piece.BLACK_TEAM){
            this.setCurrentTeamId(Piece.WHITE_TEAM);
        }else {
            this.setCurrentTeamId(Piece.BLACK_TEAM);
        }
    }


}
