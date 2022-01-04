package br.ufjf.dcc.dcc025.escape.player;

import br.ufjf.dcc.dcc025.escape.Board;

public class PlayerCoordinate {
    private int rowPosition;
    private int columnPosition;

    public PlayerCoordinate() {
        this.rowPosition = 1;
        this.columnPosition = 1;
    }

    // --------------- Getters ---------------

    public int getRowPosition() {
        return this.rowPosition;
    }

    public int getColumnPosition() {
        return this.columnPosition;
    }

    // --------------- Methods ---------------

    void verticalMove(int steps) {
        int finalRowPosition = this.rowPosition + steps;

        if (Board.isValidPosition(finalRowPosition, this.columnPosition)) {
            this.rowPosition += steps;
        }
    }

    void horizontalMove(int steps) {
        int finalColumnPosition = this.columnPosition + steps;

        if (Board.isValidPosition(this.rowPosition, finalColumnPosition)) {
            this.columnPosition += steps;
        }
    }
}
