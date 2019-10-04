package steps.continuumsecurity;

import java.util.ArrayList;
import java.util.List;

public class Users {
    List<User> users = new ArrayList<User>();

    public Users add(Credentials creds, String... roles) {
        users.add(new User(creds, roles));
        return this;
    }

    public Users add(String username, String password, String... roles) {
        users.add(new User(new UserPassCredentials(username, password), roles));
        return this;
    }

    public Credentials getDefaultCredentials() {
        if (users == null || users.size() == 0) throw new RuntimeException("No users defined!");
        return users.get(0).getCredentials();
    }

    public List<User> getAllUsersExcept(List<String> exclude) {
        List<User> theUsers = new ArrayList<User>();
        for (User user : users) {
            if (!exclude.contains(user.getCredentials().get("username"))) {
                theUsers.add(user);
            }
        }
        return theUsers;
    }

    public List<User> getAll() {
        return users;
    }

    public User findByCredential(String key, String value) {
        for (User user : users) {
            if ((user.getCredentials().containsKey(key)) && (user.getCredentials().get(key).equals(value))) return user;
        }
        return null;
    }

    public void add(User user) {
        users.add(user);
    }
}