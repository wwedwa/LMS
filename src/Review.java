package src;

/**
 * Review class
 * @author The Lobsters
 */
public class Review {
  private User writer;
  private int rating;
  private String description;

  public Review(User writer, int rating, String description) {
    this.writer = writer;
    this.rating = rating;
    this.description = description;
  }
  /** 
   * rating accessor
   * @return int
   */
  public int getRating() {
    return rating;
  }
  /** 
   * writer accessor
   * @return User
   */
  public User getWriter() {
    return writer;
  }
  /** 
   * description accessor
   * @return String
   */
  public String getDescription() {
    return description;
  }
  /** 
   * toString
   * @return String
   */
  public String toString() {
    return writer.getUsername() +
    " says \"" + description + "\"" +
    "\nRating: " + rating;
  }
}
