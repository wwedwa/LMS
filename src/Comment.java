package src;

import java.util.ArrayList;
import java.util.UUID;

/**
 * comment class
 * @author The Lobsters
 */
public class Comment {
    private User author;
    private UUID commentid;
    private String description;
    private ArrayList<Comment> replies;
    /**
    * Comment constructor with replies
    *@param author
    *@param description
    *@param replies
    */
    public Comment(User author, String description, ArrayList<Comment> replies) {
        this.author = author;
        this.description = description;
        this.replies = replies;
    }
    /**
    * Comment constructor
    *@param author
    *@param description
    */
    public Comment(User author, String description) {
        this.author = author;
        this.description = description;
    }
    /**
    * add reply comment
    *@param comment
    */
    public void addReply(Comment comment) {
        replies.add(comment);
    }
}
