package br.ufjf.dcc.dcc025.escape.player;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc.dcc025.escape.Board;
import br.ufjf.dcc.dcc025.escape.Square;

public class Player {
    private PlayerCoordinate coordinate;
    private List<String> pathInstructions;
    private Board board;
    public boolean completedGame;

    public Player() {
        this.coordinate = new PlayerCoordinate();
        this.pathInstructions = new ArrayList<>();
        this.completedGame = false;
    }

    // --------------- Getters ---------------

    public PlayerCoordinate getCoordinate() {
        return this.coordinate;
    }

    // --------------- Methods ---------------

    public void enterBoard(Board board) {
        this.board = board;
    }

    private boolean canMove(char direction, int steps) {
        int finalRowPosition = this.coordinate.getRowPosition();
        int finalColumnPosition = this.coordinate.getColumnPosition();

        switch (direction) {
            case 'e':
                finalColumnPosition -= steps;
                break;
        
            case 'd':
                finalColumnPosition += steps;
                break;

            case 'c':
                finalRowPosition -= steps;
                break;

            case 'b':
                finalRowPosition += steps;
                break;

            default:
                break;
        }

        return Board.isValidPosition(finalRowPosition, finalColumnPosition);
    }

    public boolean move(char direction, int steps) {
        if (!canMove(direction, steps)) {
            return false;
        }

        for (int step = 0; step < steps; step++) {
            switch (direction) {
                case 'e':
                    this.coordinate.horizontalMove(-1);
                    break;
            
                case 'd':
                    this.coordinate.horizontalMove(1);
                    break;
    
                case 'c':
                    this.coordinate.verticalMove(-1);
                    break;
    
                case 'b':
                    this.coordinate.verticalMove(1);
                    break;
    
                default:
                    break;
            }

            Square currentSquare = this.board.getSquare(this.coordinate.getRowPosition(), this.coordinate.getColumnPosition());
            
            if (currentSquare == Square.BOMB) {
                this.completeGame("Game Over!");

                break;
            } else if (currentSquare == Square.EXIT) {
                this.completeGame("Parabéns!");

                break;
            }
        }

        String instruction = "(" + direction + ", " + steps + ")";
        pathInstructions.add(instruction);


        if (this.completedGame) {
            String outputString = "Lista de instruções: ";

            for (String currentInstruction : this.pathInstructions) {
                outputString += currentInstruction + " ";
            }

            System.out.println(outputString);

            this.board.displayBoard();
        }
        
        return true;
    }

    private void completeGame(String message) {
        System.out.println();
        System.out.println(message);

        this.completedGame = true;
    }
}
