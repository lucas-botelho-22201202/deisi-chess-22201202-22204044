package pt.ulusofona.lp2.deisichess;

import java.io.BufferedReader;
import java.util.ArrayList;

public class Board {
    private int boardSize;
    private int amountOfPieces;
    private ArrayList<Piece> pieces = new ArrayList<>();


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
        pieces.add(piece);
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public Piece getPiecesById(int Id) {
        for (Piece piece : this.getPieces()) {
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
                var isPieceFileLine = lineElements.length == GameManager.numOfPieceParametersFromFile;

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
                            piece.setY(col);
                            piece.setX(row);
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
}
