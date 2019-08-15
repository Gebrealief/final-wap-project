package controller;

import com.google.gson.Gson;
import data.access.Cart;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/order")
public class ShoppingCartController extends HttpServlet {
    private Product product = new Product();
//    private Item item = new Item();
    private Gson gson = new Gson();
    private Cart items;
    @Override
    public void init() throws ServletException {
        items = Cart.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("items", items.getAllItems());
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        // get the ordered item
        Product product = gson.fromJson(req.getParameter("item"), Product.class);
        items.addItem(product);
        // if session present add item to session item
        if (session.getAttribute("cart") == null) {
            List<Product> cart = new ArrayList<>();
            cart.add(product);
            session.setAttribute("cart", cart);
        } else {
            List<Product> cart = (List<Product>) session.getAttribute("cart");
            cart.add(product);
            session.setAttribute("cart", cart);
        }
        // total price
        if(session.getAttribute("total") == null){
            session.setAttribute("total", Double.parseDouble(product.getPrice()));
        }
        else {
            double total_price = Double.parseDouble(product.getPrice()) +
                    (Double)session.getAttribute("total");
            session.setAttribute("total", total_price);
        }
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(product));

    }
}
