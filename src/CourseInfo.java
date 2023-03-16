package src;

import java.util.ArrayList;

public class CourseInfo {
    private Course course;
    private double grade;
    private ArrayList<Double> moduleGrades;

    public CourseInfo(Course course) {
        this.course = course;
    }
    public void updateGrade(int moduleNum, double grade) {
        if (moduleGrades.size() >= moduleNum) {
            this.moduleGrades.set(moduleNum, grade);
        }
    }
}
