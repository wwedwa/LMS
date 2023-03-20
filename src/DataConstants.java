package src;

public abstract class DataConstants {
  // Constants for user JSON file
  protected static final String USER_FILE_NAME = "json/users.json";
  protected static final String COURSE_FILE_NAME = "json/courses.json";
  protected static final String USER_ID = "id";
  protected static final String TYPE = "type";
  protected static final String USERNAME = "username";
  protected static final String PASSWORD = "password";
  protected static final String FIRST_NAME = "firstName";
  protected static final String LAST_NAME = "lastName";
  protected static final String EMAIL = "email";
  // Constants for course JSON file
  protected static final String COURSE_ID = "id";
  protected static final String AUTHOR_ID = "author";
  protected static final String RATING = "rating";
  protected static final String TITLE = "title";
  protected static final String DIFFICULTY = "difficulty";
  protected static final String LANGUAGE = "language";
  protected static final String DESCRIPTION = "description";
  // Constants for modules within courses
  protected static final String MODULES = "modules";
  protected static final String MODULE_TITLE = "title";
  protected static final String MODULE_COMMENTS = "comments";
  protected static final String MODULE_LESSONS = "lessons";
  protected static final String MODULE_QUIZ = "quiz";
  // Constants for lessons within modules
  protected static final String LESSON_TITLE = "title";
  protected static final String LESSON_CONTENT = "content";
  // Constants for quizzes within modules
  protected static final String QUIZ_QUESTION = "question";
  protected static final String QUIZ_ANSWER = "correctAnswer";
  protected static final String QUIZ_CHOICES = "answerChoices";
}
