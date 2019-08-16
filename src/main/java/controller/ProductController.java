package controller;

import com.google.gson.Gson;
import data.access.ProductDB;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Product",value = "/products")
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductDB products;
    Gson mapper = new Gson();

    @Override
    public void init() throws ServletException {
        products = ProductDB.getInstance();

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("products", products.getAllProducts());
        req.getSession().setAttribute("products", products.getAllProducts());
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name="", description="", price="0.0";
            int quantity=0, id;
            id= products.genId();
            try{
                name=request.getParameter("name");
                description= request.getParameter("description");
                quantity= Integer.parseInt(request.getParameter("quantity"));
                price= request.getParameter("price");

            }catch(Exception e) {
                resp.getWriter().println("Error Happend. This is message from SERVLET and with error in getParameter.");
            }

            Product product= new Product(id, name, description, quantity, price);
            products.addProduct(product);
            request.getSession().setAttribute("products", products.getAllProducts());
            PrintWriter out= resp.getWriter();
            out.print("You have successfully Added a Product.");
        }catch(Exception e) {
            resp.getWriter().println("Error Happend. This is message from SERVLET.");
        }

    }
}
