package controller;

import data.access.ProductDB;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajaxProduct")
public class ProductAjaxServlet extends HttpServlet {
    private ProductDB products;
    //Gson mapper = new Gson();

    @Override
    public void init() throws ServletException {
        products = ProductDB.getInstance();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*        String name=request.getParameter("name"),
                description= request.getParameter("description");
        int quantity= Integer.parseInt(request.getParameter("quantity"));
        String price= request.getParameter("price");
        int id= products.genId();
        Product product= new Product(id, name, description, quantity, price);*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        out.print("<label>Name: </label> <input type=\"text\" name=\"product-name\" id=\"product-name\" required>");
        out.println("<br>");
        out.print("<label>Description</label> <input type=\"text\" name=\"product-description\" id=\"product-description\" required>");
        out.println("<br>");
        out.print("<label>Quantity: </label><input type=\"text\" name=\"product-quantity\" id=\"product-quantity\" required>");
        out.println("<br>");
        out.print("<label>Price: </label><input type=\"text\" name=\"product-price\" placeholder=\"$100\" id=\"product-price\" required>");
        out.println("<br>");
        //out.print("<button id=\"btn_add_product\" class=\"btn btn-info\">Add</button>");
    }
    private String getHTMLString() {
        return "";
    }
}
