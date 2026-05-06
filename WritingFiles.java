
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class WritingFiles {

    public void fileThing(Map<String, ArrayList<String>> menu) {
        File file = new File("menu.txt");
        if (!(file.exists())) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String currentCategory = "";

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("CATEGORY: ")) {
                    currentCategory = line.substring(9);
                    menu.putIfAbsent(currentCategory, new ArrayList<>());
                } else if (!(currentCategory.isEmpty())) {
                    menu.get(currentCategory).add(line);
                }

            }
        } catch (IOException e) {
            System.out.println("Error loading menu" + e.getMessage());
        }

    }

    public static void main(String[] args) {

    }
}
