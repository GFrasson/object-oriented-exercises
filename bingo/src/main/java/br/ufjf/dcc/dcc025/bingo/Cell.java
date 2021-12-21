package br.ufjf.dcc.dcc025.bingo;

public class Cell {
    private int value;
    private boolean marked;

    public Cell() {
        this.value = 0;
        this.marked = false;
    }

    // --------------- Setters ---------------

    public void setValue(int value) {
        if (value >= 1 && value <= 75) {
            this.value = value;
        }
    }

    public void markCell() {
        this.marked = true;
    }

    // --------------- Getters ---------------

    public int getValue() {
        return this.value;
    }

    public boolean isMarked() {
        return this.marked;
    }

    // --------------- Methods ---------------

    public void removeValue() {
        this.value = 0;
    }
}
