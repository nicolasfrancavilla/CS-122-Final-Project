
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class OptionSelector {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, ArrayList<String>> menuOptions = new LinkedHashMap<>();
        menuOptions.put("Appetizer", new ArrayList<>());
        menuOptions.put("Entree", new ArrayList<>());
        menuOptions.put("Dessert", new ArrayList<>());
        menuOptions.put("Drinks", new ArrayList<>());

        OptionSelector obj = new OptionSelector();
        obj.fileThing(menuOptions);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- Main Menu ---");

            ArrayList<String> categories = new ArrayList<>(menuOptions.keySet());
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i));
            }
            System.out.println((categories.size() + 1) + ". Exit ");
            System.out.print(" Select category: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice >= 1 && choice <= categories.size()) {
                String SelectedCategory = categories.get(choice - 1);
                handleSubMenu(scanner, SelectedCategory, menuOptions.get(SelectedCategory));
            } else if (choice == categories.size() + 1) {
                saveMenu(menuOptions);
                isRunning = false;
            } else {
                System.out.println("Invalid Choice");
            }
        }
        scanner.close();
    }

    public static void handleSubMenu(Scanner scanner, String categoryName, ArrayList<String> items) {
        boolean inSubMenu = true;
        while (inSubMenu) {
            System.out.println("\n--- " + categoryName.toUpperCase() + " ---");
            if (items.isEmpty()) {
                System.out.println("No items yet.");
            } else {
                for (String item : items) {
                    System.out.println("-" + item);
                }
            }

            System.out.println("\n1. Add Item to " + categoryName);
            System.out.println("2. Back to Main Menu");
            System.out.print("Choice: ");
            scanner.nextLine();

            // String itemName;
            // float price;
            // int calories; 
            // boolean isVegan;
            // boolean hasGluten;
            int subChoice = scanner.nextInt();

            if (subChoice == 1) {
                System.out.print("Enter item name: ");
                String itemName = scanner.nextLine();

                System.out.print("Enter item price: ");
                float price = scanner.nextFloat();

                System.out.print("Enter item calories: ");
                int calories = scanner.nextInt();

                System.out.print("Is the item vegan?: ");
                boolean isVegan = scanner.nextBoolean();

                System.out.print("Is the item gluten free?: ");
                boolean hasGluten = scanner.nextBoolean();

                items.add(Menu yes = new Menu(itemName, price, calories, isVegan, hasGluten)
                );
                System.out.println("Added!");
            } else if (subChoice == 2) {
                inSubMenu = false;

            }
        }
    }

    public void fileThing(Map<String, ArrayList<String>> menu) {
        File file = new File("menu.txt");
        if (file.exists()) {
            file.delete();
        } else {
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

    public static void saveMenu(Map<String, ArrayList<String>> menu) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("menu.txt"))) {
            for (String category : menu.keySet()) {
                writer.println("CATEGORY: " + category);
                for (String item : menu.get(category)) {
                    writer.println(item);
                }
            }
            System.out.println("Menu saved to file");

        } catch (IOException e) {
            System.out.println("Error saving menu: " + e.getMessage());
        }
    }

}
