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
    private Language topic;
    private String description;
    private ArrayList<Module> modules;
    private Difficulty difficulty;
    private int moduleCount;

    public Course(String title, User author, Language topic, String description, ArrayList<Module> modules, Difficulty difficulty) {
        this.title = title;
        this.author = author;
        this.topic = topic;
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
