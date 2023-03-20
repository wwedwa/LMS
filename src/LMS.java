package src;

import java.util.ArrayList;


public class LMS {
  private User user;
  private Course currCourse;
  private Module currModule;
  private Lesson currLesson;
  private CourseList courseList;
  private UserList userList;

  public LMS() {
    userList = UserList.getInstance();
    courseList = CourseList.getInstance();
  }
  public boolean login(String username, String password) {
    if (userList.getUser(username).getPassword().equals(password)) {
      user = userList.getUser(username);
      return true;
    }
    else {
      return false;
    }
  }
  public void logout() {
    return;
  }
  public void addUser(String firstName, String lastName, String email, String username, String password) {
    return;
  }
  public void addCourse(String title, Language topic, String description, ArrayList<Module> modules, Difficulty difficulty) {
    return;
  }
  public ArrayList<Course> findCourses(String keyword) {
    return null;
  }
  public ArrayList<Course> getAllCourses() {
    return null;
  }
  public ArrayList<Course> getRegisteredCourses() {
    return null;
  }
  public ArrayList<Course> getCreatedCourses() {
    return null;
  }
  public ArrayList<Course> getCompletedCourses() {
    return null;
  }
  public boolean checkUsername(String username) {
    return true;
  }
  public boolean checkCourseTitle(String title) {
    return true;
  }
  public Assessment getModuleQuiz() {
    return null;
  }
  public void evaluateAssessment(Assessment assessment, ArrayList<Integer> answers) {
    return;
  }
  public void updateGrade(int moduleNum, double grade) {
    return;
  }
  public void addCourseComment(String decription) {
    return;
  }
  public void addModuleComment(String decription) {
    return;
  }
  public ArrayList<Comment> getCourseComments() {
    return null;
  }
  public ArrayList<Comment> getModuleComments() {
    return null;
  }
  public void addReply(Comment comment, String decription) {
    return;
  }
  public void addReview(String description, int rating) {
    return;
  }
  public ArrayList<Review> getReviews() {
    return null;
  }
  public User getUser() {
    return user;
  }
}