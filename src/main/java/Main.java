import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {
    @FunctionalInterface
    public interface DictionaryGenerator {
        List<String> generateDictionary(String str);
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileReader file = new FileReader("src\\main\\resources\\source_text");
        Scanner scanner = new Scanner(file);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine()).append("\n");
        }

        String str = new String(stringBuilder);
        DictionaryGenerator dictionaryGenerator = str1 -> Arrays.stream(str1.split("\\W+"))
                .distinct()
                .map(String::toLowerCase)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        printDictionary(dictionaryGenerator, str);
    }

    static void printDictionary(DictionaryGenerator dictionaryGenerator, String str) {
        dictionaryGenerator.generateDictionary(str).forEach(System.out::println);
    }
}
