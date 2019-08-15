package model;

public class MapUsers {
    static private User[] users = {new User("joun","joun"),
            new User("hellen","hellen"),
            new User("smith","smith"),
            new User("sam","sam"),
            new User("jack","jack")};


    static  public User[] getUsers(){
        return users;
    }

}
