package LMS;
public class LMS {
  private User user;
  private Course currCourse;
  private Module currModule;
  private Lesson currLesson;
  private CourseList courseList;
  private UserList userList;

  public LMS() {

  }
  public boolean login(String username, String password) {
    return true;
  }
  public void logout() {
    return;
  }
  public void addUser(String firstName, String lastName, String email, String username, String password) {
    return;
  }
  public void addCourse(String title, Langauge Topic, String description, ArrayList<Module> modules, Difficulty difficulty) {
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
  public void evaluateAssessment(Assessment assessment, ArrayList<int> answers) {
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
}