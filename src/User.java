package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * User class
 * @author The Lobsters
 */
public abstract class User {
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Date birthday;
  private UUID userid;
  private ArrayList<Course> registeredCourses;
  private ArrayList<CourseInfo> gradeBook;

  /**
   * User constructor
   * @param username
   * @param firstName
   * @param lastName
   * @param email
   * @param password
   * @param birthday
   */
  public User(String username, String firstName, String lastName, String email, String password, Date birthday) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.userid = UUID.randomUUID();
    this.birthday = birthday;
    registeredCourses = new ArrayList<Course>();
    gradeBook = new ArrayList<CourseInfo>();
  }

  /**
   * User constructor with UUID
   * @param username
   * @param firstName
   * @param lastName
   * @param email
   * @param password
   * @param userid
   * @param birthday
   */
  public User(String username, String firstName, String lastName, String email, String password, UUID userid, Date birthday) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.userid = userid;
    this.birthday = birthday;
    registeredCourses = new ArrayList<Course>();
    gradeBook = new ArrayList<CourseInfo>();
  }

  /** 
   * username accessor
   * @return String
   */
  public String getUsername() {
    return this.username;
  }

  /** 
   * first name accessor
   * @return String
   */
  public String getFirstName() {
    return this.firstName;
  }

  /** 
   * last name accessor
   * @return String
   */
  public String getLastName() {
    return this.lastName;
  }

  /** 
   * email accessor
   * @return String
   */
  public String getEmail() {
    return this.email;
  }

  /** 
   * password accessor
   * @return String
   */
  public String getPassword() {
    return this.password;
  }

  /** 
   * UUID accessor
   * @return UUID
   */
  public UUID getId() {
    return this.userid;
  }

  public abstract String getType();

  /** 
   * birthday accessor
   * @return Date
   */
  public Date getBirthday() {
    return birthday;
  }

  /** 
   * registered course accessor
   * @return ArrayList<Course>
   */
  public ArrayList<Course> getRegisteredCourses() {
    return this.registeredCourses;
  }

  /** 
   * grades accessor
   * @param course
   * @return ArrayList<Double>
   */
  public ArrayList<Double> getGrades(Course course) {
    for (CourseInfo courseInfo : gradeBook) {
      if (course.getCourseID().equals(courseInfo.getCourse().getCourseID())) {
        return courseInfo.getGrades();
      }
    }
    return new ArrayList<Double>();
  }

  /** 
   * course grade accessor
   * @param course
   * @return double
   */
  public double getCourseGrade(Course course) {
    for (CourseInfo courseInfo : gradeBook) {
      if (course.getCourseID().equals(courseInfo.getCourse().getCourseID())) {
        return courseInfo.getGrade();
      }
    }
    return -1;
  }

  /** 
   * checks if user is enrolled in a course
   * @param course
   * @return boolean
   */
  public boolean isEnrolled(Course course) {
    for (Course c : registeredCourses) {
      if (c.getCourseID().equals(course.getCourseID())) {
        return true;
      }
    }
    return false;
  }

  /** 
   * registers user for a course
   * @param course
   * @param grades
   * @return boolean
   */
  public boolean registerCourse(Course course, ArrayList<Double> grades) {
    if (isEnrolled(course)) {
      return false;
    }
    this.registeredCourses.add(course);
    this.gradeBook.add(new CourseInfo(course, grades));
    return true;
  }

  /** 
   * registers user for a course
   * @param course
   * @return boolean
   */
  public boolean registerCourse(Course course) {
    if (isEnrolled(course)) {
      return false;
    }
    this.registeredCourses.add(course);
    this.gradeBook.add(new CourseInfo(course));
    return true;
  }

  /** 
   * unregisters user from a course
   * @param course
   */
  public void unregisterCourse(Course course) {
    this.registeredCourses.remove(course);
  }

  public abstract void addCreatedCourse(Course course);

  /** 
   * updates course grade
   * @param moduleNum
   * @param grade
   * @param course
   */
  public void updateCourseGrade(int moduleNum, double grade, Course course) {
    for (CourseInfo courseInfo : gradeBook) {
      if (course.getCourseID().equals(courseInfo.getCourse().getCourseID())) {
        courseInfo.updateGrade(moduleNum, grade);
        return;
      }
    }
  }

  public abstract ArrayList<Course> getCreatedCourses();

  /** 
   * checks if course is completed
   * @param course
   * @return boolean
   */
  public boolean isCourseCompleted(Course course) {
    if (!isEnrolled(course)) {
      return false;
    }
    ArrayList<Double> grades = getGrades(course);
    for (double grade : grades) {
      if (grade < 0) {
        return false;
      }
    }
    return true;
  }
}
