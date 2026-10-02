import java.util.*;
import java.util.function.Function;

interface Transport {
    double calculateFare();
}

class Bus implements Transport {
    private double distance;

    Bus(double distance) {
        this.distance = distance;
    }

    public double calculateFare() {
        return Math.min(2 + 0.10 * distance, 10);
    }
}

class Train implements Transport {
    private double distance;

    Train(double distance) {
        this.distance = distance;
    }

    public double calculateFare() {
        return 3 + 0.15 * distance;
    }
}

class Metro implements Transport {
    private double distance;
    private double peakFactor;

    Metro(double distance, double peakFactor) {
        this.distance = distance;
        this.peakFactor = peakFactor;
    }

    public double calculateFare() {
        return (1.50 + 0.20 * distance) * peakFactor;
    }
}

public class q5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Function<double[], Transport>> factory = Map.of(
            "BUS", a -> new Bus(a[0]),
            "TRAIN", a -> new Train(a[0]),
            "METRO", a -> new Metro(a[0], a[1])
        );

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();

            double distance = sc.nextDouble();
            double peakFactor = 1;

            if (type.equals("METRO")) {
                peakFactor = sc.nextDouble();
            }

            Transport transport =
                factory.get(type).apply(
                    new double[]{distance, peakFactor}
                );

            double fare = transport.calculateFare();

            total += fare;

            System.out.printf("%s: %.2f%n", type, fare);
        }

        System.out.printf("Total: %.2f%n", total);
    }
}