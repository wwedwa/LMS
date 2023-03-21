package src;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String type;
    private UUID userid;
    private ArrayList<Course> registeredCourses;
    private ArrayList<Course> completedCourses;
    private ArrayList<CourseInfo> gradeBook;
    private ArrayList<Author> favoriteAuthors;

    public User(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userid = UUID.randomUUID();
        this.type = "student";
    }
    public User(String username, String firstName, String lastName, String email, String password, UUID userid) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userid = userid;
        this.type = "student";
    }

    public String getUsername() {
        return this.username;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public UUID getId() {
        return this.userid;
    }
    public String getType() {
        return "student";
    }
    public ArrayList<Course> getRegisteredCourses() {
        return this.registeredCourses;
    }
    public ArrayList<Course> getCompletedCourses() {
        return this.completedCourses;
    }
    public void registerCourse(Course course) {
        this.registeredCourses.add(course);
    }
    public void unregisterCourse(Course course) {
        this.registeredCourses.remove(course);
    }
    public void addCompletedCourse(Course course) {
        this.completedCourses.add(course);
    }
    public void addFavAuthor(Author author) {
        this.favoriteAuthors.add(author);
    }
    public void removeFavAuthor(Author author) {
        this.favoriteAuthors.remove(author);
    }
    public void updateCourseGrade(int moduleNum, double grade) {

    }
}
