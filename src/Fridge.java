import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.parser.Tag;
import org.junit.Test;

public class Fridge extends ArrayList<Food> {
    public Fridge() {
        super();
    }

    public Food searchID(String foodID) {
        foodID = foodID.trim().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getFoodID().equals(foodID)) {
                return this.get(i);
            }
        }
        return null;
    }

    private boolean ifIDIsDuplicate(String foodID) {
        foodID = foodID.trim().toUpperCase();
        return searchID(foodID) != null;
    }

    public void addNewFood() throws IOException {
        String newFoodID, newFoodName, newFoodType, newFridgePlace = "";
        LocalDate newExpiryDate;

        double newFoodWeight;
        int fridgePlaceInt = 0;
        boolean idDuplicate = false;
        do {
            newFoodID = ValidatePackage.inputID("Please input your food ID (FXXX):", "^[F|f]\\d{3}$",
                    "ID must not be null and must follow the format!");
            idDuplicate = ifIDIsDuplicate(newFoodID);
            if (idDuplicate) {
                System.out.println("This food ID has already existed!");
            }
        } while (idDuplicate == true);
        newFoodName = ValidatePackage.inputFoodName("Please input a food name:",
                "Food name is not valid or you have null value!");
        newFoodWeight = ValidatePackage.getDouble(
                "Enter your food weight (input must be greater than 0 and less than 50kg):", "Your input is invalid", 0,
                50);
        newFoodType = ValidatePackage.inputString("Enter your food type:", "Food type cannot be null!");
        System.out.println();
        fridgePlaceInt = ValidatePackage.getAnInteger(
                "Enter place to store food following the guildline below:\n1.Top shelf\n2.Middle shelf\n3.Bottom shelf\n4.Vegetable drawer\n5.Door site\n\nYour choice:",
                "Your choice is invalid\n", 1, 5);
        switch (fridgePlaceInt) {
            case 1:
                newFridgePlace = "Top shelf";
                break;
            case 2:
                newFridgePlace = "Middle shelf";
                break;
            case 3:
                newFridgePlace = "Bottom shelf";
                break;
            case 4:
                newFridgePlace = "Vegetable drawer";
                break;
            case 5:
                newFridgePlace = "Door site";
                break;
        }
        newExpiryDate = ValidatePackage.inputDate("Please enter expiry date (Must be after today):", "Invalid date!",
                "Expiry date must be after today!");
        Food newFood = new Food(newFoodID, newFoodName, newFoodWeight, newFoodType, newFridgePlace, newExpiryDate);
        this.add(newFood);
        System.out.println("Food added sucessfully");
    }

    // Testing function
    public void searchFood2(String foodName) {
        // ArrayList<Food> f = new ArrayList<Food>();
        // Food f1;
        boolean checkExist = false;
        if (this.isEmpty()) {
            System.out.println("No food!");
        } else {
            if (!foodName.contains(" ")) {
                for (int i = 0; i < this.size(); i++) {
                    if (this.get(i).getFoodName().toLowerCase().contains(foodName.toLowerCase())) {
                        System.out.format("|%-8s|%-20s|%-8s|%-8s|%-16s|%-15s|", this.get(i).getFoodID(),
                                toTittle(this.get(i).getFoodName()), this.get(i).getFoodWeight(),
                                toTittle(this.get(i).getFoodType()), this.get(i).getFridgePlace(),
                                this.get(i).getExpiryDateString());
                        System.out.println();
                        checkExist = true;
                    }
                }
                if (checkExist == false)
                    System.out.println("Not found");
            } else {
                String[] tempArray = foodName.split(" ", foodName.length() + 1);
                ArrayList<String> tempArrayList = new ArrayList<String>();
                for (String temp : tempArray) {
                    tempArrayList.add(temp.toLowerCase());
                }
                for (int i = 0; i < this.size(); i++) {
                    if (ifContains(this.get(i).getFoodName().toLowerCase(), tempArrayList)) {
                        System.out.format("|%-8s|%-20s|%-8s|%-8s|%-16s|%-15s|", this.get(i).getFoodID(),
                                toTittle(this.get(i).getFoodName()), this.get(i).getFoodWeight(),
                                toTittle(this.get(i).getFoodType()), this.get(i).getFridgePlace(),
                                this.get(i).getExpiryDateString());
                        System.out.println();
                        checkExist = true;
                    }
                }
                if (checkExist == false)
                    System.out.println("Not found");
            }
        }
    }

    public void searchFood() {
        // ArrayList<Food> f = new ArrayList<Food>();
        // Food f1;
        boolean checkExist = false;
        if (this.isEmpty()) {
            System.out.println("No food!");
        } else {
            String foodName = ValidatePackage.inputString("Input food you want to search:",
                    "Food name cannot be null!");
            if (!foodName.contains(" ")) {
                for (int i = 0; i < this.size(); i++) {
                    if (this.get(i).getFoodName().toLowerCase().contains(foodName.toLowerCase())) {
                        System.out.format("|%-8s|%-20s|%-8s|%-11s|%-16s|%-15s|", this.get(i).getFoodID(),
                                toTittle(this.get(i).getFoodName()), this.get(i).getFoodWeight(),
                                toTittle(this.get(i).getFoodType()), this.get(i).getFridgePlace(),
                                this.get(i).getExpiryDateString());
                        System.out.println();
                        checkExist = true;
                    }
                }
                if (checkExist == false)
                    System.out.println("Not found");
            } else {
                String[] tempArray = foodName.split(" ", foodName.length() + 1);
                ArrayList<String> tempArrayList = new ArrayList<String>();
                for (String temp : tempArray) {
                    tempArrayList.add(temp.toLowerCase());
                }
                for (int i = 0; i < this.size(); i++) {
                    if (ifContains(this.get(i).getFoodName().toLowerCase(), tempArrayList)) {
                        System.out.format("|%-8s|%-20s|%-8s|%-11s|%-16s|%-15s|", this.get(i).getFoodID(),
                                toTittle(this.get(i).getFoodName()), this.get(i).getFoodWeight(),
                                toTittle(this.get(i).getFoodType()), this.get(i).getFridgePlace(),
                                this.get(i).getExpiryDateString());
                        System.out.println();
                        checkExist = true;
                    }
                }
                if (checkExist == false)
                    System.out.println("Not found");
            }
        }
    }

    public void removeFoodByID() {
        if (this.isEmpty())
            System.out.println("Empty list!");
        else {
            String removedCode = ValidatePackage.inputID("Please input removed food ID (FXXX):", "^[F|f]\\d{3}$",
                    "ID must not be null and must follow the format!");
            Food food = this.searchID(removedCode);
            if (food == null) {
                System.out.println(removedCode + " doesn't exist");
            } else {
                String checkContinue = ValidatePackage.inputPattern("\nDo you want to removed this food? (Y/N):",
                        "^[y|n]", "Your input is not valid!");
                if (checkContinue.equals("y")) {
                    this.remove(food);
                    System.out.println(removedCode + " has been removed");
                }
            }
        }
    }

    // Testing function
    public void removeFoodByID2(String removedCode) {
        if (this.isEmpty())
            System.out.println("Empty list!");
        else {
            Food food = this.searchID(removedCode);
            if (food == null) {
                System.out.println(removedCode + " doesn't exist");
            } else {
                this.remove(food);
            }
        }
    }

    public void printAll() {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        for (int i = 0; i < this.size(); i++) {
            for (int j = i; j < this.size(); j++) {
                if (this.get(i).getExpiryDate().isBefore(this.get(j).getExpiryDate())) {
                    Collections.swap(this, i, j);
                }
            }
        }
        System.out.println("Your fridge contains:");
        System.out.format("|%-8s|%-20s|%-8s|%-11s|%-16s|%-15s|", "Food ID", "Food Name", "Weight", "Type", "Place",
                "Expiry date");
        System.out.println();
        showFood(this);
    }

    public void showFood(ArrayList<Food> a) {
        for (int i = 0; i < a.size(); i++) {
            for (int j = i; j < a.size(); j++) {
                if (a.get(i).getExpiryDate().isBefore(a.get(j).getExpiryDate())) {
                    Collections.swap(a, i, j);
                }
            }
        }
        for (int i = 0; i < a.size(); i++) {
            System.out.format("|%-8s|%-20s|%-8s|%-11s|%-16s|%-15s|", a.get(i).getFoodID(),
                    toTittle(a.get(i).getFoodName()), a.get(i).getFoodWeight(), toTittle(a.get(i).getFoodType()),
                    a.get(i).getFridgePlace(), a.get(i).getExpiryDateString());
            System.out.println();
        }
    }

    public boolean ifContains(String inputString, ArrayList<String> b) {
        List<String> inputStringList = Arrays.asList(inputString.split(" "));
        return inputStringList.containsAll(b);
    }

    public static String toTittle(String text) {
        return WordUtils.capitalizeFully(text);
    }

    public void saveToFile(String fName) {
        if (this.size() == 0) {
            System.out.println("Empty list");
            return;
        }
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Food x : this) {
                pw.println(x.getFoodID() + "," + toTittle(x.getFoodName()) + "," + x.getFoodWeight() + ","
                        + toTittle(x.getFoodType()) + "," + x.getFridgePlace() + "," + x.getExpiryDateString());
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String checkFoodExistence(String foodName) throws IOException {
        Connection.Response rawDoc = Jsoup.connect(
                "https://www.oxfordreference.com/view/10.1093/acref/9780192803511.001.0001/acref-9780192803511?btog=chap&hide=true&jumpTo=fish&page=25&pageSize=20&skipEditions=true&sort=titlesort&source=%2F10.1093%2Facref%2F9780192803511.001.0001%2Facref-9780192803511")
                .timeout(30000).method(Connection.Method.GET).execute();

        Document doc = rawDoc.parse();
        Element form = doc.select("form#jumpToForm").first();
        FormElement f = (FormElement) form;
        Element foodSearch = form.select("[value=fish]").first();
        foodSearch.val(foodName);

        Document searchResult = f.submit().cookies(rawDoc.cookies()).post();
        Element rawResult = searchResult.selectFirst("h2.itemTitle > a");
        Tag finalResult = Tag.valueOf(rawResult.text());
        return finalResult.toString();
    }

    public void sortByAlphabet(ArrayList<Food> foodList) {
        Comparator nameSort = new Comparator<Food>() {
            public int compare(Food arg0, Food arg1) {
                return arg0.getFoodName().compareToIgnoreCase(arg1.getFoodName());
            }
        };
        Collections.sort(foodList, nameSort);
        for (int i = 0; i < foodList.size(); i++) {
            System.out.format("|%-8s|%-20s|%-8s|%-11s|%-16s|%-15s|", foodList.get(i).getFoodID(),
                    toTittle(foodList.get(i).getFoodName()), foodList.get(i).getFoodWeight(),
                    toTittle(foodList.get(i).getFoodType()), foodList.get(i).getFridgePlace(),
                    foodList.get(i).getExpiryDateString());
            System.out.println();
        }
    }

    @Test
    public void testAll() {
        Fridge fridge = new Fridge();
        Food f = new Food("F123", "D", 12, "asdv", "Door", LocalDate.parse("2022-04-07"));
        Food f1 = new Food("F124", "Cheese", 12, "asdv", "Door", LocalDate.parse("2022-03-07"));
        Food f2 = new Food("F125", "Butter", 12, "asdv", "Door", LocalDate.parse("2022-05-07"));
        Food f3 = new Food("F126", "Avocado", 12, "asdv", "Door", LocalDate.parse("2022-01-07"));
        fridge.add(f);
        fridge.add(f1);
        fridge.add(f2);
        fridge.add(f3);
        fridge.showFood(fridge);
        System.out.println();
        // fridge.searchFood2("red fish");
        // System.out.println();
        // fridge.searchFood2("abcas");
        // System.out.println();
        // fridge.searchFood2("ToMaTO");
        // System.out.println();
        // fridge.removeFoodByID2("F125");
        // System.out.println();
        // fridge.showFood(fridge);
        fridge.sortByAlphabet(fridge);
    }
}
