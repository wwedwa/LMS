package LMS;
public class Lesson {
    private String title;
    private String content;

    public Lesson(String title, String content, String example) {
        this.title = title;
        this.content = content;
    }
    public String getTitle() {
      return title;
    }
    public String getContent() {
      return content;
    }
}
