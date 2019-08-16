package controller;

import data.access.Cart;
import data.access.ProductDB;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {
    private ProductDB products;
    private Cart items;

    @Override
    public void init() throws ServletException {
        products =  ProductDB.getInstance();
        items = Cart.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("checkout");
        System.out.println("action="+action);
        List<Product> itemsInCart = items.getAllItems();
        if("checkout".equals(action.substring(1,action.length()-1))){
            //update store and clear items from cart
            System.out.println("inside if");
            for (Product p : itemsInCart){
                int id = p.getId();
                Product inStore = products.getProductById(id);
                int rest = inStore.getQuantity() - p.getQuantity();
                if(rest <= 0){
                    //delete product from store
                    products.deleteProduct(id);
                }
                else {
                    inStore.setQuantity(rest);
                }
                //clear item from cart
                items.deleteItem(id);
            }
        } else{
            System.out.println(action + " is not equal to checkout");
        }
        req.setAttribute("products", products.getAllProducts());
        session.setAttribute("cart", items.getAllItems());
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.print("confirmed");
    }
}
