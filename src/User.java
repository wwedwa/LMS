package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

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

  public String getUsername() {
    return this.username;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public UUID getId() {
    return this.userid;
  }

  public abstract String getType();

  public Date getBirthday() {
    return birthday;
  }

  public ArrayList<Course> getRegisteredCourses() {
    return this.registeredCourses;
  }

  public ArrayList<Double> getGrades(Course course) {
    for (CourseInfo courseInfo : gradeBook) {
      if (course.getCourseID().equals(courseInfo.getCourse().getCourseID())) {
        return courseInfo.getGrades();
      }
    }
    return new ArrayList<Double>();
  }

  public double getCourseGrade(Course course) {
    for (CourseInfo courseInfo : gradeBook) {
      if (course.getCourseID().equals(courseInfo.getCourse().getCourseID())) {
        return courseInfo.getGrade();
      }
    }
    return -1;
  }

  public boolean isEnrolled(Course course) {
    for (Course c : registeredCourses) {
      if (c.getCourseID().equals(course.getCourseID())) {
        return true;
      }
    }
    return false;
  }

  public boolean registerCourse(Course course, ArrayList<Double> grades) {
    if (isEnrolled(course)) {
      return false;
    }
    this.registeredCourses.add(course);
    this.gradeBook.add(new CourseInfo(course, grades));
    return true;
  }

  public boolean registerCourse(Course course) {
    if (isEnrolled(course)) {
      return false;
    }
    this.registeredCourses.add(course);
    this.gradeBook.add(new CourseInfo(course));
    return true;
  }

  public void unregisterCourse(Course course) {
    this.registeredCourses.remove(course);
  }

  public abstract void addCreatedCourse(Course course);

  public void updateCourseGrade(int moduleNum, double grade, Course course) {
    for (CourseInfo courseInfo : gradeBook) {
      if (course.getCourseID().equals(courseInfo.getCourse().getCourseID())) {
        courseInfo.updateGrade(moduleNum, grade);
        return;
      }
    }
  }

  public abstract ArrayList<Course> getCreatedCourses();

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
