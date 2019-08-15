package model;

public class Product {
    private int id;
    private String name;
    private String description;
    private int quantity;
    private String price;


    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Product(int id, String name, String description, int quantity, String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }
}
