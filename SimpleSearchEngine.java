import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SimpleSearchEngine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> index = new HashMap<>();

        // Index some example documents
        indexDocument(index, "document1", "This is the first document.");
        indexDocument(index, "document2", "The second document is here.");
        indexDocument(index, "document3", "This is the third document.");

        while (true) {
            System.out.print("Enter a search query (or 'exit' to quit): ");
            String query = scanner.nextLine();

            if (query.equalsIgnoreCase("exit")) {
                break;
            }

            List<String> results = search(index, query);
            if (results.isEmpty()) {
                System.out.println("No results found.");
            } else {
                System.out.println("Search Results:");
                for (String result : results) {
                    System.out.println(result);
                }
            }
        }

        scanner.close();
    }

    // Index a document by splitting it into words and adding to the index
    private static void indexDocument(Map<String, List<String>> index, String documentName, String content) {
        String[] words = content.split("\\s+");
        for (String word : words) {
            word = word.toLowerCase(); // Normalize to lowercase
            if (!index.containsKey(word)) {
                index.put(word, new ArrayList<>());
            }
            index.get(word).add(documentName);
        }
    }

    // Search for documents containing the query
    private static List<String> search(Map<String, List<String>> index, String query) {
        List<String> results = new ArrayList<>();
        String[] queryWords = query.split("\\s+");

        for (String queryWord : queryWords) {
            queryWord = queryWord.toLowerCase(); // Normalize to lowercase
            if (index.containsKey(queryWord)) {
                results.addAll(index.get(queryWord));
            }
        }

        return results;
    }
}
