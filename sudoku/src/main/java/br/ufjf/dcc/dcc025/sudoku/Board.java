package br.ufjf.dcc.dcc025.sudoku;

import java.util.Scanner;

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

    public void createBoard(int startingNumbersAmount) {
        if (startingNumbersAmount > 40) {
            startingNumbersAmount = 40;
        } else if (startingNumbersAmount <= 0) {
            startingNumbersAmount = 1;
        }

        for (int i = 0; i < startingNumbersAmount; i++) {
            boolean inserted = false;
            int row, column, value;

            do {
                // Random values from 1 to 9
                row = (int) (Math.random() * 9) + 1;
                column = (int) (Math.random() * 9) + 1;
                value = (int) (Math.random() * 9) + 1;
                
                if (this.canInsertValue(row, column, value) && this.verifyPosition(row, column, value)) {
                    this.insertValue(row, column, value);
                    inserted = true;
                } else {
                    // Try every possible value at the same position
                    for (int j = 1; j <= 9; j++) {
                        if (this.canInsertValue(row, column, value) && this.verifyPosition(row, column, value)) {
                            this.insertValue(row, column, value);
                            inserted = true;
                            break;
                        }
                    }
                }
                    
            } while (!inserted);
            
            this.freezePosition(row, column);
        }

        this.displayBoard();
    }

    public void createBoard(Scanner keyboard) {
        int row = 0;
        int column = 0;
        int value = 0;
        
        System.out.println("Digite os valores iniciais no formato (linha, coluna, valor):");

        // Read entries
        while (row != -1 && column != -1 && value != -1) {
            String input = keyboard.nextLine();

            for (int i = 0; i < input.length(); i++) {
                // Find pattern (row, column, value)
                if (input.charAt(i) == '(') {
                    int closeIndex = input.indexOf(")", i);
                    String numbers = input.substring(i + 1, closeIndex);
                    String[] numbersArray = numbers.trim().split(",");
    
                    try {
                        row = Integer.parseInt(numbersArray[0].trim());
                        column = Integer.parseInt(numbersArray[1].trim());
                        value = Integer.parseInt(numbersArray[2].trim());
    
                        if (row == -1 && column == -1 && value == -1) {
                            // Stop inserting
                            break;
                        } else {
                            // Insert value
                            if (this.canInsertValue(row, column, value) && this.verifyPosition(row, column, value)) {
                                this.insertValue(row, column, value);
                                this.freezePosition(row, column);
                            } else {
                                // Error
                                System.out.println("Impossível inserir o valor " + value + " na posição (linha: " + row + ", coluna: " + column + ")");
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Os valores são inválidos");
                    }
    
                    i = closeIndex;
                }
            }

            this.displayBoard();
        }
    }

    private static boolean isValidPosition(int row, int column) {
        return row >= 1 && row <= 9 && column >= 1 && column <= 9;
    }

    private boolean verifyPosition(int row, int column, int value) {
        for (int i = 1; i <= this.BOARD_SIZE; i++) {
            // Iterating over the row
            if (i != column && this.getCell(row, i).getValue() == value) {
                return false;
            }

            // Iterating over the column
            if (i != row && this.getCell(i, column).getValue() == value) {
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
                if ((i != row || j != column) && this.getCell(i, j).getValue() == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean verifyBoard() {
        for (int row = 1; row <= this.BOARD_SIZE; row++) {
            for (int column = 1; column <= this.BOARD_SIZE; column++) {
                Cell cell = this.getCell(row, column);
                int value = cell.getValue();

                if (value != 0 && cell.isEditable()) {
                    boolean validPosition = this.verifyPosition(row, column, value);

                    if (!validPosition) {
                        System.out.println();
                        System.out.println("--------------------------------------------------");
                        System.out.println("Posição inválida: (linha: " + row + ", coluna: " + column + ", valor: " + cell.getValue() + ")");
                        System.out.println("--------------------------------------------------");
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean canInsertValue(int row, int column, int value) {
        if (!Board.isValidPosition(row, column)) {
            return false;
        }

        Cell cell = this.getCell(row, column);

        if (cell.isEditable() && Cell.isValidValue(value)) {
            return true;

        } else {
            return false;
        }
    }

    public boolean insertValue(int row, int column, int value) {
        if (!this.canInsertValue(row, column, value)) {
            return false;
        }

        Cell cell = this.getCell(row, column);
        cell.setValue(value);

        return true;
    }

    public boolean removeValue(int row, int column) {
        if (!Board.isValidPosition(row, column)) {
            return false;
        }

        Cell cell = this.getCell(row, column);
        if (!cell.isEditable()) {
            return false;
        }

        cell.removeValue();

        return true;
    }

    public void freezePosition(int row, int column) {
        if (Board.isValidPosition(row, column)) {
            Cell cell = this.getCell(row, column);
            cell.setEditable(false);
        }
    }

    public int countEmptyPositions() {
        int emptyCells = 0;
        for (int row = 1; row <= this.BOARD_SIZE; row++) {
            for (int column = 1; column <= this.BOARD_SIZE; column++) {
                Cell cell = this.getCell(row, column);

                if (cell.getValue() == 0) {
                    emptyCells += 1;
                }
            }
        }

        return emptyCells;
    }

    public void displayBoard() {
        System.out.println();
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
        
        System.out.println();
    }
}
