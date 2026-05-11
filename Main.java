import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, ArrayList<Menu>> menuOptions = new LinkedHashMap<>();
        menuOptions.put("Appetizer", new ArrayList<>());
        menuOptions.put("Entree", new ArrayList<>());
        menuOptions.put("Dessert", new ArrayList<>());
        menuOptions.put("Drinks", new ArrayList<>());

        fileWriterThing(menuOptions);

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- MAIN MENU ---");

            ArrayList<String> categories = new ArrayList<>(menuOptions.keySet());
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i));
            }
            System.out.println((categories.size() + 1) + ". Exit");
            System.out.print("Select category: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice >= 1 && choice <= categories.size()) {
                String selectedCategory = categories.get(choice - 1);
                subMenu(scanner, selectedCategory, menuOptions.get(selectedCategory));
            } else if (choice == categories.size() + 1) {
                fileSaveThing(menuOptions);
                isRunning = false;
            } else {
                System.out.println("Invalid choice");
            }
        }

        scanner.close();
    }

    public static void subMenu(Scanner scanner, String categoryName, ArrayList<Menu> items) {
        boolean inSubMenu = true;

        while (inSubMenu) {
            System.out.println("\n--- " + categoryName.toUpperCase() + " ---");

            if (items.isEmpty()) {
                System.out.println("No items yet.");
            } else {
                for (Menu item : items) {
                    System.out.println("- " + item);
                }
            }

            System.out.println("\n1. Add Item");
            System.out.println("2. Back");
            System.out.print("Choice: ");

            int subChoice = scanner.nextInt();
            scanner.nextLine();

            if (subChoice == 1) {
                System.out.print("Enter item name: ");
                String itemName = scanner.nextLine();

                System.out.print("Enter price: ");
                float price = scanner.nextFloat();

                System.out.print("Enter calories: ");
                int calories = scanner.nextInt();

                System.out.print("Is it vegan (true/false): ");
                boolean isVegan = scanner.nextBoolean();

                System.out.print("Is it gluten free (true/false): ");
                boolean hasGluten = scanner.nextBoolean();
                scanner.nextLine();

                Menu newItem;

                switch (categoryName) {
                    case "Appetizer":
                        newItem = new Appetizers(itemName, price, calories, isVegan, hasGluten);
                        break;
                    case "Entree":
                        newItem = new Entrees(itemName, price, calories, isVegan, hasGluten);
                        break;
                    case "Dessert":
                        newItem = new Desserts(itemName, price, calories, isVegan, hasGluten);
                        break;
                    case "Drinks":
                        newItem = new Drinks(itemName, price, calories, isVegan, hasGluten);
                        break;
                    default:
                        newItem = new Menu(itemName, price, calories, isVegan, hasGluten);
                }

                items.add(newItem);
                System.out.println("Added!");

            } else if (subChoice == 2) {
                inSubMenu = false;
            }
        }
    }


    public static void fileWriterThing(Map<String, ArrayList<Menu>> menu) {
        File file = new File("menu.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String currentCategory = "";

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("CATEGORY: ")) {
                    currentCategory = line.substring(10);
                } else if (!currentCategory.isEmpty()) {
                    String[] parts = line.split(",");
                    String name = parts[0];
                    float price = Float.parseFloat(parts[1]);
                    int calories = Integer.parseInt(parts[2]);
                    boolean vegan = Boolean.parseBoolean(parts[3]);
                    boolean gluten = Boolean.parseBoolean(parts[4]);

                    Menu item = new Menu(name, price, calories, vegan, gluten);
                    menu.get(currentCategory).add(item);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading menu: " + e.getMessage());
        }
    }

    public static void fileSaveThing(Map<String, ArrayList<Menu>> menu) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("menu.txt"))) {
            for (String category : menu.keySet()) {
            writer.println("CATEGORY: " + category);
                for (Menu item : menu.get(category)) {
                    writer.println(item.toString());
                }
            }
            System.out.println("Menu saved.");
        } catch (IOException e) {
            System.out.println("Error saving menu: " + e.getMessage());
        }
    }
}

