package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.sql.Array;
import java.util.ArrayList;

public class GameManager {

    static final int numOfPieceParametersFromFile = 4;
    Board board;

    public GameManager() {
        this.board = new Board();
    }

    public boolean loadGame(File file) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int countLine = 0;


            while ((line = reader.readLine()) != null) {
                countLine++;
                if (countLine == 1 && line.length() == 1 && Character.isDigit(line.charAt(0))) {
                    board.setBoardSize(Integer.parseInt(line));
                }

                if (countLine == 2 && line.length() == 1 && Character.isDigit(line.charAt(0))) {
                    board.setAmountOfPieces(Integer.parseInt(line));
                }

                if (countLine > 2){
                    reader = board.buildPiecesFromFile(reader);
                    reader = board.buildBoardFromFile(reader);
                }
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public int getBoardSize() {
        return board.getBoardSize();
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        // Implemente o código para mover uma peça do ponto (x0, y0) para o ponto (x1, y1).
        // Se a movimentação for bem-sucedida, retorne true; caso contrário, retorne false.
        // Exemplo:

        for(int i = 0; i<board.getPieces().size(); i++){ // iterates all the pieces on the board
            int pieceX = board.getPieces().get(i).getX();
            int pieceY = board.getPieces().get(i).getY();
            int pieceTeam = board.getPiecesById(i).getTeam();

            if(pieceX == x0 && pieceY == y0){ //check if piece coordinates match the given coordinates
                if(pieceTeam == getCurrentTeamID()){ // Check if you belong to the playing team
                    if(((x0 - x1) == 1 || (x0 - x1) == -1) && ((y0 - y1) == 1 || (y0 - y1) == -1)){ // check if it's moving only one square

                    }
                }
            }
        }

        return false; // Substitua false pelo resultado apropriado.
    }

    public String[] getSquareInfo(int x, int y) {
        // Implemente o código para obter informações sobre o quadrado na posição (x, y) e retorne-as como uma String[].
        // Exemplo:



        return new String[0]; // Substitua new String[0] pelas informações reais.
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
        return 0; // Substitua 0 pelo ID da equipe atual.
    }

    public boolean gameOver() {
        // Implemente o código para verificar se o jogo acabou e retorne true se o jogo terminou, caso contrário, retorne false.
        // Exemplo:
        return false; // Substitua false pelo resultado apropriado.
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
