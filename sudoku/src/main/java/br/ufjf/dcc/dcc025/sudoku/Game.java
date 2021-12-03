package br.ufjf.dcc.dcc025.sudoku;

import java.util.Scanner;

public class Game {

    private Board board;
    private Scanner keyboard;

    public Game() {
        this.keyboard = new Scanner(System.in);
    }

    // -------------- Methods ----------------

    public void startGame() {
        this.board = new Board();

        int gameMode = this.selectGameMode();
        this.startGameMode(gameMode);

        int gameOption = 0;
        while (gameOption != 4) {
            gameOption = this.selectGameOption();
            this.startGameOption(gameOption);
        }

        this.keyboard.close();
    }
    
    private int selectGameMode() {
        int gameMode;

        System.out.println("-----------------------------");
        System.out.println("----------- SUDOKU ----------");
        System.out.println("-----------------------------");
        System.out.println();

        do {
            System.out.println("Escolha uma opção para jogar");
            System.out.println("[1] Jogo aleatório");
            System.out.println("[2] Jogo definido");
            gameMode = this.keyboard.nextInt();

        } while (gameMode != 1 && gameMode != 2);

        return gameMode;
    }

    private void startGameMode(int gameMode) {
        switch (gameMode) {
            // Random Game
            case 1:
                System.out.println("Quantos números deseja sortear para iniciar? (escreva um valor de 1 a 40)");
                int startingNumbersAmount = this.keyboard.nextInt();

                this.board.createBoard(startingNumbersAmount);
                break;

            // Game defined by the user
            case 2:
                this.board.createBoard(this.keyboard);
                break;

            default:
                System.exit(0);
                break;
        }
    }

    private int selectGameOption() {
        int gameOption = 0;

        do {
            System.out.println("Escolha uma opção para jogar");
            System.out.println("[1] Adicionar jogada");
            System.out.println("[2] Remover jogada");
            System.out.println("[3] Verificar");
            System.out.println("[4] Sair");
            gameOption = this.keyboard.nextInt();

        } while (gameOption != 1 && gameOption != 2 && gameOption != 3 && gameOption != 4);

        return gameOption;
    }

    private void startGameOption(int gameOption) {
        int row, column, value;
        switch (gameOption) {
            // Insert value
            case 1:
                System.out.println("Linha:");
                row = this.keyboard.nextInt();

                System.out.println("Coluna:");
                column = this.keyboard.nextInt();

                System.out.println("Valor:");
                value = this.keyboard.nextInt();

                boolean inserted = this.board.insertValue(row, column, value);

                if (inserted) {
                    this.board.displayBoard();
                } else {
                    System.out.println("Impossível inserir o valor " + value + " na posição (linha: " + row + ", coluna: " + column + ")");
                }
                
                break;
                
            // Remove value
            case 2:
                System.out.println("Linha:");
                row = this.keyboard.nextInt();

                System.out.println("Coluna:");
                column = this.keyboard.nextInt();

                boolean removed = this.board.removeValue(row, column);

                if (removed) {
                    this.board.displayBoard();
                } else {
                    System.out.println("Impossível remover o valor na posição (linha: " + row + ", coluna: " + column + ")");
                }

                break;
            
            // Verify
            case 3:
                boolean boardValid =  this.board.verifyBoard();
                
                int emptyPositions = this.board.countEmptyPositions();

                System.out.println();
                System.out.println("---------------------------");
                System.out.println(81 - emptyPositions + "/81 posições preenchidas");

                if (boardValid) {
                    System.out.println("Todas as posições são válidas");

                    // Victory
                    if (emptyPositions == 0) {
                        this.victory();

                        System.out.println();
                        System.out.println("Deseja jogar novamente?");
                        System.out.println("[1] Sim");
                        System.out.println("[2] Não");
                        int input = this.keyboard.nextInt();

                        if (input == 1) {
                            this.startGame();
                        } else {
                            this.endGame();
                        }
                    }
                }

                System.out.println("---------------------------");

                break;
            
            // Quit
            case 4:
                this.endGame();
                break;

            default:
                break;
        }
    }

    private void victory() {
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println("--------- PARABÉNS :D -------");
        System.out.println("-------- Você ganhou!! ------");
        System.out.println("-----------------------------");
        System.out.println();
    }

    private void endGame() {
        this.keyboard.close();
        System.exit(0);
    }
}
