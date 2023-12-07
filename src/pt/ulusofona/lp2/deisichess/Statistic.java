package pt.ulusofona.lp2.deisichess;

import pt.ulusofona.lp2.deisichess.pieces.Piece;

public class Statistic implements Cloneable {
    private int countCaptureBlack;
    private int countValidMovesBlack;
    private int countInvalidMovesBlack;
    private int countCaptureWhite;
    private int countValidMovesWhite;
    private int countInvalidMovesWhite;
    private int winningTeam = -1;

    public void setWinningTeam(int winningTeam) {
        this.winningTeam = winningTeam;
    }

    public int getWinningTeam() {
        return winningTeam;
    }

    public void increaseCountCapture(int teamId) {
        switch (teamId) {
            case Piece.BLACK_TEAM -> {
                countCaptureBlack++;
            }
            case Piece.WHITE_TEAM -> {
                countCaptureWhite++;
            }
        }
    }

    public void increaseCountValidMoves(int teamId) {
        switch (teamId) {
            case Piece.BLACK_TEAM -> {
                countValidMovesBlack++;
            }
            case Piece.WHITE_TEAM -> {
                countValidMovesWhite++;
            }
        }
    }

    public void increaseCountInvalidMoves(int teamId) {
        switch (teamId) {
            case Piece.BLACK_TEAM -> {
                countInvalidMovesBlack++;
            }
            case Piece.WHITE_TEAM -> {
                countInvalidMovesWhite++;
            }
        }
    }

    public int getNumTotalCaptures(){

        return countCaptureBlack + countCaptureWhite;
    }

    public String statisticsToFile(){
        StringBuilder statistics = new StringBuilder();

        statistics.append("Equipa das Pretas\n")
                .append(countCaptureBlack).append("\n")
                .append(countValidMovesBlack).append("\n")
                .append(countInvalidMovesBlack).append("\n");

        statistics.append("Equipa das Brancas\n")
                .append(countCaptureWhite).append("\n")
                .append(countValidMovesWhite).append("\n")
                .append(countInvalidMovesWhite);

        return statistics.toString();
    }

    @Override
    public String toString() {

        StringBuilder statisticBuilder = new StringBuilder();
        statisticBuilder.append("JOGO DE CRAZY CHESS\n")
                .append("Resultado: ");

        switch (winningTeam) {
            case Piece.BLACK_TEAM -> {
                statisticBuilder.append("VENCERAM AS PRETAS");
            }
            case Piece.WHITE_TEAM -> {
                statisticBuilder.append("VENCERAM AS BRANCAS");
            }
            default -> {
                statisticBuilder.append("EMPATE");
            }
        }

        statisticBuilder.append("\n---\n");

        statisticBuilder.append("Equipa das Pretas\n")
                .append(countCaptureBlack).append("\n")
                .append(countValidMovesBlack).append("\n")
                .append(countInvalidMovesBlack).append("\n");

        statisticBuilder.append("Equipa das Brancas\n")
                .append(countCaptureWhite).append("\n")
                .append(countValidMovesWhite).append("\n")
                .append(countInvalidMovesWhite);

        return statisticBuilder.toString();
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
