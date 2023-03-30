package src;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

  /**
   * Loads courses from JSON file into courseList
   * @return ArrayList<Course>
   */
  public static ArrayList<Course> loadCourses() {
    UserList userList = UserList.getInstance();
    ArrayList<Course> courses = new ArrayList<Course>();
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);
			for(int i = 0; i < coursesJSON.size(); ++i) {
				JSONObject courseJSON = (JSONObject)coursesJSON.get(i);
				UUID id = UUID.fromString((String)courseJSON.get(COURSE_ID));
        UUID authorId = UUID.fromString((String)courseJSON.get(AUTHOR_ID));
        String title = (String)courseJSON.get(TITLE);
        Difficulty difficulty = Difficulty.valueOf((String)courseJSON.get(DIFFICULTY));
        Language language = Language.valueOf((String)courseJSON.get(LANGUAGE));
        String description = (String)courseJSON.get(DESCRIPTION);
        User author = userList.getUserByUUID(authorId);
        JSONArray modulesJSON = (JSONArray)courseJSON.get(MODULES);
        ArrayList<Module> modules = loadModules(modulesJSON);
        JSONArray reviewsJSON = (JSONArray)courseJSON.get(REVIEWS);
        ArrayList<Review> reviews = loadReviews(reviewsJSON);
        JSONArray commentsJSON = (JSONArray)courseJSON.get(COMMENTS);
        ArrayList<Comment> comments = loadComments(commentsJSON);
        Course course = new Course(title, author, comments, reviews, language, description, modules, difficulty, id);
        courses.add(course);
        author.addCreatedCourse(course);
        JSONArray studentsJSON = (JSONArray)courseJSON.get(STUDENTS);
        assignCourse(studentsJSON, course);
			}
			return courses;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
  }

  /**
   * Assigns given course and grades to the users taking them
   * @param studentsJSON
   * @param course
   */
  private static void assignCourse(JSONArray studentsJSON, Course course) {
    UserList userList = UserList.getInstance();
    for (int i = 0; i < studentsJSON.size(); ++i) {
      JSONObject studentJSON = (JSONObject)studentsJSON.get(i);
      UUID studentId = UUID.fromString((String)studentJSON.get(STUDENT_ID));
      User student = userList.getUserByUUID(studentId);
      JSONArray gradesJSON = (JSONArray)studentJSON.get(GRADES);
      ArrayList<Double> grades = new ArrayList<Double>();
      Iterator iterator = gradesJSON.iterator();
      while (iterator.hasNext()) {
        double grade = ((Long)iterator.next()).doubleValue();
        grades.add(grade);
      }
      student.registerCourse(course, grades);
    }
  }

  /**
   * Loads modules from JSON into a list of module objects
   * @param modulesJSON
   * @return ArrayList<Module>
   */
  private static ArrayList<Module> loadModules(JSONArray modulesJSON) {
    ArrayList<Module> modules = new ArrayList<Module>();
    for (int i = 0; i < modulesJSON.size(); ++i) {
      JSONObject moduleJSON = (JSONObject)modulesJSON.get(i);
      String title = (String)moduleJSON.get(MODULE_TITLE);
      JSONArray lessonsJSON = (JSONArray)moduleJSON.get(MODULE_LESSONS);
      ArrayList<Lesson> lessons = loadLessons(lessonsJSON);
      JSONArray commentsJSON = (JSONArray)moduleJSON.get(COMMENTS);
      ArrayList<Comment> comments = loadComments(commentsJSON);
      JSONArray questionsJSON = (JSONArray)moduleJSON.get(MODULE_QUIZ);
      Assessment quiz = loadAssessment(questionsJSON);
      modules.add(new Module(title, quiz, lessons, comments));
    }
    return modules;
  }

  /**
   * Loads reviews from JSON into a list of review objects
   * @param reviewsJSON
   * @return ArrayList<Review>
   */
  private static ArrayList<Review> loadReviews(JSONArray reviewsJSON) {
    UserList userList = UserList.getInstance();
    ArrayList<Review> reviews = new ArrayList<Review>();
    for (int i = 0; i < reviewsJSON.size(); ++i) {
      JSONObject reviewJSON = (JSONObject)reviewsJSON.get(i);
      UUID userId = UUID.fromString((String)reviewJSON.get(WRITER_ID));
      User user = userList.getUserByUUID(userId);
      int rating = ((Long)reviewJSON.get(RATING)).intValue();
      String description = (String)reviewJSON.get(REVIEW);
      reviews.add(new Review(user, rating, description));
    }
    return reviews;
  }

  /**
   * Recursively loads comements from JSON into a list of comment objects
   * @param commentsJSON
   * @return ArrayList<Comment>
   */
  private static ArrayList<Comment> loadComments(JSONArray commentsJSON) {
    UserList userList = UserList.getInstance();
    ArrayList<Comment> comments = new ArrayList<Comment>();
    for (int i = 0; i < commentsJSON.size(); i++) {
      JSONObject commentJSON = (JSONObject)commentsJSON.get(i);
      UUID userId = UUID.fromString((String)commentJSON.get(WRITER_ID));
      User user = userList.getUserByUUID(userId);
      String comment = (String)commentJSON.get(COMMENT);
      JSONArray repliesJSON = (JSONArray)commentJSON.get(REPLIES);
      ArrayList<Comment> replies = loadComments(repliesJSON);
      comments.add(new Comment(user, comment, replies));
    }
    return comments;
  }

  /**
   * Loads lessons from JSON into a list of lesson objects
   * @param lessonsJSON
   * @return ArrayList<Lesson>
   */
  private static ArrayList<Lesson> loadLessons(JSONArray lessonsJSON) {
    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    for (int i = 0; i < lessonsJSON.size(); ++i) {
      JSONObject lessonJSON = (JSONObject)lessonsJSON.get(i);
      String title = (String)lessonJSON.get(LESSON_TITLE);
      String content = (String)lessonJSON.get(LESSON_CONTENT);
      lessons.add(new Lesson(title, content));
    }
    return lessons;
  }

  /**
   * Loads Assessment from JSON for modules in the courses
   * @param questionsJSON
   * @return Assessmnet
   */
  private static Assessment loadAssessment(JSONArray questionsJSON) {
    ArrayList<Question> questions = new ArrayList<Question>();
    for (int i = 0; i < questionsJSON.size(); ++i) {
      JSONObject questionJSON = (JSONObject)questionsJSON.get(i);
      String question = (String)questionJSON.get(QUIZ_QUESTION);
      int answer = ((Long)questionJSON.get(QUIZ_ANSWER)).intValue();
      JSONArray choicesJSON = (JSONArray)questionJSON.get(QUIZ_CHOICES);
      ArrayList<String> choices = loadChoices(choicesJSON);
      questions.add(new Question(question, choices, answer));
    }
    return new Assessment(questions);
  }

  /**
   * Loads assessment choises from JSON for the quizzes of modules
   * @param choicesJSON
   * @return ArrayList<String>
   */
  private static ArrayList<String> loadChoices(JSONArray choicesJSON) {
    ArrayList<String> choices = new ArrayList<String>();
    for (int i = 0; i < choicesJSON.size(); ++i) {
      choices.add((String)choicesJSON.get(i));
    }
    return choices;
  }

  /**
   * Loads users from JSON into userList
   * @return ArrayList<User>
   */
  public static ArrayList<User> loadUsers() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			FileReader reader = new FileReader(USER_FILE_NAME);
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i = 0; i < peopleJSON.size(); ++i) {
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				UUID id = UUID.fromString((String)personJSON.get(USER_ID));
        Date birthday = new SimpleDateFormat("MM/dd/yyyy").parse((String)personJSON.get(USER_DOB));
				String username = (String)personJSON.get(USERNAME);
        String password = (String)personJSON.get(PASSWORD);
				String firstName = (String)personJSON.get(FIRST_NAME);
				String lastName = (String)personJSON.get(LAST_NAME);
				String email = (String)personJSON.get(EMAIL);
				String type = (String)personJSON.get(TYPE);

        if (type.equals("student")) {
				  users.add(new Student(username, firstName, lastName, email, password, id, birthday));
        } else if (type.equals("author")) {
          users.add(new Author(username, firstName, lastName, email, password, id, birthday));
        }
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
  }
}
