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

  public boolean addUser(String username, String firstName, String lastName, String email, String password) {
    if (contains(username)) {
      return false;
    }
    users.add(new User(username, firstName, lastName, email, password));
    return true;
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

  public boolean contains(String username) {
    for (User u : users) {
      if (u.getUsername().equals(username)) {
        return true;
      }
    }
    return false;
  }

  public void saveUsers() {
    DataWriter.saveUsers(users);
  }
}
