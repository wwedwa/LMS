package src;

import java.util.ArrayList;
import java.util.UUID;

/**
 * author class
 * @author The Lobsters
 */
public class Author extends User {
  private ArrayList<Course> createdCourses;
  private double rating;

  /**
  * Author constructor with type
  *@param firstName
  *@param lastName
  *@param email
  *@param username
  *@param password
  *@param type
  */
  public Author(String username, String firstName, String lastName, String email, String password) {
    super(username, firstName, lastName, email, password);
    createdCourses = new ArrayList<Course>();
    setType("author");
  }

  /**
  * Author constructor with id
  *@param firstName
  *@param lastName
  *@param email
  *@param username
  *@param password
  *@param id
  */
  public Author(String username, String firstName, String lastName, String email, String password, UUID id) {
    super(username, firstName, lastName, email, password, id);
  }

  /**
  * update rating
  *@param rating
  */
  public void updateRating(int rating) {
    this.rating = rating;
  }

  /**
  * returns all created courses
  */
  @Override
  public ArrayList<Course> getCreatedCourses() {
    return this.createdCourses;
  }
}
