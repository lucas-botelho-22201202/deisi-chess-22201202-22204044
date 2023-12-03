package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Rainha extends Piece {
    static final String BLACK_PNG = "Grey_Queen.png";
    static final String WHITE_PNG = "Beige_Queen.png";

    int virtualX;
    int virtualY;

    public Rainha(int uniqueId, int type, int team, String nickName) {
        this.uniqueId = uniqueId;
        this.type = type;
        this.team = team;
        this.nickName = nickName;
        this.movementRange = 5;

        switch (team) {
            case Piece.BLACK_TEAM -> {
                this.png = Rainha.BLACK_PNG;
            }
            case Piece.WHITE_TEAM -> {
                this.png = Rainha.WHITE_PNG;
            }
        }
    }

    @Override
    public boolean isValidMovement(ArrayList<Piece> boardPieces, int x, int y) {


        return isValidBehaviour(x, y) && isValidOffset(x, y);
    }

    @Override
    public boolean simulateBehaviour(ArrayList<Piece> boardPieces, int x, int y) {

        if (isDiagonalMovement(getX(), getY(), x, y)) {
            DirectionDiagonal direction = calculateDiagonalDirection(getX(), getY(), x, y);


            virtualX = getX();
            virtualY = getY();

            for (int i = 0; i < movementRange; i++) {

                switch (direction) {
                    case UP_LEFT -> moveUpLeft();
                    case UP_RIGHT -> moveUpRight();
                    case DOWN_LEFT -> moveDownLeft();
                    case DOWN_RIGHT -> moveDownRight();
                }

//                if ()
            }



        }
        return false;
    }


    private boolean isValidBehaviour(int x, int y) {
        if (isDiagonalMovement(getX(), getY(), x, y)) {
            return true;
        }

        var isAxisMovement = !isXMovement(x, y) || !isYMovement(x, y);
        return isAxisMovement;
    }

    private boolean isDiagonalMovement(int x0, int y0, int x1, int y1) {
        return Math.abs(x1 - x0) == Math.abs(y1 - y0);
    }

    private DirectionDiagonal calculateDiagonalDirection(int x0, int y0, int x1, int y1) {
        if (x1 < x0) {
            return y1 < y0 ? DirectionDiagonal.UP_LEFT : DirectionDiagonal.DOWN_LEFT;
        }

        return y1 < y0 ? DirectionDiagonal.UP_RIGHT : DirectionDiagonal.DOWN_RIGHT;
    }

    private void moveUpLeft() {
        moveUp();
        moveLeft();
    }

    private void moveUpRight() {
        moveUp();
        moveRight();
    }

    private void moveDownLeft() {
        moveLeft();
        moveDown();
    }

    private void moveDownRight() {
        moveRight();
        moveDown();
    }

    private void moveUp() {
        virtualY -= 1;
    }

    private void moveDown() {
        virtualY += 1;
    }

    private void moveLeft() {
        virtualX -= 1;
    }

    private void moveRight() {
        virtualX += 1;
    }


}
