package LMS;

import java.util.ArrayList;
import java.util.UUID;

public class Author extends User {
    private ArrayList<Course> createdCourses;
    private double rating;

    public Author(String firstName, String lastName, String email, String username, String password, String type) {
      super(username, firstName, lastName, email, password);
    }

    public Author(String firstName, String lastName, String email, String username, String password, UUID id) {
      super(username, firstName, lastName, email, password, id);
    }

    public void updateRating(int rating) {
        this.rating = rating;
    }
}
