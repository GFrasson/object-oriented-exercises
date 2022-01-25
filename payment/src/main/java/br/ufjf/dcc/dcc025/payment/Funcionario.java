package br.ufjf.dcc.dcc025.payment;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public abstract class Funcionario {
    private static List<Funcionario> employees = new ArrayList<>();
    private String name;
    private double dailySalary;

    public Funcionario() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println();
        System.out.println("Nome:");
        String name = keyboard.nextLine();

        System.out.println();
        System.out.println("Salário diário:");
        double dailySalary = keyboard.nextDouble();

        this.name = name;
        this.dailySalary = dailySalary;
        Funcionario.employees.add(this);
    }

    // ----------------- Getters -----------------

    public String getName() {
        return this.name;
    }

    public double getDailySalary() {
        return this.dailySalary;
    }

    public static List<Funcionario> getEmployees() {
        return Funcionario.employees;
    }

    // ----------------- Methods -----------------

    protected abstract double calculateComission();

    public static void displayAll() {
        System.out.println("Funcionários");
        for (Funcionario employee : Funcionario.employees) {
            System.out.println(employee.getName());
        }
    }
}
