package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {
    static final String DADOS_A_MAIS = "DADOS A MAIS";
    static final String DADOS_A_MENOS = "DADOS A MENOS";
    static final int DADOS_ESPERADOS_PECA = 4;
    private int lineNum;
    private int amountOfData;
    private int expected;

    private String problem;

    public InvalidGameInputException(int lineNum, String problem, int amountOfData, int expected) {
        super();
        this.lineNum = lineNum;
        this.problem = problem;
        this.amountOfData = amountOfData;
        this.expected = expected;
    }

    public InvalidGameInputException(InvalidGameInputException exception) {
        super();
        this.lineNum = exception.getLineWithError();
        this.problem = exception.getProblem();
        this.amountOfData = exception.getAmountOfData();
        this.expected = exception.getExpected();
    }

    public InvalidGameInputException(String problem, int amountOfData, int expected) {
        super();
        this.problem = problem;
        this.amountOfData = amountOfData;
        this.expected = expected;
    }


    public int getLineWithError() {
        return lineNum;
    }

    public String getProblemDescription() {

        var sb = new StringBuilder(problem)
                .append(" (Esperava: ")
                .append(expected)
                .append(" ; Obtive: ")
                .append(amountOfData)
                .append(")");


        return sb.toString();
    }

    public int getAmountOfData() {
        return amountOfData;
    }

    public String getProblem() {
        return problem;
    }

    public int getExpected() {
        return expected;
    }


}
