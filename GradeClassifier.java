import java.util.Scanner;
public class GradeClassifier {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int marks = sc.nextInt();
        int attendance = sc.nextInt();
        boolean eligible = attendance >= 75 && marks >= 40;
        if (!eligible) {
            System.out.println("Detained");
        } else if (marks >= 90) {
            System.out.println("A");
        } else if (marks >= 75) {
            System.out.println("B");
        } else if (marks >= 60) {
            System.out.println("C");
        } else {
            System.out.println("D");
        }
        sc.close();
    }
}
