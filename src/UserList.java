package src;

import java.util.ArrayList;
import java.util.UUID;

public class UserList {
  private ArrayList<User> users;
  private static UserList userList;

  private UserList() {
    users = DataLoader.loadUsers();
  }

  public static UserList getInstance() {
    if (userList == null) {
      userList = new UserList();
    }
    return userList;
  }

  public void addUser(User user) {
    if (user != null) {
      users.add(user);
    }
  }

  public User getUser(String username) {
    for (User u : users) {
      if (u.getUsername().equals(username)) {
        return u;
      }
    }
    return null;
  }

  public ArrayList<User> getUsers() {
    return users;
  }
  
  public User getUserByUUID(UUID id) {
    for (User u : users) {
      if (u.getId().equals(id)) {
        return u;
      }
    }
    return null;
  }

  public void saveUsers() {
    DataWriter.saveUsers(users);
  }
}
