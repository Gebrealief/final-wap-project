package data.access;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private static Cart ourInstance = new Cart();

    public static Cart getInstance() {
        return ourInstance;
    }

    private Cart() {
    }
    Map<Integer, Product> items = new HashMap<>();

    {
//        items.put(1, new Product(1, "iPohne", "New with amazing look.", 3,"549"));
//        items.put(2, new Product(2, "LG G6","High quality camera", 4, "650"));

    }

    public void addItem(Product product){
        items.put(product.getId(), product);
    }

    public void deleteItem(int productId){
        items.remove(productId);
    }

    public void updateItem(Product product){
        items.put(product.getId(), product);
    }

    public List<Product> getAllItems(){
        return new ArrayList<>(items.values());
    }

    public Product getItemById(int productId){
        return items.get(productId);
    }

    public int genId() {
        return items.size()+1;
    }
}
