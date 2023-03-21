package src;

import java.util.ArrayList;

public class Module {
    private String title;
    private Assessment quiz;
    private ArrayList<Comment> comments;
    private ArrayList<Lesson> lessons;

    public Module(String title, Assessment quiz, ArrayList<Lesson> lessons) {
        this.title = title;
        this.quiz = quiz;
        comments = new ArrayList<Comment>();
        this.lessons = lessons;
    }
    public String getTitle() {
      return title;
    }
    public ArrayList<Lesson> getLessons() {
      return lessons;
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }
}
