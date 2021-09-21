import java.io.IOException;

public class FileManager {
    public static void main(String[] args) throws IOException {
        Fridge fridge = new Fridge();
        String checkContinue = "";
        int choice;
        System.out.println("Welcome to Food Management - @ 2021 by <SE160121 - Tran Hoang Minh Khoa>");
        do {
            System.out.println(
                    "\nSelect the options below:\n1.Add a new food\n2.Search food by name\n3.Remove food by ID\n4.Print all food in descending order of expiry date\n5.Sort by alphabet\n6.Quit\n");
            choice = ValidatePackage.getAnInteger("Enter your choice:", "invalid!", 1, 6);
            switch (choice) {
                case 1:
                    do {
                        fridge.addNewFood();
                        checkContinue = ValidatePackage.inputPattern("\nDo you want to continue:", "^[y|n]",
                                "Invalid!");
                    } while (checkContinue.equalsIgnoreCase("y"));
                    break;
                case 2:
                    do {
                        fridge.searchFood();
                        checkContinue = ValidatePackage.inputPattern("\nDo you want to continue:", "^[y|n]",
                                "Invalid!");
                    } while (checkContinue.equalsIgnoreCase("y"));
                    break;
                case 3:
                    fridge.removeFoodByID();
                    break;
                case 4:
                    fridge.printAll();
                    break;
                case 5:
                    fridge.sortByAlphabet(fridge);
                    break;
                case 6:
                    fridge.saveToFile("food.txt");
                    System.out.println("Goodbye!");
                    break;
            }
        } while (choice >= 1 && choice <= 6);
    }
}
