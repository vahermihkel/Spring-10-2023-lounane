package ee.mihkel.api.entity;

public class Product {
    public int id;
    public String name;
    public double price;
    public boolean active;

    public Product() {
    }

    public Product(String name, double price) {
        this.id = (int) (Math.random() * 9);
        this.name = name;
        this.active = true;
    }

    // constructor overloading
}
