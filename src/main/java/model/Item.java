package model;

public class Item {
    private Product p = new Product();
    private int quantity;

    public Item(Product p, int quantity) {
        this.p = p;
        this.quantity = quantity;
    }

    public Item() {
    }

    public Product getP() {
        return p;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
