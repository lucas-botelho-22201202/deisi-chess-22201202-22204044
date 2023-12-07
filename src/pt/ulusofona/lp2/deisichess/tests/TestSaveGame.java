package pt.ulusofona.lp2.deisichess.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.GameManager;

import java.io.File;
import java.io.IOException;

public class TestSaveGame {

    @Test
    void save_game_and_open_saved_file() throws IOException {
        var x = 7;
        var y = 7;
        Board expectedBoard;
        Board testBoard;

        int[][] moves = {
                {x, y, x, y - 1},
        };

        for (int[] move : moves) {
            var gameManager = new GameManager();

            try {
                gameManager.loadGame(new File("test-files/test-rei/8x8rei.txt"));
            } catch (Exception e) {
            }

            int startingX = move[0];
            int startingY = move[1];
            int endingX = move[2];
            int endingY = move[3];

            gameManager.move(startingX, startingY, endingX, endingY);

            expectedBoard = gameManager.getBoard();

            gameManager.saveGame(new File("test-files/test-save-game/TestBoard.txt"));

            try {
                gameManager.loadGame(new File("test-files/test-save-game/TestBoard.txt"));
            } catch (Exception e) {
            }

            testBoard = gameManager.getBoard();

            Assertions.assertEquals(testBoard, expectedBoard);
        }
    }
}