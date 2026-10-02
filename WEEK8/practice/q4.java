import java.util.*;
import java.util.function.Function;

interface Question {
    double calculateScore();
}

class MCQ implements Question {
    private String correct, student;
    private int points;

    MCQ(String correct, String student, int points) {
        this.correct = correct;
        this.student = student;
        this.points = points;
    }

    public double calculateScore() {
        return student.equals(correct) ? points : 0;
    }
}

class TF implements Question {
    private String correct, student;
    private int points;

    TF(String correct, String student, int points) {
        this.correct = correct;
        this.student = student;
        this.points = points;
    }

    public double calculateScore() {
        return student.equals(correct) ? points : 0;
    }
}

class Essay implements Question {
    private String correct, student;
    private int points;

    Essay(String correct, String student, int points) {
        this.correct = correct;
        this.student = student;
        this.points = points;
    }

    public double calculateScore() {
        String answer = student.toLowerCase();
        String[] keywords = correct.toLowerCase().split(",");

        int count = 0;

        for (String keyword : keywords) {
            if (answer.contains(keyword.trim())) {
                count++;
            }
        }

        if (count >= 2)
            return points * 0.75;
        else if (count == 1)
            return points * 0.50;
        else
            return 0;
    }
}

public class q4 {

    static List<String> parse(String line) {
        List<String> result = new ArrayList<>();

        java.util.regex.Matcher m =
            java.util.regex.Pattern.compile("\"([^\"]*)\"|(\\S+)")
            .matcher(line);

        while (m.find()) {
            if (m.group(1) != null)
                result.add(m.group(1));
            else
                result.add(m.group(2));
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        double total = 0;

        Map<String, Function<String[], Question>> factory = Map.of(
            "MCQ", a -> new MCQ(a[1], a[2], Integer.parseInt(a[3])),
            "TF", a -> new TF(a[1], a[2], Integer.parseInt(a[3])),
            "ESSAY", a -> new Essay(a[1], a[2], Integer.parseInt(a[3]))
        );

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();

            List<String> data = parse(line);

            String type = data.get(0);

            String[] values = {
                type,
                data.get(2),   // Correct answer
                data.get(3),   // Student answer
                data.get(4)    // Points
            };

            Question question = factory.get(type).apply(values);

            double score = question.calculateScore();

            total += score;

            System.out.printf("%s: %.2f%n", type, score);
        }

        System.out.printf("Total Score: %.2f%n", total);
    }
}