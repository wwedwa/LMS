package src;

import java.util.ArrayList;
import java.util.Date;
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

  public boolean addUser(String username, String firstName, String lastName, String email, String password, String type, Date birthday) {
    if (contains(username)) {
      return false;
    }
    if (type.equals("author")) {
    users.add(new Author(username, firstName, lastName, email, password, birthday));
    } else {
      users.add(new User(username, firstName, lastName, email, password, birthday));
    }
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
