package src;

import java.util.ArrayList;
import java.util.UUID;

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
    public Comment(User author, String description) {
        this.author = author;
        this.comment = comment;
    }

    public User getWriter() {
      return author;
    }

    public String getComment() {
      return comment;
    }

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

    public String toString() {
      String toReturn = author.getUsername() + " says: " + comment + "\n";
      for (Comment reply : replies) {
        toReturn += reply.toString(1);
      }
      return toReturn;
    }

    public String toString(int depth) {
      String tabs = "";
      for (int i = 0; i < depth; i++) {
        tabs += "\t";
      }
      String toReturn = tabs + author.getUsername() + " replies: " + comment + "\n";
      for (Comment reply : replies) {
        toReturn += reply.toString(depth + 1);
      }
      return toReturn;
    }
}
