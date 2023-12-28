package pt.ulusofona.lp2.deisichess.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pt.ulusofona.lp2.deisichess.GameManager;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class TestLoadGame {

    @Test
    public void test_Reset_Statistics() {

        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/test-load-game/8x8.txt"));

        } catch (Exception e) {
        }

        int[][] moves = {
                {0, 0, 0, 1},
                {0, 7, 0, 6},
                {0, 1, 0, 2}
        };

        for (int[] move : moves) {

            int x0 = move[0];
            int y0 = move[1];
            int x1 = move[2];
            int y1 = move[3];

            gameManager.move(x0, y0, x1, y1);
            if (gameManager.gameOver()) {
                break;
            }
        }

        try {
            gameManager.loadGame(new File("test-files/test-load-game/8x8.txt"));

        } catch (Exception e) {
        }

        var gameResult = gameManager.getGameResults();

        String expectedText = "JOGO DE CRAZY CHESS,Resultado: EMPATE,---,Equipa das Pretas,0,2,0,Equipa das Brancas,0,1,0";

        ArrayList<String> listOfExpectedTexts = new ArrayList<>(Arrays.asList(expectedText.split(",")));

        Assertions.assertEquals(listOfExpectedTexts.get(10), gameResult.get(10), "test_Reset_Statistics");
    }

    @Test
    public void test_Save_And_Load() {

        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/test-load-game/8x8.txt"));

        } catch (Exception e) {
        }

        int[][] moves = {
                {0, 0, 0, 1},
                {0, 7, 0, 6},
                {0, 1, 0, 2}
        };

        for (int[] move : moves) {

            int x0 = move[0];
            int y0 = move[1];
            int x1 = move[2];
            int y1 = move[3];

            gameManager.move(x0, y0, x1, y1);
            if (gameManager.gameOver()) {
                break;
            }
        }

        try {
            gameManager.saveGame(new File("test-files/test-save-game/8x8-save.txt"));
        } catch (Exception e) {
        }

        var gameManagerSave = new GameManager();

        try {
            gameManagerSave.loadGame(new File("test-files/test-save-game/8x8-save.txt"));

        } catch (Exception e) {
        }

        Assertions.assertEquals(gameManagerSave, gameManager, "test_load_save");

    }

}
