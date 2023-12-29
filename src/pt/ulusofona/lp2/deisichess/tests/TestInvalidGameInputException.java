package pt.ulusofona.lp2.deisichess.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.deisichess.GameManager;
import pt.ulusofona.lp2.deisichess.InvalidGameInputException;

import java.io.File;

public class TestInvalidGameInputException {
    @Test
    void InvalidGameInputExceptionTest_Dados_Mais() {
        int lineNum = 10;
        String problem = "DADOS A MAIS";
        int amountOfData = 5;
        int expectedData = 4;

        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/test-exception/8x8dados-mais-linha-10.txt"));

        } catch (InvalidGameInputException e) {
            InvalidGameInputException expected = new InvalidGameInputException(lineNum, problem, amountOfData, expectedData);
            Assertions.assertEquals(e.getProblemDescription(), expected.getProblemDescription());
            Assertions.assertEquals(e.getLineWithError(), expected.getLineWithError());
            Assertions.assertEquals(e.getProblem(), expected.getProblem());
            Assertions.assertEquals(e.getAmountOfData(), expected.getAmountOfData());
            Assertions.assertEquals(e.getExpected(), expected.getExpected());

        } catch (Exception e) {

        }
    }

    @Test
    void InvalidGameInputExceptionTest_Dados_Menos() {
        int lineNum = 10;
        String problem = "DADOS A MENOS";
        int amountOfData = 3;
        int expectedData = 4;

        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/test-exception/8x8dados-menos-linha-10.txt"));

        } catch (InvalidGameInputException e) {
            InvalidGameInputException expected = new InvalidGameInputException(lineNum, problem, amountOfData, expectedData);
            Assertions.assertEquals(e.getProblemDescription(), expected.getProblemDescription());
            Assertions.assertEquals(e.getLineWithError(), expected.getLineWithError());
            Assertions.assertEquals(e.getProblem(), expected.getProblem());
            Assertions.assertEquals(e.getAmountOfData(), expected.getAmountOfData());
            Assertions.assertEquals(e.getExpected(), expected.getExpected());

        } catch (Exception e) {

        }
    }
}
