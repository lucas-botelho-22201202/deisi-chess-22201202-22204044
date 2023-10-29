package pt.ulusofona.lp2.deisichess;

public class Statistic {
    private int countCaptureBlack;
    private int countValidMovesBlack;
    private int countInvalidMovesBlack;
    private int countCaptureWhite;
    private int countValidMovesWhite;
    private int countInvalidMovesWhite;
    private int winningTeam;

    public void setWinningTeam(int winningTeam) {
        this.winningTeam = winningTeam;
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
                statisticBuilder.append("VENCERAM AS PRETAS");
            }
            default -> {
                statisticBuilder.append("EMPATE");
            }
        }

        statisticBuilder.append("\n---\n");

        statisticBuilder.append("Equipa das pretas\n")
                .append(countCaptureBlack).append("\n")
                .append(countValidMovesBlack).append("\n")
                .append(countInvalidMovesBlack).append("\n");

        statisticBuilder.append("Equipa das bracas\n")
                .append(countCaptureWhite).append("\n")
                .append(countValidMovesWhite).append("\n")
                .append(countInvalidMovesWhite);

        return statisticBuilder.toString();
    }
}
