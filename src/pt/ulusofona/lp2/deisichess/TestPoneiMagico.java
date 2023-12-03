package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestPoneiMagico {
    @Test
    public void test_PoneiMagico_valid_moves_freely() {

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x + 2, y + 2},       // valido cima direita
                {x, y, x - 2, y + 2},       // valido baixo direita
                {x, y, x - 2, y - 2},       // valido baixo esquerda
                {x, y, x - 2, y + 2},       // valido baixo direita

        };


        for (int[] move : moves) {
            var gameManager = new GameManager();

            try {
                //ensures that piece is always starting on the same place
                gameManager.loadGame(new File("test-files/test-ponei/8x8ponei.txt"));
            } catch (Exception e) {
            }

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertTrue(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_PoneiMagico_valid_moves_freely failed moving from: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
        }
    }

}
