import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Welcome to Word Counter!");

            System.out.println("Enter '1' to input text or '2' to provide a file path:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            String text = "";
            if (choice == 1) {
                System.out.println("Enter your text:");
                text = scanner.nextLine();
            } else if (choice == 2) {
                System.out.println("Enter the file path:");
                String filePath = scanner.nextLine();
                try {
                    text = readFile(filePath);
                } catch (IOException e) {
                    System.out.println("Error reading the file. Please check the file path and try again.");
                    return;
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
                return;
            }

            // Remove punctuation (optional)
            text = text.replaceAll("[^a-zA-Z ]", "");

            String[] words = text.split("\\s+"); // Splitting by whitespace
            int wordCount = words.length;

            // Ignoring common words (stop words)
            Set<String> stopWords = getStopWords();
            int nonStopWordCount = 0;
            for (String word : words) {
                if (!stopWords.contains(word.toLowerCase())) {
                    nonStopWordCount++;
                }
            }

            System.out.println("Total number of words (including stop words): " + wordCount);
            System.out.println("Total number of non-stop words: " + nonStopWordCount);

            // Additional statistics (frequency of each word)
            Map<String, Integer> wordFrequency = getWordFrequency(words);
            System.out.println("Word frequency:");
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // Unique word count
            System.out.println("Total number of unique words: " + wordFrequency.size());

        } finally {
            scanner.close(); // Close the scanner in a finally block to ensure it always happens
        }
    }

    // Function to read a file and return its content as a string
    public static String readFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        }
    }

    // Function to get a set of common stop words
    public static Set<String> getStopWords() {
        Set<String> stopWords = new HashSet<>();
        // Add common stop words here
        stopWords.add("the");
        stopWords.add("and");
        stopWords.add("is");
        // Add more as needed
        return stopWords;
    }

    // Function to get word frequency
    public static Map<String, Integer> getWordFrequency(String[] words) {
        Map<String, Integer> frequencyMap = new TreeMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        return frequencyMap;
    }
}
