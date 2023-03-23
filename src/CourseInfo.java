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
        this.moduleGrades = new ArrayList<Double>(course.getModuleCount());
        for(int i = 0; i < course.getModuleCount(); i++) {
            this.moduleGrades.set(i, -1.0);
        }
    }
    /**
    * returns a courseList
    */
    public double getGrade() {
      return grade;
    }
    /**
     * this method updates the grade
     * @param moduleNum
     * @param grade
     */
    public void updateGrade(int moduleNum, double grade) {
        if (moduleGrades.size() >= moduleNum) {
            this.moduleGrades.set(moduleNum, grade);
        }
        int gradedModules = 0;
        for(int i = 0; i < moduleGrades.size(); i++) {
            if(moduleGrades.get(i) != -1) {
                gradedModules++;
            }
        }
        this.grade = ((this.grade + grade)/gradedModules);
    }
}
