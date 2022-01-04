package br.ufjf.dcc.dcc025.escape;

import br.ufjf.dcc.dcc025.escape.player.PlayerCoordinate;

public class Board {
    private static final int BOARD_SIZE = 10;
    private int numberOfBombs;
    private Square[][] board;
    private PlayerCoordinate playerCoordinate;

    public Board(int numberOfBombs, PlayerCoordinate playerCoordinate) {
        this.numberOfBombs = numberOfBombs;
        this.playerCoordinate = playerCoordinate;
        this.initializeBoard();
        this.fillBoard();
    }

    // --------------- Getters ---------------

    public Square getSquare(int row, int column) {
        if (Board.isValidPosition(row, column)) {
            return this.board[row - 1][column - 1];
        } else {
            return null;
        }
    }

    // --------------- Setters ---------------

    private void setSquare(int row, int column, Square value) {
        if (Board.isValidPosition(row, column)) {
            this.board[row - 1][column - 1] = value;
        }
    }

    // --------------- Methods ---------------

    public static boolean isValidPosition(int row, int column) {
        return row >= 1 && row <= Board.BOARD_SIZE && column >= 1 && column <= Board.BOARD_SIZE;
    }

    private void initializeBoard() {
        this.board = new Square[Board.BOARD_SIZE][Board.BOARD_SIZE];

        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                this.board[i][j] = Square.EMPTY;
            }
        }
    }

    private void fillBoard() {
        // Setting Exit Position
        int exitRow, exitColumn;

        do {
            exitRow = Board.raffleAxisPosition();
            exitColumn = Board.raffleAxisPosition();
        } while (
            !this.emptyPosition(exitRow, exitColumn)
        );

        this.setSquare(exitRow, exitColumn, Square.EXIT);

        // Setting Bombs Positions
        int bombRow, bombColumn, bombsGenerated = 0;
        
        while (bombsGenerated < this.numberOfBombs) {
            bombRow = Board.raffleAxisPosition();
            bombColumn = Board.raffleAxisPosition();
            
            if (this.emptyPosition(bombRow, bombColumn)) {
                this.setSquare(bombRow, bombColumn, Square.BOMB);

                if (this.availablePath(exitRow, exitColumn)) {
                    bombsGenerated++;
                } else {
                    this.setSquare(bombRow, bombColumn, Square.EMPTY);
                }
            }
        }   
    }

    private boolean emptyPosition(int row, int column) {
        Square square = this.getSquare(row, column);

        if (
            square != Square.EMPTY || 
            (this.playerCoordinate.getRowPosition() == row && this.playerCoordinate.getColumnPosition() == column)
        ) {
            return false;
        }

        return true;
    }

    private boolean availablePath(int exitRow, int exitColumn) {
        return true;
    }

    private static int raffleAxisPosition() {
        return (int) (Math.random() * 10 + 1);
    }

    public void displayBoard() {
        System.out.println();

        for (int row = 1; row <= Board.BOARD_SIZE; row++) {
            String outputRowString = "|";

            for (int column = 1; column <= Board.BOARD_SIZE; column++) {
                Square currentSquare = this.getSquare(row, column);

                if (
                    this.playerCoordinate.getRowPosition() == row &&
                    this.playerCoordinate.getColumnPosition() == column
                ) {
                    outputRowString += " P |";

                } else if (currentSquare == Square.BOMB) {
                    outputRowString += " B |";

                } else if (currentSquare == Square.EXIT) {
                    outputRowString += " S |";

                } else {
                    outputRowString += "   |";
                }
            }

            System.out.println(outputRowString);
        }
    }
}
