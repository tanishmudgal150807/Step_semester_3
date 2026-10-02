import java.util.Arrays;

class Candidate implements Comparable<Candidate> {

    String name;
    double cgpa;
    int codingScore;

    Candidate(String name, double cgpa, int codingScore) {
        this.name = name;
        this.cgpa = cgpa;
        this.codingScore = codingScore;
    }

    double compositeScore() {
        return cgpa * 10 + codingScore;
    }

    @Override
    public int compareTo(Candidate other) {
        return Double.compare(other.compositeScore(), this.compositeScore());
    }

    static boolean isEligible(double cgpa) {
        return cgpa >= 7.5;
    }

    static boolean isEligible(double cgpa, int codingScore) {
        return cgpa >= 6.5 && codingScore >= 60;
    }

    static String shortlistAndRank(Candidate[] candidates) {

        Candidate[] shortlisted = new Candidate[candidates.length];

        int count = 0;

        for (Candidate candidate : candidates) {

            if (isEligible(candidate.cgpa) ||
                isEligible(candidate.cgpa, candidate.codingScore)) {

                shortlisted[count] = candidate;
                count++;
            }
        }

        shortlisted = Arrays.copyOf(shortlisted, count);

        Arrays.sort(shortlisted);

        String result = "";

        for (int i = 0; i < shortlisted.length; i++) {

            result += (i + 1) + ". "
                    + shortlisted[i].name
                    + " ("
                    + shortlisted[i].compositeScore()
                    + ")";

            if (i < shortlisted.length - 1) {
                result += " | ";
            }
        }

        return result;
    }
}

public class q5 {

    public static void main(String[] args) {

        Candidate[] candidates = {
            new Candidate("Aisha", 8.2, 40),
            new Candidate("Rohit", 6.8, 65),
            new Candidate("Meena", 6.0, 90),
            new Candidate("Karan", 7.5, 20)
        };

        System.out.println(
            Candidate.shortlistAndRank(candidates)
        );
    }
}