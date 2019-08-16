package data.access;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUsers {

    private static MapUsers ourInstance = new MapUsers();

    public static MapUsers getInstance() {
        return ourInstance;
    }

    private MapUsers() {
    }

    private Map<String, User> userDAO = new HashMap<>();

    {
        userDAO.put("sam", new User("sam", "sam"));
        userDAO.put("bini", new User("bini", "bini"));
        userDAO.put("hewan", new User("hewan", "hewan"));
        userDAO.put("smith", new User("smith", "smith"));
        userDAO.put("admin", new User("admin", "admin"));
    }

    public List<User> getAllUsers(){return new ArrayList<>(userDAO.values());}
    public void addUser(User user) {
        this.userDAO.put(user.getUserName(), user);
    }
    //Milki code
//public Map<String , User> getMap()
//{
//    return userDAO;
//}
//
//    public void addProduct(String s, User user){
//        userDAO.put(s,user);
//
//    }

}
