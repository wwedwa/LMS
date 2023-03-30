package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    if (reviews.size() > 0) {
      calculateRating();
    }
  }

  /**
  * add modules
  * @param module
  */
  public void addModules(Module module) {
    modules.add(module);
  }

  /**
  * add comment
  * @param comment
  */
  public void addComment(Comment comment) {
    comments.add(comment);
  }

  /**
  * add review
  * @param review
  */
  public void addReview(Review review) {
    reviews.add(review);
    updateRating(review.getRating());
  }

  /**
  * @return module at index
  * @param index
  */
  public Module getModule(int index) {
    return modules.get(index);
  }

  /**
  * update rating
  * @param rating
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
  * @return course id
  */
  public UUID getCourseID() {
    return courseID;
  }

  /**
  * @return rating
  */
  public double getRating() {
    return rating;
  }

  /**
  * @return title
  */
  public String getTitle() {
    return title;
  }

  /**
  * @return author
  */
  public User getAuthor() {
    return author;
  }

  /**
  * @return course comments
  */
  public ArrayList<Comment> getComments() {
    return comments;
  }

  /**
  * @return course reviews
  */
  public ArrayList<Review> getReviews() {
    return reviews;
  }

  /**
  * @return course language
  */
  public Language getLanguage() {
    return language;
  }

  /**
  * @return course description
  */
  public String getDescription() {
    return description;
  }

  /**
  * @return modules
  */
  public ArrayList<Module> getModules() {
    return modules;
  }

  /**
  * @return difficulty
  */
  public Difficulty getDifficulty() {
    return difficulty;
  }

  /**
  * @return the module index
  */
  public int getModuleCount() {
    return modules.size();
  }

  public String toString() {
    String toReturn = title;
    if (reviews.size() > 0) {
      toReturn += " - Rating: " + rating + "(" + reviews.size() + " reviews)";
    } else {
      toReturn += " - no reviews";
    }
    return toReturn;
  }

  /**
   * creates a certificate for a specific course for the user
   * @throws IOException
   */
  public boolean createCertificate(User user) {
    String fileName = title + ".txt";
    File file = new File(fileName);
    try {
      file.createNewFile();
      FileWriter writer = new FileWriter(fileName);
      writer.write("Congratulations "+ user.getFirstName()+" you completed "+ title +"!");
      writer.close();
    } catch (IOException e) {
      return false;
    }
    return true;
  }
}
