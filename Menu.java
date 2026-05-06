
public class Menu {

    protected String item;
    protected float price;
    protected int calories;
    protected boolean isVegan;
    protected boolean hasGluten;

    public Menu(String item, float price, int calories, boolean isVegan, boolean hasGluten) {
        this.item = item;
        this.price = price;
        this.calories = calories;
    }

    public String getItem() {
        return item;
    }

    public float getPrice() {
        return price;
    }

    public int getCal() {
        return calories;
    }

    public boolean getIsVegan() {
        return isVegan;
    }

    public void setIsVegan(boolean vegan) {
        isVegan = vegan;
    }

    public void sethasGluten(boolean gluten) {
        hasGluten = gluten;
    }

    @Override
    public String toString() {
        return ("Item: " + item + " Price: " + price + " Calories: " + calories + " Vegan: " + isVegan + " Gluten: "
                + hasGluten);
    }
}
