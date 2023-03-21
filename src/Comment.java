package src;

import java.util.ArrayList;
import java.util.UUID;

public class Comment {
    private User author;
    private UUID commentid;
    private String description;
    private ArrayList<Comment> replies;

    public Comment(User author, String description, ArrayList<Comment> replies) {
        this.author = author;
        this.description = description;
        this.replies = replies;
    }
    public Comment(User author, String description) {
        this.author = author;
        this.description = description;
    }
    public void addReply(Comment comment) {
        replies.add(comment);
    }
}
