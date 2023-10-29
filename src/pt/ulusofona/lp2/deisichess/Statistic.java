package pt.ulusofona.lp2.deisichess;

public class Statistic {
    private int countCaptureBlack;
    private int countValidMovesBlack;
    private int countInvalidMovesBlack;
    private int countCaptureWhite;
    private int countValidMovesWhite;
    private int countInvalidMovesWhite;

    private int winningTeam;

    public void setCountCaptureBlack(int countCaptureBlack) {
        this.countCaptureBlack = countCaptureBlack;
    }

    public void increaseCountValidMovesBlack(int countValidMovesBlack) {
        this.countValidMovesBlack = countValidMovesBlack;
    }

    public void increaseCountInvalidMovesBlack(int countInvalidMovesBlack) {
        this.countInvalidMovesBlack = countInvalidMovesBlack;
    }

    public void increaseCountCaptureWhite(int countCaptureWhite) {
        this.countCaptureWhite = countCaptureWhite;
    }

    public void increaseCountValidMovesWhite(int countValidMovesWhite) {
        this.countValidMovesWhite = countValidMovesWhite;
    }

    public void increaseCountInvalidMovesWhite(int countInvalidMovesWhite) {
        this.countInvalidMovesWhite = countInvalidMovesWhite;
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
                .append(countInvalidMovesWhite).append("\n");

        return statisticBuilder.toString();
    }
}
