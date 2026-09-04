import java.util.HashMap;
import java.util.Map;

public class Q5_WordFrequencyReport {

    static void printFilteredWordFrequency(String feedback) {

        // Convert to lowercase
        feedback = feedback.toLowerCase();

        // Remove punctuation
        feedback = feedback.replace(".", "");
        feedback = feedback.replace(",", "");

        // Split into words
        String[] words = feedback.split("\\s+");

        // Stop words
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        HashMap<String, Integer> frequency = new HashMap<>();

        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            boolean isStopWord = false;

            // Check whether word is a stop word
            for (int j = 0; j < stopWords.length; j++) {

                if (word.equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }

            // Skip stop words
            if (isStopWord) {
                continue;
            }

            // Count frequency
            if (frequency.containsKey(word)) {
                frequency.put(word, frequency.get(word) + 1);
            } else {
                frequency.put(word, 1);
            }
        }

        // Convert map entries into an array
        Map.Entry<String, Integer>[] entries =
                frequency.entrySet().toArray(new Map.Entry[0]);

        // Sort by frequency in descending order
        for (int i = 0; i < entries.length - 1; i++) {

            for (int j = i + 1; j < entries.length; j++) {

                if (entries[i].getValue() < entries[j].getValue()) {

                    Map.Entry<String, Integer> temp = entries[i];
                    entries[i] = entries[j];
                    entries[j] = temp;
                }
            }
        }

        // Print result
        for (int i = 0; i < entries.length; i++) {
            System.out.println(
                entries[i].getKey() + ": " + entries[i].getValue()
            );
        }
    }

    public static void main(String[] args) {

        String feedback =
                "The mentor was great, the session was great and clear.";

        printFilteredWordFrequency(feedback);
    }
}