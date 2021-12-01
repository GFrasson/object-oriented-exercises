package br.ufjf.dcc.dcc025.sudoku;

import java.util.Scanner;
import java.util.regex.Pattern;

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
            
            // Start Game

        } else if (gameMode == 2) {
            // Game defined by the user
            System.out.println("Digite os valores iniciais no formato (linha,coluna,valor):");

            Pattern pattern = Pattern.compile("\\([1-9],[1-9],[1-9]\\)");
            
            while (keyboard.hasNext(pattern)) {
                String userEntry = keyboard.next(pattern);
            }
        }


        keyboard.close();
    }


}
