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
    * returns a courseList
    */
  public static CourseList getInstance() {
    if (courseList == null) {
      courseList = new CourseList();
    }
    return courseList;
  }

  /**
    * adds course to courseList
    */
  public void addCourse(Course course) {
    if (course != null) {
      courses.add(course);
    }
  }

  /**
    * returns all courses
    */
  public ArrayList<Course> getAllCourses() {
    return courses;
  }

  /**
    * returns arraylist of courses with keyword
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
    * returns true if the course contians that title else false
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
