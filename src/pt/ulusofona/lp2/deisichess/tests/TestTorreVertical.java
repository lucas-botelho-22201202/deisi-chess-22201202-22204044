package pt.ulusofona.lp2.deisichess.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.deisichess.GameManager;

import java.io.File;

public class TestTorreVertical {
    @Test
    public void test_TorreVertical_invalid_vertical() {

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x + 1, y},  // Horizontal move to the right (one step)
                {x, y, x + 3, y},  // Horizontal move to the right (three step)
                {x, y, x - 1, y},  // Horizontal move to the left (one step)
                {x, y, x - 3, y},  // Horizontal move to the left (three step)

        };

        var gameManager = new GameManager();

        try {
            //ensures that piece is always starting on the same place
            gameManager.loadGame(new File("test-files/test-torre-vertical/8x8.txt"));
        } catch (Exception e) {
        }

        for (int[] move : moves) {


            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_TorreVertical_invalid_vertical successfully from: (")
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
    public void test_TorreVertical_invalid_diagonals() {

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x + 1, y + 1},  // Diagonal move (invalid)
                {x, y, x - 1, y + 1},  // Diagonal move (invalid)
                {x, y, x + 1, y - 1},  // Diagonal move (invalid)
                {x, y, x - 1, y - 1},  // Diagonal move (invalid)
        };

        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/test-torre-vertical/8x8.txt"));
        } catch (Exception e) {
        }

        for (int[] move : moves) {

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_TorreVertical_invalid_diagonals moved successfuly: (")
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
    public void test_TorreVertical_valid_verticals() {

        var x = 3;
        var y = 4;

        //vertical moves
        int[][] moves = {
                {x, y, x, y + 2},
                {x, y, x, y - 1},
        };

        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/test-torre-vertical/8x8.txt"));
        } catch (Exception e) {
        }

        for (int[] move : moves) {


            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertTrue(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_TorreVertical_valid_verticals moved unsuccessfuly: (")
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
    public void test_TorreVertical_collisions() {

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x, y + 2},
                {x, y, x, y - 2},
        };

        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/test-torre-vertical/8x8-colisao-even-quadrants.txt"));
        } catch (Exception e) {
        }

        for (int[] move : moves) {


            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_TorreVertical_collisions moved successfuly: (")
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
