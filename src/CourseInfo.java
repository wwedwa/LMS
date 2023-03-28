package src;

import java.util.ArrayList;

/**
 * course info class
 * @author The Lobsters
 */
public class CourseInfo {
  private Course course;
  private double grade;
  private ArrayList<Double> moduleGrades;

  /**
  * CourseInfo constructor
  *@param course
  */
  public CourseInfo(Course course) {
    this.course = course;
    this.grade = 0;
    this.moduleGrades = new ArrayList<Double>();
    for(int i = 0; i < course.getModuleCount(); i++) {
      this.moduleGrades.add(-1.0);
    }
  }

  public CourseInfo(Course course, ArrayList<Double> moduleGrades) {
    this.course = course;
    this.moduleGrades = moduleGrades;
    this.grade = 0;
    int count = 0;
    for(double grade : moduleGrades) {
      if (grade != -1) {
        this.grade += grade;
        count++;
      }
    }
    if (count > 0) {
      this.grade /= count;
    }
  }

  public double getGrade() {
    return grade;
  }

  public Course getCourse() {
    return course;
  }

  public ArrayList<Double> getGrades() {
    return moduleGrades;
  }

  /**
   * this method updates the grade
   * @param moduleNum
   * @param grade
   */
  public void updateGrade(int moduleNum, double grade) {
    this.moduleGrades.set(moduleNum, grade);
    int gradedModules = 0;
    int total = 0;
    for(int i = 0; i < moduleGrades.size(); i++) {
      if(moduleGrades.get(i) != -1) {
        gradedModules++;
        total += moduleGrades.get(i);
      }
    }
    this.grade = total/gradedModules;
  }
}
