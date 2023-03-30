package src;

import java.util.ArrayList;

/**
 * comment class
 * @author The Lobsters
 */
public class Comment {
  private User author;
  private String comment;
  private ArrayList<Comment> replies;
  /**
  * Comment constructor with replies
  *@param author
  *@param comment
  *@param replies
  */
  public Comment(User author, String comment, ArrayList<Comment> replies) {
    this.author = author;
    this.comment = comment;
    this.replies = replies;
  }
  /**
  * Comment constructor
  *@param author
  *@param comment
  */
  public Comment(User author, String comment) {
    this.author = author;
    this.comment = comment;
    this.replies = new ArrayList<Comment>();
  }
  /**
   * author accessor
   * @return author
   */
  public User getAuthor() {
    return author;
  }
  /**
   * comment accessor
   * @return comment
   */
  public String getComment() {
    return comment;
  }
  /**
   * replies accessor
   * @return replies
   */
  public ArrayList<Comment> getReplies() {
    return replies;
  }
  /**
  * add reply comment
  *@param comment
  */
  public void addReply(Comment comment) {
    replies.add(comment);
  }
  /**
   * toString method
   * @return String
   */
  public String toString() {
    return author.getUsername() + " says: " + comment;
  }
}
