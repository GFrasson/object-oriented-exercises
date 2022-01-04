package br.ufjf.dcc.dcc025.escape;

import java.util.Scanner;

import br.ufjf.dcc.dcc025.escape.player.*;

public class Game {
    private static Scanner keyboard;
    private Board board;
    private Player player;

    public Game() {
        Game.keyboard = new Scanner(System.in);
    }

    public void gameRun() {
        int playGame = 1;
        while (playGame == 1) {
            this.startGame();
            playGame = Game.keepPlayingInput();
        }

        Game.keyboard.close();
    }

    private void startGame() {
        this.initialConfiguration();

        while (!this.player.completedGame) {
            this.runTurn();
        }
    }

    private void initialConfiguration() {
        Game.displayMainMenu();
        
        int numberOfBombs = Game.inputNumberOfBombs();
        this.player = new Player();
        this.board = new Board(numberOfBombs, this.player.getCoordinate());
        this.player.enterBoard(this.board);
    }

    private static void displayMainMenu() {
        System.out.println("-----------------------------");
        System.out.println("-------- ESCAPE GAME --------");
        System.out.println("-----------------------------");
        System.out.println("O jogador deve encontrar a saída sem encostar nas bombas!");
        System.out.println("Para se locomover digite um sentido 'e': esquerda, 'd': direita, 'c': cima ou 'b': baixo e a quantidade de passos");
        System.out.println();
    }

    private static int inputNumberOfBombs() {
        int numberOfBombs;
        do {
            System.out.println("Digite o número de bombas (0 a 30): ");
            numberOfBombs = Game.keyboard.nextInt();
        } while (numberOfBombs < 0 || numberOfBombs > 30);
        
        return numberOfBombs;
    }

    private void runTurn() {
        this.board.displayBoard();

        char direction;
        int steps;
        
        boolean moved = false;
        while (!moved) {
            System.out.println("Digite os valores no formato (sentido, quantidade):");
            String input = Game.keyboard.nextLine();
    
            for (int i = 0; i < input.length(); i++) {
                // Find pattern (direction, steps)
                if (input.charAt(i) == '(') {
                    int closeIndex = input.indexOf(")", i);
                    String values = input.substring(i + 1, closeIndex);
                    String[] valuesArray = values.trim().split(",");
    
                    try {
                        direction = valuesArray[0].trim().charAt(0);
                        steps = Integer.parseInt(valuesArray[1].trim());
    
                        moved = this.player.move(direction, steps);
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Os valores são inválidos");
                    }
    
                    i = closeIndex;
                }
            }
        }
    }

    private static int keepPlayingInput() {
        int playGame;

        do {
            System.out.println("Quer jogar novamente?");
            System.out.println("[0] Não");
            System.out.println("[1] Sim");
            playGame = Game.keyboard.nextInt();
        } while (playGame != 0 && playGame != 1);

        return playGame;
    }
}
