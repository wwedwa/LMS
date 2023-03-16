package LMS;

public class Review {
    private User author;
    private int rating;
    private String description;

    public Review(User author, int rating, String description) {
        this.author = author;
        this.rating = rating;
        this.description = description;
    }
    public String toString() {
        return author.getUsername() +
            " says \"" + description + "\"" +
            "\nRating" + rating;
    }
}
