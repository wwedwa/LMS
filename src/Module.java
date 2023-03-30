package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Module class
 * @author The Lobsters
 */
public class Module {
  private String title;
  private Assessment assessment;
  private ArrayList<Comment> comments;
  private ArrayList<Lesson> lessons;
  /**
   * Module constructor
   * @param title
   * @param assessment
   * @param lessons
   */
  public Module(String title, Assessment assessment, ArrayList<Lesson> lessons) {
    this.title = title;
    this.assessment = assessment;
    comments = new ArrayList<Comment>();
    this.lessons = lessons;
  }
  /**
   * Module constructor with comments
   * @param title
   * @param assessment
   * @param lessons
   * @param comments
   */
  public Module(String title, Assessment assessment, ArrayList<Lesson> lessons, ArrayList<Comment> comments) {
    this.title = title;
    this.assessment = assessment;
    this.lessons = lessons;
    this.comments = comments;
  }
  /** 
   * comment accessor
   * @return ArrayList<Comment>
   */
  public ArrayList<Comment> getComments() {
    return comments;
  }
  /** 
   * add a comment
   * @param comment
   */
  public void addComment(Comment comment) {
    comments.add(comment);
  }
  /** 
   * add a lesson
   * @param lesson
   */
  public void addLesson(Lesson lesson) {
    lessons.add(lesson);
  }
  /** 
   * assessment accessor
   * @return Assessment
   */
  public Assessment getAssessment() {
    return assessment;
  }
  /** 
   * title accessor
   * @return String
   */
  public String getTitle() {
    return this.title;
  }
  /** 
   * lesson accessor
   * @return ArrayList<Lesson>
   */
  public ArrayList<Lesson> getLessons() {
    return lessons;
  }
  /** 
   * toString method
   * @return String
   */
  public String toString() {
    return this.title;
  }

  /**
   * saves a module to a file
   * @return true or false if module is saved
   */
  public boolean saveModule() {
    String fileName = title + ".txt";
    File file = new File(fileName);
    try {
      file.createNewFile();
      FileWriter writer = new FileWriter(fileName);
      writer.write(title + "\n");
      for (int i = 0; i < lessons.size(); i++) {
        String currLesson = lessons.get(i).toString();
        writer.write("\n" + currLesson + "\n");
      }
      writer.close();
    } catch (IOException e) {
      return false;
    }
    return true;
  }
}
