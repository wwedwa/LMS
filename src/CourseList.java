package src;

import java.util.ArrayList;

/**
 * course list class
 * @author The Lobsters
 */
public class CourseList {
  private ArrayList<Course> courses;
  private static CourseList courseList;
  /**
    * CourseList constructor
    */
  private CourseList() {
    courses = DataLoader.loadCourses();
  }
  
  /**
    * @return a courseList
    */
  public static CourseList getInstance() {
    if (courseList == null) {
      courseList = new CourseList();
    }
    return courseList;
  }

  /**
   * adds a course
   * @param title
   * @param author
   * @param language
   * @param description
   * @param modules
   * @param difficulty
   * @return boolean if course was added
   */
  public boolean addCourse(String title, User author, Language language, String description, ArrayList<Module> modules, Difficulty difficulty) {
    if (contains(title)) {
      return false;
    }
    courses.add(new Course(title, author, language, description, modules, difficulty));
    return true;
  }

  /**
    * @return all courses
    */
  public ArrayList<Course> getAllCourses() {
    return courses;
  }

  /**
    * @return arraylist of courses with keyword
    @param word
    */
  public ArrayList<Course> getCoursesByKeyWord(String word) {
    ArrayList<Course> foundCourses = new ArrayList<Course>();
    for (Course c : courses) {
      if (c.getTitle().toLowerCase().contains(word.toLowerCase())) {
        foundCourses.add(c);
      }
    }
    return foundCourses;
  }

  /**
    * @return true if the course contians that title else false
    */
  public boolean contains(String title) {
    for (Course c : courses) {
      if (c.getTitle().equals(title)) {
        return true;
      }
    }
    return false;
  }

  /**
    * writes courses to datawriter to save them
    */
  public void saveCourses() {
    DataWriter.saveCourses(courses);
  }
}
