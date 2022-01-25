package br.ufjf.dcc.dcc025.payment;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Vendedor extends Funcionario {
    private static List<Vendedor> sellers = new ArrayList<>();
    private List<Venda> sales = new ArrayList<>();

    public Vendedor() {
        super();
        Vendedor.sellers.add(this);
    }

    // ----------------- Getters -----------------

    public static List<Vendedor> getSellers() {
        return Vendedor.sellers;
    }

    public List<Venda> getSales() {
        return this.sales;
    }

    // ----------------- Methods -----------------

    protected double calculateComission() {
        double totalSalesValue = 0.0;
        for (Venda sale : this.sales) {
            totalSalesValue += sale.getValue();
        }

        return this.getDailySalary() + 0.01 * totalSalesValue;
    }

    public void addSale() {
        this.sales.add(new Venda());
    }

    public static void displayAll() {
        System.out.println();
        System.out.println("Vendedores");
        for (Vendedor seller : Vendedor.sellers) {
            System.out.println(seller.getName());
        }
    }

    public static Vendedor select() {
        Vendedor chosenSeller = null;

        while (chosenSeller == null) {
            System.out.println();
            System.out.println("Digite o nome do vendedor:");
            Scanner keyboard = new Scanner(System.in);
            String chosenSellerName = keyboard.nextLine();
            
            for (Vendedor seller : Vendedor.sellers) {
                if (seller.getName().equals(chosenSellerName)) {
                    chosenSeller = seller;
                    break;
                }
            }
        }

        return chosenSeller;
    }
}
