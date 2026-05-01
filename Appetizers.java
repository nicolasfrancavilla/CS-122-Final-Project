public class Appetizers extends Menu {
     public Appetizers(String item, float price, int calories, boolean isVegan, boolean hasGluten){
        super(item, price, calories, isVegan, hasGluten);
    }

    @Override
    public String toString() {
        return ("Item: " + item + " Price: " + price + " Calories: " + calories + " Vegan: " + isVegan + " Gluten: " + hasGluten);
    }
}
