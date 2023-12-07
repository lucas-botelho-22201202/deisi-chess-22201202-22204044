package pt.ulusofona.lp2.deisichess.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pt.ulusofona.lp2.deisichess.GameManager;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class TestGameManager {
    @Test
    public void test_White_Victory() {

        var gameManager = new GameManager();

        int[][] moves = {
                {1, 0, 1, 1},
                {1, 2, 1, 1},
                {3, 0, 2, 0},
                {1, 1, 2, 1},
                {2, 0, 1, 1},
                {2, 1, 1, 1}
        };

        for (int[] move : moves) {


            try {
                gameManager.loadGame(new File("test-files/4x4.txt"));

            } catch (Exception e) {
            }

            int x0 = move[0];
            int y0 = move[1];
            int x1 = move[2];
            int y1 = move[3];

            gameManager.move(x0, y0, x1, y1);
            if (gameManager.gameOver()) {
                break;
            }
        }

        var gameResult = gameManager.getGameResults();

//        String expectedText = "JOGO DE CRAZY CHESS,Resultado: VENCERAM AS BRANCAS,---,Equipa das Pretas,0,3,0,Equipa das Brancas,3,3,0";
        String expectedText = "JOGO DE CRAZY CHESS,Resultado: VENCERAM AS BRANCAS";

        ArrayList<String> listOfExpectedTexts = new ArrayList<>(Arrays.asList(expectedText.split(",")));

        Assertions.assertEquals(listOfExpectedTexts.get(1), gameResult.get(1), "test_White_Victory");
    }

    @Test
    public void test_White_Victory_1Move() {
        var gameManager = new GameManager();


        int[][] moves = {
                {0, 0, 1, 1},
                {0, 1, 1, 1}
        };

        for (int[] move : moves) {

            try {
                gameManager.loadGame(new File("test-files/4x4_1move_victory_white.txt"));

            } catch (Exception e) {
            }

            int x0 = move[0];
            int y0 = move[1];
            int x1 = move[2];
            int y1 = move[3];

            gameManager.move(x0, y0, x1, y1);
            if (gameManager.gameOver()) {
                break;
            }
        }


        var gameResult = gameManager.getGameResults();

        String expectedText = "JOGO DE CRAZY CHESS,Resultado: VENCERAM AS BRANCAS";

        ArrayList<String> listOfExpectedTexts = new ArrayList<>(Arrays.asList(expectedText.split(",")));

        Assertions.assertEquals(listOfExpectedTexts.get(1), gameResult.get(1), "test_White_Victory_1Move");


    }

    @Test
    public void test_Draw_10_Sequencial_moves_without_capturing() {
        var gameManager = new GameManager();


        int[][] moves = {
                {1, 0, 1, 1},//preta
//                {1, 2, 1, 1},//branca comeu (captura das brancas)
                {3, 0, 3, 1},//preta
                {1, 1, 0, 1},//branca
                {3, 1, 3, 0},//preta
                {0, 1, 1, 1},//branca
                {3, 0, 3, 1},//preta
                {1, 1, 0, 1},//branca
                {3, 1, 3, 0},//preta
                {0, 1, 1, 1},//branca
                {3, 0, 3, 1},//preta
                {1, 1, 0, 1} //branca
        };

        int countMoves = 0;
        for (int[] move : moves) {

            try {
                gameManager.loadGame(new File("test-files/4x4.txt"));

            } catch (Exception e) {

            }

            int x0 = move[0];
            int y0 = move[1];
            int x1 = move[2];
            int y1 = move[3];

            gameManager.move(x0, y0, x1, y1);
            countMoves++;
            if (gameManager.gameOver()) {
                break;
            }
        }

        var gameResult = gameManager.getGameResults();

        String expectedText = "JOGO DE CRAZY CHESS,Resultado: EMPATE";

        ArrayList<String> listOfExpectedTexts = new ArrayList<>(Arrays.asList(expectedText.split(",")));

        System.out.println(countMoves);
        Assertions.assertEquals(listOfExpectedTexts.get(1), gameResult.get(1), "test_Draw_10_Sequencial_moves_without_capturing failed with total of:" + countMoves + " moves.");
    }

    @Test
    public void test_White_Instant_Victory() {
        var gameManager = new GameManager();
        try {
            gameManager.loadGame(new File("test-files/4x4_white_win_instantly.txt"));
            gameManager.gameOver();
            var gameResult = gameManager.getGameResults();

//            String expectedText = "JOGO DE CRAZY CHESS,Resultado: VENCERAM AS BRANCAS,---,Equipa das Pretas,0,3,0,Equipa das Brancas,3,3,0";
            String expectedText = "JOGO DE CRAZY CHESS,Resultado: VENCERAM AS BRANCAS";

            ArrayList<String> listOfExpectedTexts = new ArrayList<>(Arrays.asList(expectedText.split(",")));

            Assertions.assertEquals(listOfExpectedTexts.get(1), gameResult.get(1), "test_White_Victory");

        } catch (Exception e) {

        }
    }
}
