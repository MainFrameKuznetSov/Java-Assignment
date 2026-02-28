import java.util.ArrayList;
import java.util.List;

abstract class FoodItem {
    private int id;
    private String name;
    private double price;

    FoodItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract double calculateDiscount();

}

class VegItem extends FoodItem {
    public VegItem(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.1d;
    }

}

class NonVegItem extends FoodItem {
    public NonVegItem(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.05d;
    }

}

class Order {
    private List<FoodItem> list;

    public Order() {
        list = new ArrayList<>();
    }

    public void addFood(FoodItem food) {
        list.add(food);
    }

    public double calculateTotalBill() {
        double total = 0.0d;
        for (FoodItem food : list)
            total += food.getPrice() - food.calculateDiscount();
        return total;
    }

}

public class FoodItemDriver {
    public static void main(String[] args) {
        FoodItem f1 = new NonVegItem(1, "Chicken Biriyani", 120);
        FoodItem f2 = new NonVegItem(2, "Mutton Biriyani", 180);
        FoodItem f3 = new NonVegItem(3, "Chicken Chaap", 70);
        FoodItem f4 = new VegItem(4, "Paneer Pulao", 130);
        FoodItem f5 = new VegItem(5, "Paneer Kofta", 120);
        FoodItem f6 = new VegItem(6, "Butter Naan", 30);
        Order order = new Order();
        order.addFood(f1);
        order.addFood(f2);
        order.addFood(f3);
        order.addFood(f4);
        order.addFood(f5);
        order.addFood(f6);
        System.out.println("Total bill:- Rs. " + order.calculateTotalBill());
    }
}
