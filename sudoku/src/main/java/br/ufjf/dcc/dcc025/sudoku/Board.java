package br.ufjf.dcc.dcc025.sudoku;

import br.ufjf.dcc.dcc025.sudoku.Cell;

public class Board {

    private final int BOARD_SIZE = 9;
    private Cell board[][];

    public Board() {
        board = new Cell[BOARD_SIZE][BOARD_SIZE];
    }
}
