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

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user= new User(fullName, email, userName, password);
        Login.usersDAO.addUser(user);
        request.getRequestDispatcher("login").forward(request, response);
/*
  System.out.println(fullname+" "+email+" "+username+" "+password);
        if (UserUtil.setUser(username, new User(fullname, email, username, password))) {

            request.getSession().setAttribute("Welcome", "Welcome  " + fullname);
            response.sendRedirect("/assignment13/login.jsp");
        } else {

            request.getSession().setAttribute("Warning", "Username or email exist please try again");
            response.sendRedirect("/assignment13/index.jsp");
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*

        if (request.getParameter("username") != null) {
            PrintWriter out = response.getWriter();
            String username = request.getParameter("username");
            Boolean isUser = UserUtil.isExistUserName(username);

            try {
                out.println(isUser);
            } finally {
                out.close();
            }
        } else if (request.getParameter("email") != null) {
            PrintWriter out = response.getWriter();
            String s=request.getParameter("username");
            String email = request.getParameter("email");
            Boolean isEmailUser = UserUtil.isExistEmail(s,email);
            try {
                out.println(isEmailUser);
            } finally {
                out.close();
            }
        }
*/


    }
}
