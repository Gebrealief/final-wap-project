package controller;

import data.access.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/removeitem")
public class RemoveCartItem extends HttpServlet {
    private Cart items;
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String string = req.getParameter("removeitem");
        string = string.substring(1,string.length()-1);
        System.out.println("id = "+string);
        items = Cart.getInstance();
        items.deleteItem(Integer.parseInt(string));
        session.setAttribute("cart", items.getAllItems());
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.print(string);
    }
}
