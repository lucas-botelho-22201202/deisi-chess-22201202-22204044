package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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

    public boolean loadGame(File file) {
        if (file == null){
            return false;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            board.setBoardSize(Integer.parseInt(reader.readLine()));
            board.setAmountOfPieces(Integer.parseInt(reader.readLine()));

            if (!board.createPiecesFromFile(reader, board.getAmountOfPieces())) {
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
        if (!board.isValidCoordinate(x0, y0) || !board.isValidCoordinate(x1, y1)) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var sourcePiece = board.getPieceAt(x0, y0);
        if (sourcePiece == null) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var isInvalidXMove = sourcePiece.isInvalidXMove(x0, x1);
        var isInvalidYMove = sourcePiece.isInvalidYMove(y0, y1);

        if (isInvalidXMove || isInvalidYMove) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var triedToMoveOtherTeamsPiece = sourcePiece.getTeam() != getCurrentTeamID();
        if (triedToMoveOtherTeamsPiece) {
            statistic.increaseCountInvalidMoves(getCurrentTeamID());
            return false;
        }

        var destinationPiece = board.getPieceAt(x1, y1);
        if (destinationPiece != null) {
            var isSameTeam = sourcePiece.getTeam() == destinationPiece.getTeam();
            if (isSameTeam) {
                statistic.increaseCountInvalidMoves(getCurrentTeamID());
                return false;
            }

            destinationPiece.killPiece();
            statistic.increaseCountCapture(getCurrentTeamID());
        }

        sourcePiece.moveTo(x1, y1);
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
        board.countHowManyPiecesAreInGameForEachTeam();

        var isDraw = board.getBlackTeamPiecesCount() == 1 && board.getWhiteTeamPiecesCount() == 1;
        if (isDraw) {
            statistic.setWinningTeam(-1);
            return true;
        }

        var blackTeamWon = board.getWhiteTeamPiecesCount() == 0 && board.getBlackTeamPiecesCount() > 0;
        if (blackTeamWon) {
            statistic.setWinningTeam(Piece.BLACK_TEAM);
            return true;
        }

        var whiteTeamWon = board.getBlackTeamPiecesCount() == 0 && board.getWhiteTeamPiecesCount() > 0;
        if (whiteTeamWon) {
            statistic.setWinningTeam(Piece.WHITE_TEAM);
            return true;
        }

        var maxMovesReached = this.numMoves == GameManager.MAX_MOVS;
        return maxMovesReached;
    }

    public ArrayList<String> getGameResults() {
        var statsLines = statistic.toString().split("\n");

        return new ArrayList<>(Arrays.asList(statsLines));
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
        btnAuthor1.addMouseListener(new MouseListener() {
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
                if (entrou == 0) {
                    pnlAuthor1.add(lblAuthor1, BorderLayout.CENTER);
                    pnlAuthor1.add(btnAuthor1, BorderLayout.WEST);
                    pnlAuthor1.repaint();
                    pnlAuthor1.revalidate();
                    entrou = 1;
                } else {
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
        btnAuthor2.addMouseListener(new MouseListener() {
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
                if (entrou == 0) {
                    pnlAuthor2.add(lblAuthor2, BorderLayout.CENTER);
                    pnlAuthor2.add(btnAuthor2, BorderLayout.WEST);
                    pnlAuthor2.repaint();
                    pnlAuthor2.revalidate();
                    entrou = 1;
                } else {
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
