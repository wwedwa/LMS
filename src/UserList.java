package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * UserList class
 * @author The Lobsters
 */
public class UserList {
  private ArrayList<User> users;
  private static UserList userList;

  /**
   * UserList constructor
   */
  private UserList() {
    users = DataLoader.loadUsers();
  }

  /** 
   * getInstance
   * @return UserList
   */
  public static UserList getInstance() {
    if (userList == null) {
      userList = new UserList();
    }
    return userList;
  }

  /** 
   * adds user
   * @param username
   * @param firstName
   * @param lastName
   * @param email
   * @param password
   * @param type
   * @param birthday
   * @return boolean
   */
  public boolean addUser(String username, String firstName, String lastName, String email, String password, String type, Date birthday) {
    if (contains(username)) {
      return false;
    }
    if (type.equals("author")) {
    users.add(new Author(username, firstName, lastName, email, password, birthday));
    } else {
      users.add(new Student(username, firstName, lastName, email, password, birthday));
    }
    return true;
  }

  /** 
   * user accessor
   * @param username
   * @return User
   */
  public User getUser(String username) {
    for (User u : users) {
      if (u.getUsername().equals(username)) {
        return u;
      }
    }
    return null;
  }

  /** 
   * users accessor
   * @return ArrayList<User>
   */
  public ArrayList<User> getUsers() {
    return users;
  }

  /** 
   * user accessor by UUID
   * @param id
   * @return User
   */
  public User getUserByUUID(UUID id) {
    for (User u : users) {
      if (u.getId().equals(id)) {
        return u;
      }
    }
    return null;
  }

  /** 
   * checks if username is present in the list
   * @param username
   * @return boolean
   */
  public boolean contains(String username) {
    for (User u : users) {
      if (u.getUsername().equals(username)) {
        return true;
      }
    }
    return false;
  }

  /**
   * saves users
   */
  public void saveUsers() {
    DataWriter.saveUsers(users);
  }
}
