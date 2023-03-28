package src;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * LMS class
 * @author The Lobsters
 */
public class LMS {
  private User user;
  private Author author;
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

  public Course getCourse() {
    return currCourse;
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
    courseList.saveCourses();
    userList.saveUsers();
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
  public boolean addUser(String username, String firstName, String lastName, String email, String password) {
    boolean wasAdded = userList.addUser(username, firstName, lastName, email, password);
    if (wasAdded) {
      user = userList.getUser(username);
    }
    return wasAdded;
  }

  /**
   * this method adds a new Course to the courseList
   * @param title
   * @param language
   * @param description
   * @param modules
   * @param difficulty
   */
  public boolean addCourse(String title, Language language, String description, ArrayList<Module> modules, Difficulty difficulty) {
    return false;
  }

  /**
   * this method searches the courseList for a course by keyword
   * @param keyword
   * @return list of courses containing the keyword
   */
  public ArrayList<Course> findCourses(String keyword) {
    return courseList.getCoursesByKeyWord(keyword);
  }

  public void moveToCourse(int choice) {
    currCourse = courseList.getAllCourses().get(choice - 1);
  }

  public void moveToRegisteredCourse(int choice) {
    currCourse = user.getRegisteredCourses().get(choice - 1);
  }

  public void moveToModule(int choice) {
    currModule = currCourse.getModules().get(choice - 1);
  }

  /**
   * this method gets all courses in the list
   * @return list of all courses
   */
  public ArrayList<Course> getAllCourses() {
    return this.courseList.getAllCourses();
  }

  public ArrayList<Course> getRegisteredCourses() {
    return user.getRegisteredCourses();
  }

  public ArrayList<Module> getModules() {
    return currCourse.getModules();
  }

  public String getCourseDescription() {
    return currCourse.getDescription();
  }

  public double getCourseGrade() {
    return user.getCourseGrade(currCourse);
  }

  public void register() {
    user.registerCourse(currCourse);
  }

  /**
   * this method gets the User's registered courses as strings
   * @return list of registered course strings
   */
  public ArrayList<String> getRegisteredCourseStrings() {
    ArrayList<Course> courses = user.getRegisteredCourses();
    ArrayList<String> courseStrings = new ArrayList<String>();
    for (int i = 0; i < courses.size(); i++) {
      courseStrings.add(courses.get(i).toString());
    }
    return courseStrings;
  }

  public ArrayList<String> getModuleGradeStrings() {
    ArrayList<Module> modules = currCourse.getModules();
    ArrayList<Double> grades = user.getGrades(currCourse);
    ArrayList<String> moduleStrings = new ArrayList<String>();
    for (int i = 0; i < modules.size(); i++) {
      String grade = (grades.get(i) == -1) ? "Not taken" : grades.get(i).toString();
      moduleStrings.add(modules.get(i) + " - Quiz Grade: " + grade);
    }
    return moduleStrings;
  }

  public ArrayList<Lesson> getLessons() {
    return currModule.getLessons();
  }

  public Assessment getQuiz() {
    return currModule.getAssessment();
  }
  /**
   * this method gets the User's registered courses
   * @return
   */
  public ArrayList<Course> getCreatedCourses() {
    ArrayList<Course> courses = new ArrayList<>();
    for (Course c : getAllCourses())
    if (c.getAuthor().equals(getUser())) {
      courses.add(c);
    }
    return courses;
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
  public double evaluateAssessment(ArrayList<Integer> answers) {
    double grade = currModule.getAssessment().evaluateAssessment(answers);
    user.updateCourseGrade(currCourse.getModules().indexOf(currModule), grade, currCourse);
    return grade;
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

  public void createCertificate() throws IOException {
    try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(currCourse.getTitle()+".txt"), "utf-8"))) {
      writer.write("Congratulations "+user.getFirstName()+" you completed "+currCourse.getTitle()+"!");
    }
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

  public boolean hasReviewed() {
    ArrayList<Review> reviews = currCourse.getReviews();
    for (Review review : reviews) {
      if (review.getWriter().getId().equals(user.getId())) {
        return true;
      }
    }
    return false;
  }

  /**
   * 
   * @return
   */
  public User getUser() {
    return user;
  }
}