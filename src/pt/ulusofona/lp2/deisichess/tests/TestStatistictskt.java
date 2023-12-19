package pt.ulusofona.lp2.deisichess.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.deisichess.*;
import pt.ulusofona.lp2.deisichess.pieces.Piece;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class TestStatistictskt {
    @Test
    public void test_tipos_capturados() {


        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/8x8.txt"));

            for (int i = 0; i < gameManager.getBoard().getAmountOfPieces(); i++) {
               gameManager.getBoard().pieces().get(i).setStatus(Piece.PIECE_IS_CAPTURED);
            }

            var result = StatisticsKt.getStatsCalculator(StatType.TIPOS_CAPTURADOS).invoke(gameManager);

            var expected = new ArrayList<>(Arrays.asList(
                    "Homer Simpson",
                    "Joker/Rainha",
                    "Padre da Vila",
                    "Ponei Mágico",
                    "Rainha",
                    "Rei",
                    "TorreHor",
                    "TorreVert"
            ));
            Assertions.assertEquals(expected, result, "test_tipos_capturados");

        } catch (Exception e) {
        }

        return;
    }
}
