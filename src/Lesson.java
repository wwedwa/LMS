package src;
/**
 * Lesson class
 * @author The Lobsters
 */
public class Lesson {
  private String title;
  private String content;
  /**
   * Constructor that creates a Lesson
   * @param title
   * @param content
   */
  public Lesson(String title, String content) {
    this.title = title;
    this.content = content;
  }
  /**
   * Title accessor method
   * @return String title
   */
  public String getTitle() {
    return title;
  }
  /**
   * Title content method
   * @return String content
   */
  public String getContent() {
    return content;
  }

  public String toString() {
    return "----- " + title + " -----\n" + content;
  }
}
