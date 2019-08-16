package controller;

//import model.MapUsers;

import data.access.MapUsers;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/login")

public class Login extends HttpServlet {
    public static MapUsers usersDAO= MapUsers.getInstance();
    @Override
    public void init() throws ServletException {
        super.init();
        usersDAO = MapUsers.getInstance();
    }
    public void addUser(User user) {
        this.usersDAO.getAllUsers().add(user);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();

        Cookie[] cookies=request.getCookies();
        String userName = "";
        String rememberme = "";
        String message = "";
        HttpSession session= request.getSession();
        if(session.getAttribute("msg") != null)
            message = (String)session.getAttribute("msg");
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("user-name")) {
                    userName = cookie.getValue();
                }
                if(cookie.getName().equals("checkbox")) {
                    if (cookie.getValue() == null)
                        rememberme = "";
                    else
                        rememberme = "checked";
                }
            }
        }
        out.print("<h1>Welcome</h1>");
        if(request.getAttribute("msg") != null)
            out.print(request.getAttribute("msg"));
        else
            out.print("");

        out.print("<p><label> user name: </label><input class=\"login-input\" type=\"text\" name=\"user-name\" id=\"user-name\" value=\"" + userName+"\" size=\"20\" maxlength=\"25\"></p>\n");
        out.print("<p><label> password: </label><input class=\"login-input\" type=\"password\" name=\"password\" id=\"password\" size=\"20\" maxlength=\"25\"></p>");
        out.print("<label> remember me: </label><input type=\"checkbox\" id=\"checkbox\" name=\"checkbox\"" + rememberme +  " >");
        out.print("<p style=\"color: red\">" +  message + "</p>\n");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("user-name");
        String password = req.getParameter("password");
        String rememberme = req.getParameter("checkbox");
        System.out.println("checkbox: "+rememberme);
        HttpSession session = req.getSession();
        System.out.println("un : " + userName);
        System.out.println("un : " + password);
        System.out.println("un : "  + userName);
        boolean flag = false;
        List<User> users = usersDAO.getAllUsers();
        for (User u: users){
            if(u.getUserName().equals(userName) &&
                u.getPassword().equals(password)){
                flag = true;
                break;
            }
        }
        if(flag) {

            session.setAttribute("userName", userName);
            if (rememberme != null && rememberme.equals("on")) {
                Cookie userNameCookie = new Cookie("user-name", userName);
                Cookie rememberMecookie = new Cookie("checkbox", rememberme);
                userNameCookie.setMaxAge(30 * 24 * 60 * 60); //equivalent one month in seconds
                rememberMecookie.setMaxAge(30 * 24 * 60 * 60);
                System.out.println("cookie: " + userNameCookie.getValue());
                resp.addCookie(userNameCookie);
                resp.addCookie(rememberMecookie);
            } else {
                Cookie cUserName = new Cookie("user-name", null);
                Cookie rememberMecookie = new Cookie("checkbox", rememberme);
                cUserName.setMaxAge(0);
                rememberMecookie.setMaxAge(0);
                resp.addCookie(cUserName);
                resp.addCookie(rememberMecookie);
            }
            //resp.sendRedirect("products");
            resp.getWriter().print("TRUE");

        }
        else {
            session.setAttribute("msg", " Invalid user name and password!");
            doGet(req, resp);
        }

    }
}
