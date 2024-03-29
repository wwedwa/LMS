package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * author class
 * @author The Lobsters
 */
public class Author extends User {
  private ArrayList<Course> createdCourses;

  /**
  * Author constructor with type
  *@param firstName
  *@param lastName
  *@param email
  *@param username
  *@param password
  */
  public Author(String username, String firstName, String lastName, String email, String password, Date birthday) {
    super(username, firstName, lastName, email, password, birthday);
    createdCourses = new ArrayList<Course>();
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
  public Author(String username, String firstName, String lastName, String email, String password, UUID id, Date birthday) {
    super(username, firstName, lastName, email, password, id, birthday);
    createdCourses = new ArrayList<Course>();
  }

  @Override
  public String getType() {
    return "author";
  }

  @Override
  public void addCreatedCourse(Course course) {
    for (Course c : createdCourses) {
      if (c.getCourseID().equals(course.getCourseID())) {
        return;
      }
    }
    createdCourses.add(course);
  }

  /**
  * returns all created courses
  */
  @Override
  public ArrayList<Course> getCreatedCourses() {
    return this.createdCourses;
  }
}
