package pt.ulusofona.lp2.deisichess;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class GameManager {

    static final int NUM_OF_PIECE_PARAMETERS_FROM_FILE = 4;
    static final int MAX_MOVS = 10;
    public static int numMoves = 0;
    Board board;
    private int currentTeamId = 0;


    public GameManager() {
        this.board = new Board();
    }

    public boolean loadGame(File file) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            if (!board.setBoardSizeFromString(reader.readLine())) {
                return false;
            }

            if (!board.setAmountOfPiecesFromString(reader.readLine())) {
                return false;
            }

            if (!board.buildPiecesFromFile(reader, board.getAmountOfPieces())) {
                return false;
            }

            return board.buildBoardFromFile(reader);

        } catch (Exception e) {
            return false;
        }
    }

    public int getBoardSize() {
        return board.getBoardSize();
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        // Implemente o código para mover uma peça do ponto (x0, y0) para o ponto (x1, y1).
                }

        return false; // Substitua false pelo resultado apropriado.
    }

    public String[] getSquareInfo(int x, int y) {
        var piece = board.getPieceInSquare(x, y);
        if(piece != null){
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
        return this.currentTeamId;
    }

    public boolean gameOver() {

        var blackTeamPiecesCount = 0;
        var whiteTeamPiecesCount = 0;

        for (Piece piece : board.getPieces()) {
            if (piece.getTeam() == Piece.BLACK_TEAM) {
                blackTeamPiecesCount++;
            } else {
                whiteTeamPiecesCount++;
            }
        }

        var blackTeamLost = blackTeamPiecesCount == 0 && whiteTeamPiecesCount > 0;
        var whiteTeamLost = whiteTeamPiecesCount == 0 && blackTeamPiecesCount > 0;
        var isDraw = blackTeamPiecesCount == whiteTeamPiecesCount;
        var maxMovesReached = GameManager.numMoves == GameManager.MAX_MOVS;

        return blackTeamLost || whiteTeamLost || isDraw || maxMovesReached;
    }

    public ArrayList<String> getGameResults() {
        // Implemente o código para obter os resultados do jogo e retorne-os como uma ArrayList de Strings.
        // Exemplo:
        return new ArrayList<>(); // Substitua new ArrayList<>() pelos resultados reais.
    }

    public JPanel getAuthorsPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Autores do projeto:");
        lblTitle.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 36));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTitle, BorderLayout.NORTH);

        //JLabel Author 1
        JLabel lblAuthor1 = new JLabel("Gonçalo Neves nº 22204044          ", SwingConstants.RIGHT);
        lblAuthor1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 15));

        //JButton Author 1
        JButton btnAuthor1 = new JButton("Chumbar aluno!");
        btnAuthor1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        btnAuthor1.setBackground(Color.WHITE);
        btnAuthor1.setFocusPainted(false);

        //JPanel Author 1
        JPanel pnlAuthor1 = new JPanel();
        pnlAuthor1.setLayout(new BorderLayout());
        pnlAuthor1.add(lblAuthor1, BorderLayout.WEST);
        pnlAuthor1.add(btnAuthor1, BorderLayout.CENTER);

        //add Mouse Listener to Button 1
        btnAuthor1.addMouseListener(new MouseListener(){
            int entrou = 0;
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(entrou == 0){
                    pnlAuthor1.add(lblAuthor1, BorderLayout.CENTER);
                    pnlAuthor1.add(btnAuthor1, BorderLayout.WEST);
                    pnlAuthor1.repaint();
                    pnlAuthor1.revalidate();
                    entrou = 1;
                }else{
                    pnlAuthor1.add(lblAuthor1, BorderLayout.WEST);
                    pnlAuthor1.add(btnAuthor1, BorderLayout.CENTER);
                    pnlAuthor1.repaint();
                    pnlAuthor1.revalidate();
                    entrou = 0;
                }


            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panel.add(pnlAuthor1, BorderLayout.CENTER);

        //JLabel Author 2
        JLabel lblAuthor2 = new JLabel("Lucas Botelho nº 22201202           ", SwingConstants.RIGHT);
        lblAuthor2.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 15));

        //JButton Author 2
        JButton btnAuthor2 = new JButton("Chumbar aluno!");
        btnAuthor2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        btnAuthor2.setBackground(Color.WHITE);
        btnAuthor2.setFocusPainted(false);

        //JPanel Author 2
        JPanel pnlAuthor2 = new JPanel();
        pnlAuthor2.setLayout(new BorderLayout());
        pnlAuthor2.add(lblAuthor2, BorderLayout.WEST);
        pnlAuthor2.add(btnAuthor2, BorderLayout.CENTER);

        //add Mouse Listener to Button 2
        btnAuthor2.addMouseListener(new MouseListener(){
            int entrou = 0;
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(entrou == 0){
                    pnlAuthor2.add(lblAuthor2, BorderLayout.CENTER);
                    pnlAuthor2.add(btnAuthor2, BorderLayout.WEST);
                    pnlAuthor2.repaint();
                    pnlAuthor2.revalidate();
                    entrou = 1;
                }else{
                    pnlAuthor2.add(lblAuthor2, BorderLayout.WEST);
                    pnlAuthor2.add(btnAuthor2, BorderLayout.CENTER);
                    pnlAuthor2.repaint();
                    pnlAuthor2.revalidate();
                    entrou = 0;
                }


            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panel.add(pnlAuthor2, BorderLayout.SOUTH);

        return panel;
    }
}
