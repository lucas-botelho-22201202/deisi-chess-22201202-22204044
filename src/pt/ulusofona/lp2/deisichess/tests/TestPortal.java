package pt.ulusofona.lp2.deisichess.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.deisichess.GameManager;

import java.io.File;

public class TestPortal {
    @Test
    public void test_Portal_valid_moves() {

        var x = 0;
        var y = 0;

        int[][] moves = {
                {x, y, x, y + 2},
                {x, y, x + 2, y},
                {x, y, x + 2, y + 2}
        };

        var gameManager = new GameManager();

        try {
            //ensures that piece is always starting on the same place
            gameManager.loadGame(new File("test-files/test-portal/8x8portal.txt"));
        } catch (Exception e) {
        }

        for (int[] move : moves) {


            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertTrue(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_Portal_valid_moves moved unsuccessfuly: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
            gameManager.undo();

        }
    }

    @Test
    public void test_Portal_invalid_move() {

        var x = 0;
        var y = 0;

        int[][] moves = {
                {x, y, x, y + 1},
                {x, y, x + 1, y},
                {x, y, x + 2, y + 1}
        };

        var gameManager = new GameManager();

        try {
            //ensures that piece is always starting on the same place
            gameManager.loadGame(new File("test-files/test-portal/8x8portal.txt"));
        } catch (Exception e) {
        }

        for (int[] move : moves) {


            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_Portal_invalid_moves moved successfuly: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
            gameManager.undo();

        }
    }

    @Test
    public void test_Portal_collision() {

        var x = 0;
        var y = 0;

        int[][] moves = {
                {x, y, x + 4, y}
        };

        var gameManager = new GameManager();

        try {
            //ensures that piece is always starting on the same place
            gameManager.loadGame(new File("test-files/test-portal/8x8portal.txt"));
        } catch (Exception e) {
        }

        for (int[] move : moves) {


            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_Portal_collision moved successfuly: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
            gameManager.undo();

        }
    }
}
