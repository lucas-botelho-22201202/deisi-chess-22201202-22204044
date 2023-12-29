package pt.ulusofona.lp2.deisichess.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.deisichess.GameManager;

import java.io.File;

public class TestHomer {
    @Test
    public void test_HomerInvalidMove() {

        var x = 6;
        var y = 7;
        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/8x8-normal.txt"));
        } catch (Exception e) {
        }


            Assertions.assertFalse(gameManager.move(0, y, x-1, y-1), "homer moved");
            gameManager.move(0, 0, 1, 1);
            Assertions.assertFalse(gameManager.move(x, y, x-1, y), "homer moved");


    }

    @Test
    public void test_HomerValidMove() {

        var x = 6;
        var y = 7;

        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/8x8-normal.txt"));
        } catch (Exception e) {
        }

            gameManager.move(0, 0, 1, 1);
            Assertions.assertTrue(gameManager.move(x, y, x-1, y-1), "homer moved");
    }


}
