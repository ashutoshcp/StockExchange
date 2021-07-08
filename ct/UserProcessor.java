package ct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProcessor {

    private static Map<String, User> userMap;

    public String addUser(String name) {
        if (userMap == null || userMap.isEmpty()) {
            userMap = new HashMap<>();
        }
        User user = userMap.get(name);
        if (user == null) {
            user = new User(name);
            //System.out.println("Registering user " + name + " in the system");
            userMap.put(name, user);
            return name + " registered successfully";
        } else {
            String info = "user " + name + " already found in the system";
            //System.out.println(info);
            return info;
        }
    }

    public User findUser(String name) throws UserNotFoundException {
        User user = getUser(name);
        if (user == null) {
            throw new UserNotFoundException("name : " + name + "not found");
        }
        return user;
    }

    public static User getUser(String name) {
        if (userMap == null || userMap.isEmpty()) {
            return null;
        }
        User user = userMap.get(name);
        return user;
    }

    public void shortListProperty( Integer propertyId, String userName) {
        User user = getUser(userName);
        if (user == null) {
            System.out.println("User not registered");
            return;
        }
        if (PropertyProcessor.isValidProperty(propertyId)) {
            user.addProperty(propertyId);
        } else {
            System.out.println("Property: " + propertyId + " is not valid");
        }

    }

    public void viewShortlistedProperties(String userName) {
        User user = getUser(userName);
        if (user == null || user.getShortlistedProperties().isEmpty()) {
            System.out.println("Nothing shortlisted for user: " + userName);
            return;
        }
        for (int p: user.getShortlistedProperties()) {
            // get and list
        }
    }
}
