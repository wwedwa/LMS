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
    this.user = new User(username, firstName, lastName, email, password);
  }
  public void addCourse(String title, Language language, String description, ArrayList<Module> modules, Difficulty difficulty) {
    courseList.addCourse(new Course(title, this.user, language, description, modules, difficulty));
  }
  public ArrayList<Course> findCourses(String keyword) {
    return courseList.getCoursesByKeyWord(keyword);
  }
  public ArrayList<Course> getAllCourses() {
    return null;
  }
  public ArrayList<Course> getRegisteredCourses() {
    return this.user.getRegisteredCourses();
  }
  public ArrayList<Course> getCreatedCourses() {
    return this.getCreatedCourses();
  }
  public ArrayList<Course> getCompletedCourses() {
    return this.user.getCompletedCourses();
  }
  public boolean checkUsername(String username) {
    return username.equals(this.user.getUsername());
  }
  public boolean checkCourseTitle(String title) {
    return this.courseList.contains(title);
  }
  public Assessment getModuleQuiz() {
    return this.currModule.getQuiz();
  }
  public double evaluateAssessment(Assessment assessment, ArrayList<Integer> answers) {
    return currModule.getAssessment().evaluateAssessment(answers);
  }
  public void updateGrade(int moduleNum, double grade) {
    user.updateCourseGrade(moduleNum, grade);
  }
  public void addCourseComment(String decription) {
    Comment comment = new Comment(user, decription);
    currCourse.addComment(comment);
  }
  public void addModuleComment(String decription) {
    Comment comment = new Comment(user, decription);
    currModule.addComment(comment);
  }
  public ArrayList<Comment> getCourseComments() {
    return currCourse.getComments();
  }
  public ArrayList<Comment> getModuleComments() {
    return currModule.getComments();
  }
  public void addReply(Comment comment, String decription) {
    Comment reply = new Comment(user, decription);
    comment.addReply(reply);
  }
  public void addReview(String description, int rating) {
    Review review = new Review(user, rating, description);
    currCourse.addReview(review);
  }
  public ArrayList<Review> getReviews() {
    return currCourse.getReviews();
  }
  public User getUser() {
    return user;
  }
}