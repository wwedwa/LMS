package LMS;

import java.util.UUID;

public class User {
  private String firstName;
  private String lastName;
  private String email;
  private String username;
  private String password;
  private String type;
  private UUID id;

  public User(String firstName, String lastName, String email, String username, String password, String type) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.password = password;
    this.type = type;
    this.id = UUID.randomUUID();
  }

  public User(String firstName, String lastName, String email, String username, String password, String type, UUID id) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.password = password;
    this.type = type;
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getType() {
    return type;
  }

  public UUID getId() {
    return id;
  }

  public void addCourse(Course course) {
      
  }
  public void removeCourse(Course course) {
      
  }
  public void addCompletedCourse(Course course) {
      
  }
  public void addFavAuthor(Author author) {
      
  }
  public void removeFavAuthor(Author author) {
      
  }
  public void updateCourseGrade(int moduleNum, double grade) {
      
  }
}
