package br.ufjf.dcc.dcc025.payment;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class PaymentSystem {

    public PaymentSystem() {

    }

    public void execute() {
        while (true) {
            int option = PaymentSystem.chooseOption();
            this.executeOption(option);
        }
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("Escolha uma opção:");
        System.out.println("[1] Cadastrar Vendedor;");
        System.out.println("[2] Cadastrar Gerente;");
        System.out.println("[3] Associar um Vendedor a um Gerente;");
        System.out.println("[4] Registrar as vendas realizadas por um Vendedor;");
        System.out.println("[5] Calcular o valor que será pago a cada Funcionário no final do dia;");
        System.out.println("[6] Sair.");
    }

    private static int chooseOption() {
        int option;
        List<Integer> availableOptions = new ArrayList<>(
            Arrays.asList(1, 2, 3, 4, 5, 6)
        );

        do {
            PaymentSystem.displayMenu();
            Scanner keyboard = new Scanner(System.in);
            option = keyboard.nextInt();
        } while (!availableOptions.contains(option));

        return option;
    }

    private void executeOption(int option) {
        switch (option) {
            // Cadastrar Vendedor
            case 1:
                this.registerSeller();
                break;

            // Cadastrar Gerente
            case 2:
                this.registerManager();
                break;

            // Associar um vendedor a um gerente
            case 3:
                this.associateSellerToManager();
                break;

            // Registrar as vendas realizadas por um vendedor
            case 4:
                this.registerSales();
                break;

            // Calcular o valor que será pago a cada Funcionário no final do dia
            case 5:
                this.displayDailyPayment();
                break;
            
            // Sair
            case 6:
                System.exit(0);
                break;
        
            default:
                break;
        }
    }

    private void registerSeller() {
        new Vendedor();
    }

    private void registerManager() {
        new Gerente();
    }

    private void associateSellerToManager() {
        Vendedor.displayAll();
        Vendedor chosenSeller = Vendedor.select();
        Gerente.displayAll();
        Gerente chosenManager = Gerente.select();

        chosenManager.associateSeller(chosenSeller);
    }

    private void registerSales() {
        Vendedor.displayAll();
        Vendedor chosenSeller = Vendedor.select();
        chosenSeller.addSale();
    }

    private Map<Funcionario, Double> calculateDailyPayment() {
        Map<Funcionario, Double> paymentTable = new HashMap<>();

        for (Funcionario employee : Funcionario.getEmployees()) {
            paymentTable.put(employee, employee.calculateComission());
        }

        return paymentTable;
    }

    private void displayDailyPayment() {
        Map<Funcionario, Double> paymentTable = this.calculateDailyPayment();

        for (Map.Entry<Funcionario, Double> employeePayment : paymentTable.entrySet()) {
            System.out.println();
            System.out.println("Nome: " + employeePayment.getKey().getName() + " ----- " + "Valor do dia: " + employeePayment.getValue());
        }
    }
}
