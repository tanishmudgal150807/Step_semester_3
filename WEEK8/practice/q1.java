import java.util.*;
import java.util.function.Function;

interface Payment {
    double calculateAmount();
}

class Card implements Payment {
    private double amount;

    Card(double amount) {
        this.amount = amount;
    }

    public double calculateAmount() {
        return amount + amount * 0.02;
    }
}

class Wallet implements Payment {
    private double amount;

    Wallet(double amount) {
        this.amount = amount;
    }

    public double calculateAmount() {
        return amount + amount * 0.01;
    }
}

class BankTransfer implements Payment {
    private double amount;

    BankTransfer(double amount) {
        this.amount = amount;
    }

    public double calculateAmount() {
        return amount;
    }
}

public class q1{
    static Map<String, Function<Double, Payment>> factory = Map.of(
        "CARD", Card::new,
        "WALLET", Wallet::new,
        "BANKTRANSFER", BankTransfer::new
    );

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();

            Payment payment = factory.get(type).apply(amount);

            double adjusted = payment.calculateAmount();
            total += adjusted;

            System.out.printf("%s: %.2f%n", type, adjusted);
        }

        System.out.printf("Total: %.2f%n", total);
    }
}