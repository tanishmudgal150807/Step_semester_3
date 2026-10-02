import java.util.*;

interface Room {
    double calculateBill(int units);
}

class SingleRoom implements Room {
    public double calculateBill(int units) {
        return units * 8;
    }
}

class SharedRoom implements Room {
    private int occupants;

    SharedRoom(int occupants) {
        this.occupants = occupants;
    }

    public double calculateBill(int units) {
        return (units * 6) / occupants;
    }
}

class ACRoom implements Room {
    public double calculateBill(int units) {
        return units * 10 + 200;
    }
}

public class M3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {

            String type = sc.next();
            int units = sc.nextInt();

            Room room;

            if (type.equals("SINGLE")) {
                room = new SingleRoom();
            } else if (type.equals("SHARED")) {
                int occupants = sc.nextInt();
                room = new SharedRoom(occupants);
            } else {
                room = new ACRoom();
            }

            double bill = room.calculateBill(units);

            System.out.printf("%s: %.2f%n", type, bill);

            total += bill;
        }

        System.out.printf("Total: %.2f%n", total);
    }
}