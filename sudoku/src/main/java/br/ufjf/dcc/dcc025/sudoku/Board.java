package br.ufjf.dcc.dcc025.sudoku;

public class Board {

    private final int BOARD_SIZE = 9;
    private Cell board[][];

    public Board() {
        this.board = new Cell[this.BOARD_SIZE][this.BOARD_SIZE];

        for (int i = 0; i < this.BOARD_SIZE; i++) {
            for (int j = 0; j < this.BOARD_SIZE; j++) {
                this.board[i][j] = new Cell();
            }
        }
    }

    // -------------- Getters ----------------

    private Cell getCell(int row, int column) {
        return this.board[row - 1][column - 1];
    }

    // -------------- Methods ----------------

    private static boolean isValidPosition(int row, int column) {
        return row >= 1 && row <= 9 && column >= 1 && column <= 9;
    }

    private boolean canInsertValue(int row, int column, int value) {
        if (!Board.isValidPosition(row, column)) {
            return false;
        }

        Cell cell = this.getCell(row, column);

        if (cell.isEditable() && Cell.isValidValue(value)) {
            for (int i = 1; i <= this.BOARD_SIZE; i++) {
                // Iterating over the row
                if (this.getCell(row, i).getValue() == value) {
                    return false;
                }

                // Iterating over the column
                if (this.getCell(i, column).getValue() == value) {
                    return false;
                }
            }

            // Normalize interval [1, 9] to [1, 3]
            int squarePositionRow = (int) (Math.ceil(row / 3.0));
            int squarePositionColumn = (int) (Math.ceil(column / 3.0));

            // Initial index of the square (3x3)
            int startingIndexSquareRow = (squarePositionRow * 3) - 2;
            int startingIndexSquareColumn = (squarePositionColumn * 3) - 2;

            // Iterating over the square (3x3)
            for (int i = startingIndexSquareRow; i <= startingIndexSquareRow + 2; i++) {
                for (int j = startingIndexSquareColumn; j <= startingIndexSquareColumn + 2; j++) {
                    if (this.getCell(i, j).getValue() == value) {
                        return false;
                    }
                }
            }

        } else {
            return false;
        }

        return true;
    }

    public boolean insertValue(int row, int column, int value) {
        if (!this.canInsertValue(row, column, value)) {
            return false;
        }

        Cell cell = this.getCell(row, column);
        cell.setValue(value);

        return true;
    }

    public void freezePosition(int row, int column) {
        if (Board.isValidPosition(row, column)) {
            Cell cell = this.getCell(row, column);
            cell.setEditable(false);
        }
    }

    public void displayBoard() {
        System.out.println("  | 1 2 3   4 5 6   7 8 9");
        System.out.println("--------------------------");

        for (int row = 1; row <= this.BOARD_SIZE; row++) {
            String displayRow = row + " | ";

            for (int column = 1; column <= this.BOARD_SIZE; column++) {
                Cell cell = this.getCell(row, column);

                displayRow += cell.getValue() + " ";

                if (column == 3 || column == 6) {
                    displayRow += "| ";
                }
            }

            System.out.println(displayRow);
            if (row == 3 || row == 6) {
                System.out.println("--------------------------");
            }
        }
    }
}
