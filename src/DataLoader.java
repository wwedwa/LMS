package src;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
  static UserList userList = UserList.getInstance();

  public static ArrayList<Course> loadCourses() {
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
        Course course = new Course(title, author, null, reviews, language, description, modules, difficulty, id);
        courses.add(course);
        JSONArray studentsJSON = (JSONArray)courseJSON.get(STUDENTS);
        assignCourse(studentsJSON, course);
			}
			return courses;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
  }

  private static void assignCourse(JSONArray studentsJSON, Course course) {
    for (int i = 0; i < studentsJSON.size(); ++i) {
      JSONObject studentJSON = (JSONObject)studentsJSON.get(i);
      UUID studentId = UUID.fromString((String)studentJSON.get(STUDENT_ID));
      User student = userList.getUserByUUID(studentId);
      student.registerCourse(course);
      JSONArray gradesJSON = (JSONArray)studentJSON.get(GRADES);
      ArrayList<Double> grades = new ArrayList<Double>();
      Iterator iterator = gradesJSON.iterator();
      while (iterator.hasNext()) {
        double grade = ((Long)iterator.next()).doubleValue();
        grades.add(grade);
      }
      student.setGrades(course, grades);
    }
  }

  private static ArrayList<Module> loadModules(JSONArray modulesJSON) {
    ArrayList<Module> modules = new ArrayList<Module>();
    for (int i = 0; i < modulesJSON.size(); ++i) {
      JSONObject moduleJSON = (JSONObject)modulesJSON.get(i);
      String title = (String)moduleJSON.get(MODULE_TITLE);
      JSONArray lessonsJSON = (JSONArray)moduleJSON.get(MODULE_LESSONS);
      ArrayList<Lesson> lessons = loadLessons(lessonsJSON);
      JSONArray questionsJSON = (JSONArray)moduleJSON.get(MODULE_QUIZ);
      Assessment quiz = loadAssessment(questionsJSON);
      modules.add(new Module(title, quiz, lessons));
    }
    return modules;
  }

  private static ArrayList<Review> loadReviews(JSONArray reviewsJSON) {
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

  private static ArrayList<Comment> loadComments(JSONArray commentsJSON) {
    ArrayList<Comment> comments = new ArrayList<Comment>();
    return comments;
  }

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

  private static ArrayList<String> loadChoices(JSONArray choicesJSON) {
    ArrayList<String> choices = new ArrayList<String>();
    for (int i = 0; i < choicesJSON.size(); ++i) {
      choices.add((String)choicesJSON.get(i));
    }
    return choices;
  }

  public static ArrayList<User> loadUsers() {
		ArrayList<User> users = new ArrayList<User>();
    
		try {
			FileReader reader = new FileReader(USER_FILE_NAME);
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i = 0; i < peopleJSON.size(); ++i) {
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				UUID id = UUID.fromString((String)personJSON.get(USER_ID));
				String username = (String)personJSON.get(USERNAME);
        String password = (String)personJSON.get(PASSWORD);
				String firstName = (String)personJSON.get(FIRST_NAME);
				String lastName = (String)personJSON.get(LAST_NAME);
				String email = (String)personJSON.get(EMAIL);
				String type = (String)personJSON.get(TYPE);

        if (type.equals("student")) {
				  users.add(new User(username, firstName, lastName, email, password, id));
        } else if (type.equals("author")) {
          users.add(new Author(username, firstName, lastName, email, password, id));
        }
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
  }

  public static void main(String[] args) {
    UserList userList = UserList.getInstance();
    ArrayList<Course> cs = loadCourses();
    Course c = cs.get(0); 
    System.out.println(c.getTitle());
    System.out.println(c.getDescription());
    System.out.println(c.getRating());
    System.out.println(c.getLanguage());
    ArrayList<Module> modules = c.getModules();
    for (Module m : modules) {
      System.out.println(m.getTitle());
      ArrayList<Lesson> ls = m.getLessons();
      for (Lesson l : ls) {
        System.out.println(l.getTitle());
        System.out.println(l.getContent());
        System.out.println("--------------------------------------");
      }
    }
    for (Review r : c.getReviews()) {
      System.out.println(r);
    }
  }
}
