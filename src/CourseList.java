package src;

import java.util.ArrayList;

public class CourseList {
  private ArrayList<Course> courses;
  private static CourseList courseList;

  private CourseList() {
    courses = DataLoader.loadCourses();
  }

  public static CourseList getInstance() {
    if (courseList == null) {
      courseList = new CourseList();
    }
    return courseList;
  }

  public void addCourse(Course course) {
    if (course != null) {
      courses.add(course);
    }
  }

  public ArrayList<Course> getCoursesByKeyWord(String word) {
    ArrayList<Course> foundCourses = new ArrayList<Course>();
    for (Course c : courses) {
      if (c.getTitle().equals(word)) {
        foundCourses.add(c);
      }
    }
    return foundCourses;
  }
  
  public void saveCourses() {
    DataWriter.saveCourses(courses);
  }
}
