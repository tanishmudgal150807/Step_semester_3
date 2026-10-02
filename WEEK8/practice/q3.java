import java.util.*;
import java.util.function.Function;

interface Delivery {
    double calculateFee();
}

class Standard implements Delivery {
    private double weight, distance;

    Standard(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    public double calculateFee() {
        return 5 + 0.50 * weight + 0.10 * distance;
    }
}

class Express implements Delivery {
    private double weight, distance;

    Express(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    public double calculateFee() {
        return 15 + 1.00 * weight + 0.20 * distance;
    }
}

class International implements Delivery {
    private double weight, distance, customsFee;

    International(double weight, double distance, double customsFee) {
        this.weight = weight;
        this.distance = distance;
        this.customsFee = customsFee;
    }

    public double calculateFee() {
        return 25 + 2.00 * weight + 0.50 * distance + customsFee;
    }
}

public class q3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<double[], Delivery>> factory = Map.of(
            "STANDARD", a -> new Standard(a[0], a[1]),
            "EXPRESS", a -> new Express(a[0], a[1]),
            "INTERNATIONAL", a -> new International(a[0], a[1], a[2])
        );

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();

            double weight = sc.nextDouble();
            double distance = sc.nextDouble();

            double customs = 0;

            if (type.equals("INTERNATIONAL")) {
                customs = sc.nextDouble();
            }

            Delivery delivery =
                factory.get(type).apply(new double[]{weight, distance, customs});

            double fee = delivery.calculateFee();

            total += fee;

            System.out.printf("%s: %.2f%n", type, fee);
        }

        System.out.printf("Total: %.2f%n", total);
    }
}