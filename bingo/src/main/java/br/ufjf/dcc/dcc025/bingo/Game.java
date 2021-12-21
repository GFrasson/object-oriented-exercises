package br.ufjf.dcc.dcc025.bingo;

import java.util.List;
import java.util.Vector;
import java.util.Scanner;

public class Game {
    private static Scanner keyboard;
    private Player players[];
    private List<Integer> raffledNumbers;
    private int turnValue;

    public Game() {
        Game.keyboard = new Scanner(System.in);
        this.raffledNumbers = new Vector<Integer>(75);
    }

    // --------------- Getters ---------------

    private int getNumberOfPlayers() {
        return this.players.length;
    }

    private int getNumberOfPlayersLeft() {
        int count = 0;
        for (int i = 0; i < this.getNumberOfPlayers(); i++) {
            if (!this.players[i].isWinner()) {
                count++;
            }
        }

        return count;
    }

    private int getTurnValue() {
        return this.turnValue;
    }

    // --------------- Setters ---------------
    
    private void raffleTurnValue() {
        do {
            this.turnValue = (int) (Math.random() * 75 + 1);
        } while(raffledNumbers.contains(this.turnValue));

        raffledNumbers.add(this.turnValue);

        String line = "Números sorteados: ";
        for (int i = 0; i < raffledNumbers.size(); i++) {
            line += raffledNumbers.get(i) + " ";
        }

        System.out.println(line);
    }

    // --------------- Methods ---------------

    public void startGame() {
        this.initialConfiguration();

        boolean bingo = false;
        int playGame = 1;
        while(playGame == 1 && this.getNumberOfPlayersLeft() >= 1 && this.raffledNumbers.size() < 75) {
            while (bingo == false) {
                bingo = this.runTurn();

                int nextTurnInput;
                do {
                    System.out.println("Digite [0] para seguir para o próximo turno");
                    nextTurnInput = Game.keyboard.nextInt();
                } while (nextTurnInput != 0);
            }

            if (this.getNumberOfPlayersLeft() <= 0 || this.raffledNumbers.size() >= 75) {
                break;
            }
            
            do {
                System.out.println("Quer continuar jogando?");
                System.out.println("[0] Não");
                System.out.println("[1] Sim");
                playGame = Game.keyboard.nextInt();
            } while (playGame != 0 && playGame != 1);

            if (playGame == 1) {
                bingo = false;
            }
        }

        Game.keyboard.close();
    }

    private void initialConfiguration() {
        Game.displayMainMenu();
        
        int numberOfPlayers = inputNumberOfPlayers();
        this.initializePlayers(numberOfPlayers);
    }

    private static void displayMainMenu() {
        System.out.println("-----------------------------");
        System.out.println("----------- BINGO -----------");
        System.out.println("-----------------------------");
        System.out.println();
    }

    private static int inputNumberOfPlayers() {
        System.out.println("Digite o número de jogadores: ");
        int numberOfPlayers = Game.keyboard.nextInt();

        return numberOfPlayers;
    }

    private void initializePlayers(int numberOfPlayers) {
        this.players = new Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            this.players[i] = new Player(i + 1);
        }
    }

    private boolean runTurn() {
        this.raffleTurnValue();
        System.out.println("O valor sorteado foi: " + this.getTurnValue());

        boolean bingo = false;
        for (int i = 0; i < this.getNumberOfPlayers(); i++) {
            Player player = this.players[i];

            if (player.isWinner()) {
                continue;
            }

            Card playerCard = player.getCard();

            playerCard.updateCard(this.turnValue);
            playerCard.displayCard();

            if (playerCard.isCompleted()) {
                System.out.println("O jogador com a cartela de número " + playerCard.getSequencialNumber() + " fez bingo!");
                System.out.println();
                player.setWinner();
                bingo = true;
            }
        }

        return bingo;
    }
}
