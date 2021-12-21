package br.ufjf.dcc.dcc025.bingo;

public class Card {
    private static final int CARD_TABLE_SIZE = 5; 
    private final int SEQUENCIAL_NUMBER;
    private Cell[][] cardTable;

    public Card(int sequencialNumber) {
        this.SEQUENCIAL_NUMBER = sequencialNumber;
        this.initializeCardTable();
        this.generateCardValues();
    }

    // --------------- Getters ---------------

    public int getSequencialNumber() {
        return this.SEQUENCIAL_NUMBER;
    }

    // --------------- Methods ---------------

    private void initializeCardTable() {
        this.cardTable = new Cell[Card.CARD_TABLE_SIZE][Card.CARD_TABLE_SIZE];

        for (int row = 0; row < Card.CARD_TABLE_SIZE; row++) {
            for (int column = 0; column < Card.CARD_TABLE_SIZE; column++) {
                this.cardTable[row][column] = new Cell();
            }
        }
    }

    private void generateCardValues() {
        for (int column = 0; column < Card.CARD_TABLE_SIZE; column++) {
            for (int row = 0; row < Card.CARD_TABLE_SIZE; row++) {
                int randomValue;
                do {
                    randomValue = (int) (Math.random() * 15 + column * 15 + 1);
                } while(this.repeatedValueInColumn(column, randomValue));

                Cell cell = this.cardTable[row][column];
                cell.setValue(randomValue);
            }
        }

        this.cardTable[2][2].removeValue();
    }

    private boolean repeatedValueInColumn(int column, int value) {
        for (int i = 0; i < Card.CARD_TABLE_SIZE; i++) {
            if (this.cardTable[i][column].getValue() == value) {
                return true;
            }
        }

        return false;
    }

    public void updateCard(int value) {
        for (int row = 0; row < Card.CARD_TABLE_SIZE; row++) {
            for (int column = 0; column < Card.CARD_TABLE_SIZE; column++) {
                Cell cell = this.cardTable[row][column];
                
                if (cell.getValue() == value) {
                    cell.markCell();
                }
            }
        }
    }

    public boolean isCompleted() {
        for (int row = 0; row < Card.CARD_TABLE_SIZE; row++) {
            for (int column = 0; column < Card.CARD_TABLE_SIZE; column++) {
                if (row == 2 && column == 2) {
                    continue;
                }
                
                Cell cell = this.cardTable[row][column];
                
                if (!cell.isMarked()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void displayCard() {
        System.out.println("Cartela: " + this.getSequencialNumber());
        System.out.println("| B  | I  | N  | G  | O  |");

        for (int i = 0; i < Card.CARD_TABLE_SIZE; i++) {
            String displayLine = "|";

            for (int j = 0; j < Card.CARD_TABLE_SIZE; j++) {
                if (i == 2 && j == 2) {
                    displayLine += "    |";
                    continue;
                }

                Cell cell = this.cardTable[i][j];
                
                int value = cell.getValue();
                if (cell.isMarked()) {
                    displayLine += "-";

                    if (value <= 9) {
                        displayLine += "0";
                    }

                    displayLine += cell.getValue() + "-|";
                } else {
                    displayLine += " ";

                    if (value <= 9) {
                        displayLine += "0";
                    }

                    displayLine += cell.getValue() + " |";
                }
            }

            System.out.println(displayLine);
        }

        System.out.println();
    }
    
}
