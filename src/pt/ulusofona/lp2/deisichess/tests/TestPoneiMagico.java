package pt.ulusofona.lp2.deisichess.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.deisichess.GameManager;

import java.io.File;

public class TestPoneiMagico {

    @Test
    public void test_PoneiMagico_colisionsEvenQuadrants() {

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x + 2, y - 2},
                {x, y, x - 2, y + 2},
        };


        for (int[] move : moves) {
            var gameManager = new GameManager();

            try {
                //ensures that piece is always starting on the same place
                gameManager.loadGame(new File("test-files/test-ponei-magico/8x8-colisao-even-quadrants.txt"));
            } catch (Exception e) {
            }

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_PoneiMagico_colisionsEvenQuadrants moved successfuly: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
        }
    }

    @Test
    public void test_PoneiMagico_colisionsOddQuadrants() {

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x + 2, y + 2},
                {x, y, x - 2, y - 2},
        };


        for (int[] move : moves) {
            var gameManager = new GameManager();

            try {
                //ensures that piece is always starting on the same place
                gameManager.loadGame(new File("test-files/test-ponei-magico/8x8-colisao-odd-quadrants.txt"));
            } catch (Exception e) {
            }

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_PoneiMagico_colisionsEvenQuadrants moved successfuly: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
        }
    }


    @Test
    public void test_PoneiMagico_validMovementsFromCenter() {

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x - 2, y - 2},
                {x, y, x + 2, y - 2},
                {x, y, x + 2, y + 2},
                {x, y, x - 2, y + 2},
        };


        for (int[] move : moves) {
            var gameManager = new GameManager();

            try {
                //ensures that piece is always starting on the same place
                gameManager.loadGame(new File("test-files/test-ponei-magico/8x8.txt"));
            } catch (Exception e) {
            }

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertTrue(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_PoneiMagico_validMovementsFromCenter moved unsuccessfuly: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
        }
    }

    @Test
    public void test_PoneiMagico_with_obstruction_in_one_path() {

        var x = 4;
        var y = 2;

        int[][] moves = {
                {x, y, x + 2, y + 2},
        };


        for (int[] move : moves) {
            var gameManager = new GameManager();

            try {
                //ensures that piece is always starting on the same place
                gameManager.loadGame(new File("test-files/test-ponei-magico/8x8-colisao.txt"));
            } catch (Exception e) {
            }

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertTrue(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_PoneiMagico_validMovementsFromCenter moved unsuccessfuly: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
        }
    }



}
