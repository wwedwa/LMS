package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * LMS class
 * @author The Lobsters
 */
public class LMS {
  private User user;
  private Course currCourse;
  private Module currModule;
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
  public boolean addUser(String username, String firstName, String lastName, String email, String password, String type, Date birthday) {
    boolean wasAdded = userList.addUser(username, firstName, lastName, email, password, type, birthday);
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
    boolean toReturn = courseList.addCourse(title, user, language, description, modules, difficulty);
    return toReturn;
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
   * moves current course to a new course
   * @param choice
   */
  public void moveToCourse(int choice) {
    currCourse = courseList.getAllCourses().get(choice - 1);
  }
  /**
   * moves current course to a new course
   * @param choice
   */
  public void moveToRegisteredCourse(int choice) {
    currCourse = user.getRegisteredCourses().get(choice - 1);
  }
  /**
   * moves current module to a new module
   * @param choice
   */
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
  /**
   * @return arraylist of registered courses
   */
  public ArrayList<Course> getRegisteredCourses() {
    return user.getRegisteredCourses();
  }
  /**
   * @return the current module
   */
  public ArrayList<Module> getModules() {
    return currCourse.getModules();
  }
  /**
   * @return the current course description
   */
  public String getCourseDescription() {
    return currCourse.getDescription();
  }
  /**
   * @return the current course grade of the user
   */
  public double getCourseGrade() {
    return user.getCourseGrade(currCourse);
  }
  /**
   * registers user to new course
   */
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
  /**
   * gets the users current grades
   */
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
  /**
   * gets the current modules lessons
   */
  public ArrayList<Lesson> getLessons() {
    return currModule.getLessons();
  }
  /**
   * gets the current modules quiz
   */
  public Assessment getQuiz() {
    return currModule.getAssessment();
  }
  /**
   * this method gets the User's registered courses
   * @return
   */
  public ArrayList<Course> getCreatedCourses() {
    return user.getCreatedCourses();
  }

  /**
   * @return the course list titles
   */
  public boolean checkCourseTitle(String title) {
    return this.courseList.contains(title);
  }

  /**
   * @return the current modules quiz
   */
  public Assessment getModuleQuiz() {
    return this.currModule.getAssessment();
  }
  /**
   * creates the comments 
   */
  public ArrayList<String> generateComments(ArrayList<Comment> comments) {
    ArrayList<String> options = new ArrayList<String>();
    for (Comment comment : comments) {
      options.add(comment.toString());
      for (Comment reply : comment.getReplies()) {
        generateComments(reply, 1, options);
      }
    }
    return options;
  }
  /**
   * generates the comments 
   */
  private void generateComments(Comment comment, int depth, ArrayList<String> options) {
    String tabs = "";
    for (int i = 0; i < depth; i++) {
      tabs += "\t";
    }
    options.add(tabs + comment);
    for (Comment reply : comment.getReplies()) {
      generateComments(reply, depth + 1, options);
    }
    return;
  }
  /**
   * generates a list of comments and @return them
   */
  private ArrayList<Comment> generateCommentList(ArrayList<Comment> comments) {
    ArrayList<Comment> commentList = new ArrayList<Comment>();
    for (Comment comment : comments) {
      commentList.add(comment);
      for (Comment reply : comment.getReplies()) {
        generateCommentList(reply, commentList);
      }
    }
    return commentList;
  }
  /**
   * generates a list of comments
   */
  private void generateCommentList(Comment comment, ArrayList<Comment> commentList) {
    commentList.add(comment);
    for (Comment reply : comment.getReplies()) {
      generateCommentList(reply, commentList);
    }
    return;
  }
  /**
   * evaluates the users assessment 
   * @param assessment
   * @param answers
   * @return
   */
  public double evaluateAssessment(ArrayList<Integer> answers) {
    double grade = currModule.getAssessment().evaluateAssessment(answers) * 100;
    user.updateCourseGrade(currCourse.getModules().indexOf(currModule), grade, currCourse);
    return grade;
  }
  /**
   * updates the users grade
   */
  public void updateGrade(int moduleNum, double grade) {
    user.updateCourseGrade(moduleNum, grade, currCourse);
  }

  /**
   * adds a comment to the current module 
   */
  public void addModuleComment(String decription) {
    currModule.addComment(new Comment(user, decription));
  }

  /**
   * creates a certificate for a specific course for the user
   * @throws IOException
   */
  public boolean createCertificate() {
    return currCourse.createCertificate(user);
  }

  /**
   * @return the current course comments
   */
  public ArrayList<Comment> getCourseComments() {
    return currCourse.getComments();
  }

  /**
   * @return the current modules comments
   */
  public ArrayList<Comment> getModuleComments() {
    return currModule.getComments();
  }

  /**
   * adds a reply to a comment
   */
  public void addReply(int commentNum, String decription, ArrayList<Comment> comments) {
    Comment reply = new Comment(user, decription);
    generateCommentList(comments).get(commentNum - 1).addReply(reply);
  }

  public void addCourseComment(String description) {
    currCourse.addComment(new Comment(user, description));
  }

  /**
   * adds a review to the current course
   */
  public void addReview(String description, int rating) {
    Review review = new Review(user, rating, description);
    currCourse.addReview(review);
  }

  /**
   * @return the reviews of a current course
   */
  public ArrayList<Review> getReviews() {
    return currCourse.getReviews();
  }
  /**
   * @return true if the current course has reviews else false
   */
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
   * @return the current user
   */
  public User getUser() {
    return user;
  }
  /**
   * @return true is the users current course is completed else false
   */
  public boolean isCourseCompleted() {
    return user.isCourseCompleted(currCourse);
  }
  /**
   * saves a module to a file
   * @return true or false if module is saved
   */
  public boolean saveModule() {
    return currModule.saveModule();
  }
}
