package pt.ulusofona.lp2.deisichess.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.deisichess.GameManager;

import java.io.File;

public class TestJoker {
    @Test
    public void test_JokerInvalidMove() {

        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/8x8-normal.txt"));
        } catch (Exception e) {
        }

        Assertions.assertFalse(gameManager.move(7, 0, 4, 4), "joker didnt move on queen");
        Assertions.assertFalse(gameManager.move(7, 7, 4, 5), "joker didnt move on ponei");
        Assertions.assertFalse(gameManager.move(3, 4,3,3), "joker didnt move on bishop");
        Assertions.assertFalse(gameManager.move(5, 5, 6, 6), "joker didnt move on torrehor");
    }

    @Test
    public void test_JokerValidMove() {

        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/8x8-normal.txt"));
        } catch (Exception e) {
        }

            Assertions.assertTrue(gameManager.move(7, 0, 3, 4), "joker didnt move on queen");
            Assertions.assertTrue(gameManager.move(7, 7, 5, 5), "joker didnt move on ponei");
            Assertions.assertTrue(gameManager.move(3, 4,2,3), "joker didnt move on bishop");
            Assertions.assertTrue(gameManager.move(5, 5, 6, 5), "joker didnt move on torrehor");

    }


}
