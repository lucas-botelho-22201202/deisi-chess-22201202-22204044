package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestPadreDaVila {
    @Test
    public void test_PadreDaVila_valid_diagonals() {

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x + 1, y + 1},  // Diagonal move to the top-right (one step)
                {x, y, x - 1, y + 1},  // Diagonal move to the top-left (one step)
                {x, y, x + 1, y - 1},  // Diagonal move to the bottom-right (one step)
                {x, y, x - 1, y - 1},  // Diagonal move to the bottom-left (one step)
        };


        for (int[] move : moves) {
            var gameManager = new GameManager();

            try {
                //ensures that piece is always starting on the same place
                gameManager.loadGame(new File("test-files/test-padre-da-vila/8x8bispo.txt"));
            } catch (Exception e) {
            }

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertTrue(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_PadreDaVila_valid_diagonals failed moving from: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
        }
    }

    @Test
    public void test_PadreDaVila_invalid_diagonals(){

        var x = 3;
        var y = 4;

        //bad diagonals
        int[][] moves = {
                {x, y, x + 1, y + 2},
                {x, y, x - 1, y + 2},
                {x, y, x + 2, y - 1},
                {x, y, x - 2, y - 1},
        };


        for (int[] move : moves) {
            var gameManager = new GameManager();

            try {
                //ensures that piece is always starting on the same place
                gameManager.loadGame(new File("test-files/test-padre-da-vila/8x8bispo.txt"));
            } catch (Exception e) {
            }

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_PadreDaVila_invalid_diagonals moved successfuly: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
        }
    }

    @Test
    public void test_PadreDaVila_invalid_moves(){

        var x = 3;
        var y = 4;

        //horizontal or vertical moves
        int[][] moves = {
                {x, y, x + 1, y},
                {x, y, x - 1, y},
                {x, y, x, y + 2},
                {x, y, x, y - 1},
        };


        for (int[] move : moves) {
            var gameManager = new GameManager();

            try {
                //ensures that piece is always starting on the same place
                gameManager.loadGame(new File("test-files/test-padre-da-vila/8x8bispo.txt"));
            } catch (Exception e) {
            }

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_PadreDaVila_invalid_moves moved successfuly: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
        }
    }
    @Test
    public void test_PadreDaVila_colisions(){

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x + 2, y + 2},  // Diagonal move to the top-right (two step)
                {x, y, x - 2, y - 2},  // Diagonal move to the bottom-left (two step)
        };


        for (int[] move : moves) {
            var gameManager = new GameManager();

            try {
                gameManager.loadGame(new File("test-files/test-padre-da-vila/8x8bispo-colisao.txt"));
            } catch (Exception e) {
            }

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_PadreDaVila_colisions moved successfuly: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
        }
    }



}
