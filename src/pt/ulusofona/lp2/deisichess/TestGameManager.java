package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class TestGameManager {
    @Test
    public void test_White_Victory(){
        var gameManager = new GameManager();

        var gameLoaded = gameManager.loadGame(new File("test-files/4x4.txt"));

        int[][] moves = {
                {1, 0, 1, 1},
                {1, 2, 1, 1},
                {3, 0, 2, 0},
                {1, 1, 2, 1},
                {1, 0, 1, 1},
                {2, 1, 1, 1}
        };

        for (int[] move : moves) {
            int x0 = move[0];
            int y0 = move[1];
            int x1 = move[2];
            int y1 = move[3];

            gameManager.move(x0, y0, x1, y1);
            if (gameManager.gameOver()){
                break;
            }
        }


        var gameResult = gameManager.getGameResults();

        String expectedText = "JOGO DE CRAZY CHESS, Resultado: VENCERAM AS BRANCAS, ---, Equipa das Pretas, 0, 2, 0, Equipa das Brancas, 2, 2, 0";

        ArrayList<String> listOfExpectedTexts = new ArrayList<>(Arrays.asList(expectedText.split(",")));

        for (int i = 0; i < listOfExpectedTexts.size(); i++) {
            Assertions.assertEquals(listOfExpectedTexts.get(i), gameResult.get(i) , "test_White_Victory result matched at index:" + i);
        }



    }
}
