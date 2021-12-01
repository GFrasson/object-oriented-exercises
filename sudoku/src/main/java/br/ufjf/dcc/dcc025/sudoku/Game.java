package br.ufjf.dcc.dcc025.sudoku;

import java.util.Scanner;
import br.ufjf.dcc.dcc025.sudoku.Board;

public class Game {

    public Game() {

    }

    public void mainMenu() {
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

        if (gameMode == 1) {
            // Random Game
            System.out.println("Quantos números deseja sortear para iniciar?");
            int startingNumbersAmount = keyboard.nextInt();



        } else if (gameMode == 2) {
            // Game defined by the user

        }


        keyboard.close();
    }


}
