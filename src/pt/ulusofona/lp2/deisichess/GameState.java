package pt.ulusofona.lp2.deisichess;

public class GameState {

    private Board board;
    private Statistic statistic;

    public GameState(Board board, Statistic statistic) {
        this.board = board;
        this.statistic = statistic;
    }

    public Board getBoard() {
        return board;
    }

    public Statistic getStatistic() {
        return statistic;
    }
}
