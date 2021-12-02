package br.ufjf.dcc.dcc025.sudoku;

import java.util.Scanner;

public class Game {

    private Board board;

    public Game() {
        this.board = new Board();
    }

    // -------------- Methods ----------------

    public void startGame() {
        this.mainMenu();
        this.board.displayBoard();
    }
    
    private void mainMenu() {
        Scanner keyboard = new Scanner(System.in);
        int gameMode;

        System.out.println("-----------------------------");
        System.out.println("----------- SUDOKU ----------");
        System.out.println("-----------------------------");
        System.out.println();

        do {
            System.out.println("Escolha uma opção para jogar");
            System.out.println("[1] Jogo aleatório");
            System.out.println("[2] Jogo definido");
            gameMode = keyboard.nextInt();

        } while (gameMode != 1 && gameMode != 2);

        switch (gameMode) {
            case 1:
                // Random Game
                System.out.println("Quantos números deseja sortear para iniciar? (escreva um valor de 1 a 81)");
                int startingNumbersAmount = keyboard.nextInt();

                this.createBoard(startingNumbersAmount);
                break;

            case 2:
                // Game defined by the user
                this.createBoard();
                break;

            default:
                System.exit(0);
                break;
        }

        keyboard.close();
    }

    private void createBoard(int startingNumbersAmount) {
        if (startingNumbersAmount > 81) {
            startingNumbersAmount = 81;
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
    
                inserted = this.board.insertValue(row, column, value);
    
                if (!inserted) {
                    // Try every possible value at the same position
                    for (int j = 1; j <= 9; j++) {
                        inserted = this.board.insertValue(row, column, value);
    
                        if (inserted)
                            break;
                    }
                }
            } while (!inserted);
            
            this.board.freezePosition(row, column);
        }
    }

    private void createBoard() {
        Scanner keyboard = new Scanner(System.in);
        int row = 0;
        int column = 0;
        int value = 0;
        
        System.out.println("Digite os valores iniciais no formato (linha,coluna,valor):");

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
                            boolean inserted = this.board.insertValue(row, column, value);
    
                            if (inserted) {
                                this.board.freezePosition(row, column);
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
        }

        keyboard.close();
    }

    private void gameMenu() {
        Scanner keyboard = new Scanner(System.in);
        int gameMode;

        do {
            System.out.println("Escolha uma opção para jogar");
            System.out.println("[1] Adicionar jogada");
            System.out.println("[2] Remover jogada");
            System.out.println("[3] Verificar");
            System.out.println("[4] Sair");
            gameMode = keyboard.nextInt();

        } while (gameMode != 1 && gameMode != 2 && gameMode != 3 && gameMode != 4);

        switch (gameMode) {
            case 1:
                
                break;
        
            case 2:
                
                break;
            
            case 3:
                
                break;
            
            case 4:
                
                break;

            default:
                break;
        }
    }
}
