package br.ufjf.dcc.dcc025.payment;

import java.util.Scanner;

public class Venda {
    private static int salesAmount = 0;
    private int saleNumber;
    private double value;

    public Venda() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.println("Valor total da venda:");
        double value = keyboard.nextDouble();
        
        this.saleNumber = ++Venda.salesAmount;
        this.value = value;
    }

    // ----------------- Getters -----------------

    public int getSaleNumber() {
        return this.saleNumber;
    }

    public double getValue() {
        return this.value;
    }
}
