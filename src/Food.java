import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Food {
    private LocalDate expiryDate;
    private String foodID;
    private String foodName;
    private String foodType;
    private String fridgePlace;
    private double foodWeight;

    public Food() {

    }

    public Food(String foodID, String foodName, double foodWeight, String foodType, String fridgePlace,
            LocalDate expiryDate) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.foodType = foodType;
        this.fridgePlace = fridgePlace;
        this.expiryDate = expiryDate;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(double foodWeight) {
        this.foodWeight = foodWeight;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFridgePlace() {
        return fridgePlace;
    }

    public void setFridgePlace(String fridgePlace) {
        this.fridgePlace = fridgePlace;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExpiryDateString() {
        return expiryDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void showAll() {
        System.out.format("|%-8s|%-20s|%-8s|%-8s|%-16s|%-15s|", foodID, Fridge.toTittle(foodName), foodWeight,
                Fridge.toTittle(foodType), fridgePlace, getExpiryDateString());

    }
}
