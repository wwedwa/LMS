package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Student extends User {

  /**
  * Author constructor with type
  *@param firstName
  *@param lastName
  *@param email
  *@param username
  *@param password
  */
  public Student(String username, String firstName, String lastName, String email, String password, Date birthday) {
    super(username, firstName, lastName, email, password, birthday);
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
  public Student(String username, String firstName, String lastName, String email, String password, UUID id, Date birthday) {
    super(username, firstName, lastName, email, password, id, birthday);
  }

  @Override
  public String getType() {
    return "student";
  }
  
  @Override
  public void addCreatedCourse(Course course) {
    return;
  }

  /**
  * returns all created courses
  */
  @Override
  public ArrayList<Course> getCreatedCourses() {
    return new ArrayList<Course>();
  }
}
