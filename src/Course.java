package src;

import java.util.ArrayList;
import java.util.UUID;

/**
 * course class
 * @author The Lobsters
 */
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
        this.comments = new ArrayList<Comment>();
        this.reviews = new ArrayList<Review>();
        this.language = language;
        this.description = description;
        this.modules = modules;
        this.difficulty = difficulty;
        this.courseID = UUID.randomUUID();
    }
    public Course(String title, double rating, User author, ArrayList<Comment> comments, ArrayList<Review> reviews, Language language, String description, ArrayList<Module> modules, Difficulty difficulty, UUID id) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.comments = comments;
        this.reviews = reviews;
        this.language = language;
        this.description = description;
        this.modules = modules;
        this.difficulty = difficulty;
        this.courseID = id;

    }
    public void addModules(Module module) {
        modules.add(module);
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public void addReview(Review review) {
        reviews.add(review);
    }
    public Module getModule(int index) {
        return modules.get(index);
    }
    private void updateRating(int rating) {
        // multiply out to use for average
        double total = this.rating*reviews.size();
        // find new average
        this.rating = (total + rating)/(reviews.size()+1);
    }
    public void createCertificate(User user) {
        return;
    }
    public UUID getCourseID() {
        return courseID;
    }
    public double getRating() {
        return rating;
    }
    public String getTitle() {
        return title;
    }
    public User getAuthor() {
        return author;
    }
    public ArrayList<Comment> getComments() {
        return comments;
    }
    public ArrayList<Review> getReviews() {
        return reviews;
    }
    public Language getLanguage() {
        return language;
    }
    public String getDescription() {
        return description;
    }
    public ArrayList<Module> getModules() {
        return modules;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }
    public int getModuleCount() {
        return moduleCount;
    }
    
}
