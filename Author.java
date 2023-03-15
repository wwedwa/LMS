package LMS;

import java.util.ArrayList;
import java.util.UUID;

public class Author extends User {
    private ArrayList<Course> createdCourses;
    private double rating;

    public Author(String firstName, String lastName, String email, String username, String password, String type) {
      super(firstName, lastName, email, username, password, type);
    }

    public Author(String firstName, String lastName, String email, String username, String password, String type, UUID id) {
      super(firstName, lastName, email, username, password, type, id);
    }

    public void updateRating(int rating) {
        
    }
}
