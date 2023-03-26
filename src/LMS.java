package src;

import java.util.ArrayList;

/**
 * LMS class
 * @author The Lobsters
 */
public class LMS {
  private User user;
  private Course currCourse;
  private Module currModule;
  private Lesson currLesson;
  private CourseList courseList;
  private UserList userList;

  /**
   * LMS constructor that calls the singletons
   */
  public LMS() {
    userList = UserList.getInstance();
    courseList = CourseList.getInstance();
  }

  /**
   * this method allows the User to log in
   * @param username
   * @param password
   * @return boolean true or false depending on if the login was successful
   */
  public boolean login(String username, String password) {
    if (!userList.contains(username)) {
      return false;
    }
    if (userList.getUser(username).getPassword().equals(password)) {
      user = userList.getUser(username);
      return true;
    } else {
      return false;
    }
  }

  /**
   * 
   */
  public void logout() {
    // ***DONT UNCOMMENT TWO LINES BELOW, WILL BREAK CODE***
    //courseList.saveCourses();
    //userList.saveUsers();
    System.exit(0);
  }

  /**
   * this method adds a new User to the user variable
   * @param firstName
   * @param lastName
   * @param email
   * @param username
   * @param password
   */
  public void addUser(String firstName, String lastName, String email, String username, String password) {
    this.user = new User(username, firstName, lastName, email, password);
  }

  /**
   * this method adds a new Course to the courseList
   * @param title
   * @param language
   * @param description
   * @param modules
   * @param difficulty
   */
  public void addCourse(String title, Language language, String description, ArrayList<Module> modules, Difficulty difficulty) {
    courseList.addCourse(new Course(title, this.user, language, description, modules, difficulty));
  }

  /**
   * this method searches the courseList for a course by keyword
   * @param keyword
   * @return list of courses containing the keyword
   */
  public ArrayList<Course> findCourses(String keyword) {
    return courseList.getCoursesByKeyWord(keyword);
  }

  /**
   * this method gets all courses in the list
   * @return list of all courses
   */
  public ArrayList<Course> getAllCourses() {
    return this.courseList.getAllCourses();
  }

  /**
   * this method gets the User's registered courses
   * @return list of registered courses
   */
  public ArrayList<Course> getRegisteredCourses() {
    return this.user.getRegisteredCourses();
  }

  /**
   * this method gets the User's registered courses
   * @return
   */
  public ArrayList<Course> getCreatedCourses() {
    return this.getCreatedCourses();
  }

  /**
   * this method returns a list of completed courses
   * @return list of courses
   */
  public ArrayList<Course> getCompletedCourses() {
    return this.user.getCompletedCourses();
  }

  /**
   * 
   * @return
   */
  public boolean checkUsername(String username) {
    return username.equals(this.user.getUsername());
  }

  /**
   * 
   * @return
   */
  public boolean checkCourseTitle(String title) {
    return this.courseList.contains(title);
  }

  /**
   * 
   * @return
   */
  public Assessment getModuleQuiz() {
    return this.currModule.getAssessment();
  }

  /**
   * 
   * @param assessment
   * @param answers
   * @return
   */
  public double evaluateAssessment(Assessment assessment, ArrayList<Integer> answers) {
    return currModule.getAssessment().evaluateAssessment(answers);
  }

  /**
   * 
   * @return
   */
  public void updateGrade(int moduleNum, double grade) {
    user.updateCourseGrade(moduleNum, grade, currCourse);
  }

  /**
   * 
   * @return
   */
  public void addCourseComment(String decription) {
    Comment comment = new Comment(user, decription);
    currCourse.addComment(comment);
  }

  /**
   * 
   * @return
   */
  public void addModuleComment(String decription) {
    Comment comment = new Comment(user, decription);
    currModule.addComment(comment);
  }

  /**
   * 
   * @return
   */
  public ArrayList<Comment> getCourseComments() {
    return currCourse.getComments();
  }

  /**
   * 
   * @return
   */
  public ArrayList<Comment> getModuleComments() {
    return currModule.getComments();
  }

  /**
   * 
   * @return
   */
  public void addReply(Comment comment, String decription) {
    Comment reply = new Comment(user, decription);
    comment.addReply(reply);
  }

  /**
   * 
   * @return
   */
  public void addReview(String description, int rating) {
    Review review = new Review(user, rating, description);
    currCourse.addReview(review);
  }

  /**
   * 
   * @return
   */
  public ArrayList<Review> getReviews() {
    return currCourse.getReviews();
  }

  /**
   * 
   * @return
   */
  public User getUser() {
    return user;
  }
}