package src;

import java.util.ArrayList;

public class Module {
    private String title;
    private Assessment assessment;
    private ArrayList<Comment> comments;
    private ArrayList<Lesson> lessons;

    public Module(String title, Assessment assessment, ArrayList<Lesson> lessons) {
        this.title = title;
        this.assessment = assessment;
        comments = new ArrayList<Comment>();
        this.lessons = lessons;
    }
    public ArrayList<Comment> getComments() {
      return comments;
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }
    public Assessment getAssessment() {
      return assessment;
    }
    public String getTitle() {
        return this.getTitle();
    }
    public ArrayList<Lesson> getLessons() {
      return lessons;
    }
}
