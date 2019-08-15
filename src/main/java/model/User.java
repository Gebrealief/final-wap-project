package model;

import javax.servlet.http.HttpServlet;

public class User extends HttpServlet {
    private String userName;
    private  String password;
    private String fullName="";
    private String email="";
    public User() {}

    public User(String name, String password){
        this.userName = name;
        this.password = password;
    }
    public User(String fullName, String email, String name, String password) {
        this(name, password);
        this.fullName=fullName;
        this.email=email;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
