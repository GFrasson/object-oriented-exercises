package br.ufjf.dcc.dcc025.sudoku;

public class Cell {

    private int value;
    private boolean editable;

    public Cell() {
        this.value = 0;
        this.editable = true;
    }

    // -------------- Getters ----------------

    public int getValue() {
        return this.value;
    }

    public boolean isEditable() {
        return this.editable;
    }

    // -------------- Setters ----------------

    public void setValue(int value) {
        if (Cell.isValidValue(value)) {
            this.value = value;
        } else {
            System.out.println("Não é possível inserir o valor " + value);
        }
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    // -------------- Methods ----------------

    public static boolean isValidValue(int value) {
        return value >= 1 && value <= 9;
    }
}
