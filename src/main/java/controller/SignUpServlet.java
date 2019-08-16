package controller;//package controller;



import com.google.gson.Gson;
import model.MapUsers;
import model.User;
//import util.UserUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;


@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Gson gson = new Gson();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String fullName, email, userName, password;
        fullName=email=userName=password=null;
        try {
            fullName = request.getParameter("fullName");
            email = request.getParameter("email");
            userName= request.getParameter("userName");
            password = request.getParameter("password");
        }catch(Exception e) {
            response.getWriter().println("FALSE");
            return;
        }
        if(fullName == null || email == null || userName == null || password == null) {
            response.getWriter().println("FALSE");
            return;
        }
        User user= new User(fullName, email, userName, password);
        Login.usersDAO.addUser(user);
        //request.getRequestDispatcher("login").forward(request, response);
        response.getWriter().println("TRUE");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();

        out.println("<h1> Sign Up </h1>");
        out.println("<p><label for=\"fullName\">Full Name: </label> <input id=\"fullName\" type=\"text\" name=\"fullName\" /></p>");
        out.println("<p><label for=\"userName\">User Name: </label><input id=\"userName\" type=\"text\" name=\"userName\" /></p>");
        out.println("<p><label for=\"password\">Password: </label><input id=\"password\" type=\"password\" name=\"password\" /></p>");
        out.println("<p><label for=\"email\">Email: </label><input type=\"email\" id=\"email\" name=\"email\" /></p>");
    }
}
