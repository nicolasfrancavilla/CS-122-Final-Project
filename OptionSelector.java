import java.util.ArrayList;
import java.util.Scanner;

public class OptionSelector {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            ArrayList<String> menuOptions = new ArrayList<>();
            menuOptions.add("View Menu");
            menuOptions.add("Appetizer");
            menuOptions.add("Entree");
            menuOptions.add("Dessert");
            menuOptions.add("Drinks");



            boolean isRunning = true;


            while(isRunning){
                System.out.println("\n--- Current Menu ---");
                for (int i = 0; i < menuOptions.size(); i ++){
                    System.out.println((i+1) + ". "+menuOptions.get(i));
            }
            System.out.println((menuOptions.size()+1) + ". Add new menu item: ");
            System.out.println((menuOptions.size()+2) + ". Exit ");
            System.out.print(" Select an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= menuOptions.size()){
                    System.out.println("Executing: " + menuOptions.get(choice - 1));
                } else if (choice == menuOptions.size() + 1){
                    System.out.print("Enter name for new option: ");
                    String newOption = scanner.nextLine();
                    menuOptions.add(newOption);

                }else if (choice == menuOptions.size() + 2){
                    isRunning = false;
                } else {
                    System.out.println("Invalid Choice");
                }
            }
            scanner.close();
        }
    }
        


       
        