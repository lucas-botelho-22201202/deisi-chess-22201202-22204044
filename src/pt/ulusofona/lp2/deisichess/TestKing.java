package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestKing {
    @Test
    public void test_Rei_valid_moves() {
        var gameManager = new GameManager();

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x, y + 1},       // valido frente preto
                {x, y, x + 1, y},       // valido direita preto
                {x, y, x - 1, y},       // valido esquerda preto
                {x, y, x - 1, y - 1},   // valido tras preto
                {x, y, x - 1, y + 1},   // valido diagonal cima-direita preto
                {x, y, x, y + 1},       // valido diagonal cima-esquerda preto
                {x, y, x + 1, y + 1},   // valido diagonal baixo-direita preto
                {x, y, x + 1, y - 1}    // valido diagonal baixo-esquerda preto
        };



        for (int[] move : moves) {

            try {
                //ensures that piece is always starting on the same place
                gameManager.loadGame(new File("test-files/test-king/8x8king.txt"));
            } catch (Exception e) {
            }

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];


            Assertions.assertTrue(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_Rei_valid_moves failed moving from: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
        }
    }

    @Test
    public void test_Rei_invalid_moves() {
        var gameManager = new GameManager();

        var x = 3;
        var y = 4;

        int[][] moves = {
                {x, y, x, y}, //invalido mesmo sitio
                {x, y, x+2, y}, //invalido 2 direita
                {x, y, x-2, y}, //invalido 2 esquerda
                {x, y, x, y+2}, //invalido 2 baixo
                {x, y, x, y-2}, //invalido 2 cima
                {0, 0, 1, 0}, //ver se consegue comer peca da mesma equipa
        };

        for (int[] move : moves) {

            try {
                //ensures that piece is always starting on the same place
                gameManager.loadGame(new File("test-files/test-king/8x8king.txt"));
            } catch (Exception ignored) {
            }

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];

            Assertions.assertFalse(gameManager.move(startingX, startingY, endingX, endingY),
                    new StringBuilder("test_Rei_invalid_moves successfully moved from: (")
                            .append(startingX).append(":")
                            .append(startingY).append(")")
                            .append(" to: (")
                            .append(endingX).append(":")
                            .append(endingY).append(")")
                            .toString());
        }
    }
}
