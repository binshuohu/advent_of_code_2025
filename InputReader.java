import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {
    public static List<String> read(String path) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(path))) {
            List<String> lines = new ArrayList<>();
            while(scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            return lines;
        }
    }
}
