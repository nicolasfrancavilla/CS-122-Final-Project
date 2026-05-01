
public class Drinks extends Menu {

    public Drinks(String item, float price, int calories,boolean isVegan,boolean hasGluten){
            super(item, price, calories,isVegan,hasGluten);
    }

    @Override
    public String toString() {
        return ("Item: " + item + " Price: " + price + " Calories: " + calories);
    }
}
