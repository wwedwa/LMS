package LMS;

import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID courseID;
    private double rating;
    private String title;
    private User author;
    private ArrayList<Comment> comments;
    private ArrayList<Review> reviews;
    private Language language;
    private String description;
    private ArrayList<Module> modules;
    private Difficulty difficulty;
    private int moduleCount;

    public Course(String title, User author, Language language, String description, ArrayList<Module> modules, Difficulty difficulty) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.description = description;
        this.modules = modules;
        this.difficulty = difficulty;
    }
    public void addModules(Lesson lesson) {
        return;
    }
    public void addComment(Comment comment) {
        return;
    }
    public void addReview(Review review) {
        return;
    }
    public Lesson getModule(int index) {
        return null;
    }
    public void updateRating(int rating) {
        return;
    }
    public void createCertificate() {
        return;
    }
}
