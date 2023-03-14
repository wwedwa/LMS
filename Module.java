package LMS;
public class Module {
    private String title;
    private Assessment quiz;
    private ArrayList<Comment> comments;
    private ArrayList<Lesson> lessons;

    public Module(String title, Assessment quiz) {
        this.title = title;
        this.quiz = quiz;
    }
    public void addComment(Comment comment) {
        return;
    }
    public void addLesson(Lesson lesson) {
        return;
    }
}
