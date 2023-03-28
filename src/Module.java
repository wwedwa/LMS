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

  public Module(String title, Assessment assessment, ArrayList<Lesson> lessons, ArrayList<Comment> comments) {
    this.title = title;
    this.assessment = assessment;
    this.lessons = lessons;
    this.comments = comments;
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
    return this.title;
  }
  
  public ArrayList<Lesson> getLessons() {
    return lessons;
  }

  public String toString() {
    return this.title;
  }
}
