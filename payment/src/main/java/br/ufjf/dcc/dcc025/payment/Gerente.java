package br.ufjf.dcc.dcc025.payment;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Gerente extends Funcionario {
    private static List<Gerente> managers = new ArrayList<>();
    private Set<Vendedor> sellers;

    public Gerente() {
        super();
        Gerente.managers.add(this);
        this.sellers = new HashSet<>();
    }

    // ----------------- Getters -----------------

    public static List<Gerente> getManagers() {
        return Gerente.managers;
    }

    public Set<Vendedor> getSellers() {
        return this.sellers;
    }

    // ----------------- Methods -----------------

    protected double calculateComission() {
        double totalSalesValue = 0.0;
        for (Vendedor seller : sellers) {
            for (Venda sale : seller.getSales()) {
                totalSalesValue += sale.getValue();
            }
        }

        return this.getDailySalary() + 0.005 * totalSalesValue;
    }

    public void associateSeller(Vendedor seller) {
        this.sellers.add(seller);
    }

    public static Gerente select() {
        Gerente chosenManager = null;

        while (chosenManager == null) {
            System.out.println();
            System.out.println("Digite o nome do gerente:");
            Scanner keyboard = new Scanner(System.in);
            String chosenManagerName = keyboard.nextLine();
            
            for (Gerente manager : Gerente.managers) {
                if (manager.getName().equals(chosenManagerName)) {
                    chosenManager = manager;
                    break;
                }
            }
        }

        return chosenManager;
    }

    public static void displayAll() {
        System.out.println();
        System.out.println("Gerentes");
        for (Gerente manager : Gerente.managers) {
            System.out.println(manager.getName());
        }
    }
}
