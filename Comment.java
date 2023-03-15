import java.util.ArrayList;
import java.util.UUID;

public class Comment {
    private User author;
    private UUID commentid;
    private String description;
    private ArrayList<Comment> replies;

    public Comment(User author, String description, ArrayList<Comment> replies) {

    }
    public void addReply(Comment comment) {

    }
}
