package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestRainha {
    @Test
    public void test_Rainha_valid_moves_freely_from_top_left() {
        var gameManager = new GameManager();

        var x = 0;
        var y = 0;

        int[][] moves = {
                {x, y, x + 1, y},       // valido direita
                {x, y, x + 5, y},       // valido direita
                {x, y, x, y + 1},       // valido baixo
                {x, y, x, y + 5},       // valido baixo
                {x, y, x + 5, y + 5},       // valido direita baixo
                {x, y, x + 1, y + 1},       // valido baixo preto
        };


        for (int[] move : moves) {

            try {
                //ensures that piece is always starting on the same place
                gameManager.loadGame(new File("test-files/test-rainha/8x8rainha.txt"));
            } catch (Exception e) {
            }

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
        }
    }

    @Test
    public void test_Rainha_valid_moves_freely_from_bottom_right() {
        var gameManager = new GameManager();

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


        for (int[] move : moves) {

            try {
                //ensures that piece is always starting on the same place
                gameManager.loadGame(new File("test-files/test-rainha/8x8rainha.txt"));
            } catch (Exception e) {
            }

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
        }
    }
}
