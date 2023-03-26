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

  /**
  * Course constructor
  *@param title
  *@param author
  *@param langauge
  *@param description
  *@param modules
  *@param difficulty
  */
  public Course(String title, User author, Language language, String description, ArrayList<Module> modules, Difficulty difficulty) {
    this.title = title;
    this.author = author;
    this.comments = new ArrayList<Comment>();
    this.reviews = new ArrayList<Review>();
    this.language = language;
    this.description = description;
    this.modules = modules;
    this.difficulty = difficulty;
    this.rating = 0;
    this.courseID = UUID.randomUUID();
  }

  /**
  * Course constructor
  *@param title
  *@param rating
  *@param author
  *@param comments
  *@param reviews
  *@param langauge
  *@param description
  *@param modules
  *@param difficulty
  *@param id
  */
  public Course(String title, User author, ArrayList<Comment> comments, ArrayList<Review> reviews, Language language, String description, ArrayList<Module> modules, Difficulty difficulty, UUID id) {
    this.title = title;
    this.author = author;
    this.comments = comments;
    this.reviews = reviews;
    this.language = language;
    this.description = description;
    this.modules = modules;
    this.difficulty = difficulty;
    this.courseID = id;
    calculateRating();
  }

  /**
  * add modules
  *@param module
  */
  public void addModules(Module module) {
    modules.add(module);
  }

  /**
  * add comment
  *@param comment
  */
  public void addComment(Comment comment) {
    comments.add(comment);
  }

  /**
  * add review
  *@param review
  */
  public void addReview(Review review) {
    reviews.add(review);
    updateRating(review.getRating());
  }

  /**
  * returns module at index
  *@param index
  */
  public Module getModule(int index) {
    return modules.get(index);
  }

  /**
  * update rating
  *@param rating
  */
  private void updateRating(int rating) {
    // multiply out to use for average
    double total = this.rating * reviews.size();
    // find new average
    this.rating = (total + rating) / (reviews.size() + 1);
  }

  private void calculateRating() {
    int count, total;
    count = total = 0;
    for (Review review : reviews) {
      total += review.getRating();
      count++;
    }
    rating = total / count;
  }

  /**
  * create certificate for the user
  *@param user
  */
  public void createCertificate(User user) {
    return;
  }

  /**
  * returns course id
  */
  public UUID getCourseID() {
    return courseID;
  }

  /**
  * returns rating
  */
  public double getRating() {
    return rating;
  }

  /**
  * returns title
  */
  public String getTitle() {
    return title;
  }

  /**
  * returns author
  */
  public User getAuthor() {
    return author;
  }

  /**
  * returns course comments
  */
  public ArrayList<Comment> getComments() {
    return comments;
  }

  /**
  * returns course reviews
  */
  public ArrayList<Review> getReviews() {
    return reviews;
  }

  /**
  * returns course language
  */
  public Language getLanguage() {
    return language;
  }

  /**
  * returns course description
  */
  public String getDescription() {
    return description;
  }

  /**
  * returns modules
  */
  public ArrayList<Module> getModules() {
    return modules;
  }

  /**
  * returns difficulty
  */
  public Difficulty getDifficulty() {
    return difficulty;
  }

  /**
  * returns the module index
  */
  public int getModuleCount() {
    return moduleCount;
  }
}
