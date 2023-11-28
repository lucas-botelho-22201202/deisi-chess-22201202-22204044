package pt.ulusofona.lp2.deisichess;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Board {
    private int size;
    private Team whiteTeam = new Team();
    private Team blackTeam= new Team();
    private int currentTeamId;

    public Board() {
        defineStartingTeam(Team.BLACK_TEAM);
    }

    private void defineStartingTeam(int startingTeam) {
        currentTeamId = startingTeam;
    }

    public int countBlackTeamPieces() {
        return blackTeam.getPieces().size();
    }

    public int countWhiteTeamPieces() {
        return whiteTeam.getPieces().size();
    }

    public void setCurrentTeamId(int currentTeamId) {
        this.currentTeamId = currentTeamId;
    }

    public int getCurrentTeamId() {
        return currentTeamId;
    }

    public int getSize() {
        return size;
    }

    public int countTotalPieces() {
        return countBlackTeamPieces() + countWhiteTeamPieces();
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Piece getPiecesById(int id) {
        Piece blackPiece = getPieceByIdFrom(blackTeam.getPieces(), id);

        return blackPiece != null
                ? blackPiece
                : getPieceByIdFrom(whiteTeam.getPieces(), id);
    }

    public void createPiecesFromFile(BufferedReader reader, int numPieces) throws InvalidGameInputException, IOException {
        String line;
        try {
            for (int countLine = 1; countLine <= numPieces; countLine++) {
                line = reader.readLine();
                var piece = PieceFactory.CreatePiece(line);
                AddPieceToCorrespondingTeam(piece);
            }
        } catch (InvalidGameInputException e) {
            throw new InvalidGameInputException();
        } catch (Exception e) {
            throw new IOException();
        }

    }

    private void AddPieceToCorrespondingTeam(Piece piece) {
        (piece.getTeam() == Team.BLACK_TEAM ? blackTeam : whiteTeam).addPiece(piece);
    }

    public void buildBoardFromFile(BufferedReader reader) throws IOException {
        String line;
        int y = 0;

        try {
            while ((line = reader.readLine()) != null) {
                var lineElements = line.split(":");
                var isBoardFileLine = lineElements.length == this.getSize();
                if (!isBoardFileLine) {
                    return;
                }

                this.processBoardFileLine(lineElements, y);
                y++;
            }

        } catch (Exception e) {
            throw new IOException();
        }

    }

    public Piece getPieceAt(int x, int y) {
        Piece blackPiece = getPieceAtFrom(blackTeam.getPieces(), x, y);

        return blackPiece != null
                ? blackPiece
                : getPieceAtFrom(whiteTeam.getPieces(), x, y);

    }

    private Piece getPieceAtFrom(ArrayList<Piece> pieces, int x, int y) {
        for (Piece piece : pieces) {
            if (piece.getX() == x && piece.getY() == y) {
                return piece;
            }
        }

        return null;
    }

    private Piece getPieceByIdFrom(ArrayList<Piece> pieces, int id) {
        for (Piece piece : pieces) {
            if (piece.getUniqueId() == id) {
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
        var isValidX = x >= 0 && x < this.getSize();
        var isValidY = y >= 0 && y < this.getSize();
        return isValidX && isValidY;
    }

    public void switchPlayingTeam() {
        setCurrentTeamId(getCurrentTeamId() == Team.BLACK_TEAM ? Team.WHITE_TEAM : Team.BLACK_TEAM);
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
