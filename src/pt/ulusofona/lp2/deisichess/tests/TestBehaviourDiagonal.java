package pt.ulusofona.lp2.deisichess.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.GameManager;
import pt.ulusofona.lp2.deisichess.Hint;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestBehaviourDiagonal {
    @Test
    public void TestValidBehaviours_BishopFromCenter(){

        var x = 3;
        var y = 4;

        ArrayList<ArrayList<Integer>> expectedPositions = new ArrayList<>();

        expectedPositions.add(new ArrayList<>(List.of(x + 1, y + 1)));
        expectedPositions.add(new ArrayList<>(List.of(x + 2, y + 2)));
        expectedPositions.add(new ArrayList<>(List.of(x + 3, y + 3)));
        expectedPositions.add(new ArrayList<>(List.of(x - 1, y - 1)));
        expectedPositions.add(new ArrayList<>(List.of(x - 2, y - 2)));
        expectedPositions.add(new ArrayList<>(List.of(x - 3, y - 3)));
        expectedPositions.add(new ArrayList<>(List.of(x + 1, y - 1)));
        expectedPositions.add(new ArrayList<>(List.of(x + 2, y - 2)));
        expectedPositions.add(new ArrayList<>(List.of(x + 3, y - 3)));
        expectedPositions.add(new ArrayList<>(List.of(x - 1, y + 1)));
        expectedPositions.add(new ArrayList<>(List.of(x - 2, y + 2)));
        expectedPositions.add(new ArrayList<>(List.of(x - 3, y + 3)));
        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/test-behaviour-diagonal/8x8-bishop-center.txt"));
        } catch (Exception e) {
        }


        var board = gameManager.getBoard();
        var piece = Board.getPieceAt(x, y, board.pieces());
        var forseenPositions = piece.forseeMovements(board.pieces(), board.getBoardSize());


        for (ArrayList<Integer> forseenPosition : forseenPositions) {

            Assertions.assertTrue(expectedPositions.contains(forseenPosition),
                    new StringBuilder("TestValidBehaviours_BishopFromCenter expected does not contain: (")
                            .append(forseenPosition.get(0)).append(":")
                            .append(forseenPosition.get(1)).append(")")
                            .toString());


        }
    }

    @Test
    public void TestValidBehaviours_BishopFromCenter_WithCollision(){

        var x = 3;
        var y = 4;


        ArrayList<ArrayList<Integer>> expectedPositions = new ArrayList<>();

        expectedPositions.add(new ArrayList<>(List.of(x + 1, y + 1)));
        expectedPositions.add(new ArrayList<>(List.of(x + 2, y + 2)));
//        expectedPositions.add(new ArrayList<>(List.of(x + 3, y + 3))); collides on +2+2
        expectedPositions.add(new ArrayList<>(List.of(x - 1, y - 1)));
        expectedPositions.add(new ArrayList<>(List.of(x - 2, y - 2)));
//        expectedPositions.add(new ArrayList<>(List.of(x - 3, y - 3))); collides with teammate
        expectedPositions.add(new ArrayList<>(List.of(x + 1, y - 1)));
        expectedPositions.add(new ArrayList<>(List.of(x + 2, y - 2)));
        expectedPositions.add(new ArrayList<>(List.of(x + 3, y - 3)));
        expectedPositions.add(new ArrayList<>(List.of(x - 1, y + 1)));
        expectedPositions.add(new ArrayList<>(List.of(x - 2, y + 2)));
        expectedPositions.add(new ArrayList<>(List.of(x - 3, y + 3)));
        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/test-behaviour-diagonal/8x8-bishop-center.txt"));
        } catch (Exception e) {
        }


        var board = gameManager.getBoard();
        var piece = Board.getPieceAt(x, y, board.pieces());
        var forseenPositions = piece.forseeMovements(board.pieces(), board.getBoardSize());


        for (ArrayList<Integer> forseenPosition : forseenPositions) {

            if (piece.isValidMove(board.pieces(), forseenPosition.get(0), forseenPosition.get(1))) {
                var pieceAtDestination = Board.getPieceAt(forseenPosition.get(0), forseenPosition.get(1), board.pieces());
                if (pieceAtDestination != null) {
                    if (gameManager.getCurrentTeamID() != pieceAtDestination.getTeam()) {
                        Assertions.assertTrue(expectedPositions.contains(forseenPosition),
                                new StringBuilder("TestValidBehaviours_BishopFromCenter_WithCollision expected does not contain: (")
                                        .append(forseenPosition.get(0)).append(":")
                                        .append(forseenPosition.get(1)).append(")")
                                        .toString());
                    }
                }
            }
        }
    }

    @Test
    public void TestInvalidBehaviours_BishopFromCenter_WithCollision(){

        var x = 3;
        var y = 4;


        ArrayList<ArrayList<Integer>> invalidPositions = new ArrayList<>();

        invalidPositions.add(new ArrayList<>(List.of(x + 3, y + 3))); //collies on +2+2

        var gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/test-behaviour-diagonal/8x8-bishop-center.txt"));
        } catch (Exception e) {
        }


        var board = gameManager.getBoard();
        var piece = Board.getPieceAt(x, y, board.pieces());
        var forseenPositions = piece.forseeMovements(board.pieces(), board.getBoardSize());


        for (ArrayList<Integer> invalidPosition : invalidPositions) {

            if (piece.isValidMove(board.pieces(), invalidPosition.get(0), invalidPosition.get(1))) {
                var pieceAtDestination = Board.getPieceAt(invalidPosition.get(0), invalidPosition.get(1), board.pieces());


                Assertions.assertFalse(forseenPositions.contains(invalidPosition),
                        new StringBuilder("TestInvalidBehaviours_BishopFromCenter_WithCollision expected does not contain: (")
                                .append(invalidPosition.get(0)).append(":")
                                .append(invalidPosition.get(1)).append(")")
                                .toString());

            }
        }
    }


}
