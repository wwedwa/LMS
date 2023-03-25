package src;

public class Review {
  private User writer;
  private int rating;
  private String description;

  public Review(User writer, int rating, String description) {
    this.writer = writer;
    this.rating = rating;
    this.description = description;
  }
  
  public int getRating() {
    return rating;
  }

  public User getWriter() {
    return writer;
  }

  public String getDescription() {
    return description;
  }
  
  public String toString() {
    return writer.getUsername() +
    " says \"" + description + "\"" +
    "\nRating: " + rating;
  }
}
