package pt.ulusofona.lp2.deisichess.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.deisichess.GameManager;

import java.io.File;

public class TestRainha {
    @Test
    public void test_Rainha_valid_moves_top_left() {

        var x = 0;
        var y = 0;

        int[][] moves = {
                {x, y, x + 1, y},       // valido direita
                {x, y, x + 5, y},       // valido direita
                {x, y, x, y + 1},       // valido baixo
                {x, y, x, y + 5},       // valido baixo
                {x, y, x + 5, y + 5},       // valido direita baixo
                {x, y, x + 1, y + 1},       // valido baixo direita
        };

        var gameManager = new GameManager();

        try {
            //ensures that piece is always starting on the same place
            gameManager.loadGame(new File("test-files/test-rainha/8x8rainha.txt"));
        } catch (Exception e) {
        }
        for (int[] move : moves) {


            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertTrue(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_Rainha_valid_moves_freely_from_top_left failed moving from: (")
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
    public void test_Rainha_valid_moves_bottom_right() {

        var x = 7;
        var y = 7;

        int[][] moves = {
                {x, y, x - 1, y},       // valido direita
                {x, y, x - 5, y},       // valido direita
                {x, y, x, y - 1},       // valido baixo
                {x, y, x, y - 5},       // valido baixo
                {x, y, x - 5, y - 5},       // valido direita baixo
                {x, y, x - 1, y - 1},       // valido baixo preto
        };

        var gameManager = new GameManager();

        try {
            //ensures that piece is always starting on the same place
            gameManager.loadGame(new File("test-files/test-rainha/8x8rainha.txt"));
        } catch (Exception e) {
        }

        for (int[] move : moves) {


            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertTrue(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_Rainha_valid_moves_freely_from_top_left failed moving from: (")
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
    public void test_Rainha_invalid_moves_from_center(){

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x + 1, y + 2},
                {x, y, x + 1, y - 2},
                {x, y, x -1 , y - 2},
                {x, y, x -1 , y + 2},
        };

        var gameManager = new GameManager();

        try {
            //ensures that piece is always starting on the same place
            gameManager.loadGame(new File("test-files/test-rainha/8x8rainha.txt"));
        } catch (Exception e) {
        }

        for (int[] move : moves) {


            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_Rainha_invalid_moves_from_center moved successfuly: (")
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
    public void test_Rainha_colisions(){

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x - 1, y - 1},
                {x, y, x + 1, y},
        };

        var gameManager = new GameManager();

        try {
            //ensures that piece is always starting on the same place
            gameManager.loadGame(new File("test-files/test-rainha/8x8rainha-colisao.txt"));
        } catch (Exception e) {
        }
        for (int[] move : moves) {


            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_Rainha_colisions moved successfuly: (")
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
