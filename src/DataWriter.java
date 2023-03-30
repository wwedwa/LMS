package src;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
  
  /**
   * Saves all courses into a json file
   * @param courses
   */
  public static void saveCourses(ArrayList<Course> courses) {
    JSONArray jsonCourses = new JSONArray();
    for (int i = 0; i < courses.size(); ++i) {
      jsonCourses.add(getCourseJSON(courses.get(i)));
    }
    try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) {
      file.write(jsonCourses.toJSONString());
      file.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  /**
   * Create JSONOBject for courses to be saved in json file
   * @param course
   * @return JSONObject for given course
   */
  public static JSONObject getCourseJSON(Course course) {
		JSONObject courseDetails = new JSONObject();
		courseDetails.put(COURSE_ID, course.getCourseID().toString());
		courseDetails.put(AUTHOR_ID, course.getAuthor().getId().toString());
		courseDetails.put(TITLE, course.getTitle());
		courseDetails.put(DIFFICULTY, course.getDifficulty().toString());
		courseDetails.put(LANGUAGE, course.getLanguage().toString());
    courseDetails.put(DESCRIPTION, course.getDescription());
    courseDetails.put(STUDENTS, getStudentsJSON(course));
    courseDetails.put(COMMENTS, getCommentsJSON(course.getComments()));
    courseDetails.put(REVIEWS, getReviewsJSON(course.getReviews()));
    courseDetails.put(MODULES, getModulesJSON(course.getModules()));
    return courseDetails;
	}

  /**
   * Creates JSONArray of students that are taking the given course
   * @param course
   * @return JSONArray of students in a particular course
   */
  public static JSONArray getStudentsJSON(Course course) {
    UserList userList = UserList.getInstance();
    JSONArray jsonStudents = new JSONArray();
    for (User user : userList.getUsers()) {
      if (user.isEnrolled(course)) {
        jsonStudents.add(getStudentJSON(user, course));
      }
    }
    return jsonStudents;
  }

  /**
   * Creates JSONObject representing a student and their grades in given course
   * @param user
   * @param course
   * @return JSONObject for student in the given course
   */
  public static JSONObject getStudentJSON(User user, Course course) {
    JSONObject studentDetails = new JSONObject();
    studentDetails.put(STUDENT_ID, user.getId().toString());
    studentDetails.put(GRADES, user.getGrades(course));
    return studentDetails;
  }

  /**
   * Creates JSONArray of modules made from the given module list
   * @param modules
   * @return JSONArray of modules 
   */
  public static JSONArray getModulesJSON(ArrayList<Module> modules) {
    JSONArray jsonModules = new JSONArray();
    for (int i = 0; i < modules.size(); ++i) {
      jsonModules.add(getModuleJSON(modules.get(i)));
    }
    return jsonModules;
  }

  /**
   * Recursively creates JSONArray of comments from given list of comments
   * @param comments
   * @return JSONArray of comments
   */
  public static JSONArray getCommentsJSON(ArrayList<Comment> comments) {
    JSONArray jsonComments = new JSONArray();
    for (int i = 0; i < comments.size(); ++i) {
      jsonComments.add(getCommentJSON(comments.get(i)));
    }
    return jsonComments;
  }

  /**
   * Creates JSONObject for given comment
   * @param comment
   * @return JSONObject of the given comment
   */
  public static JSONObject getCommentJSON(Comment comment) {
    JSONObject commentDetails = new JSONObject();
    commentDetails.put(WRITER_ID, comment.getAuthor().getId().toString());
    commentDetails.put(COMMENT, comment.getComment());
    commentDetails.put(REPLIES, getCommentsJSON(comment.getReplies()));
    return commentDetails;
  }

  /**
   * Creates JSONArray of given reviews
   * @param reviews
   * @return JSONArray of given reviews
   */
  public static JSONArray getReviewsJSON(ArrayList<Review> reviews) {
    JSONArray jsonReviews = new JSONArray();
    for (int i = 0; i < reviews.size(); ++i) {
      jsonReviews.add(getReviewJSON(reviews.get(i)));
    }
    return jsonReviews;
  }

  /**
   * Creates JSONObject of given review
   * @param review
   * @return JSONObject of given review
   */
  public static JSONObject getReviewJSON(Review review) {
    JSONObject reviewDetails = new JSONObject();
    reviewDetails.put(WRITER_ID, review.getWriter().getId().toString());
    reviewDetails.put(RATING, review.getRating());
    reviewDetails.put(REVIEW, review.getDescription());
    return reviewDetails;
  }

  /**
   * Creates JSONObject of given module
   * @param module
   * @return JSONObject of given module
   */
  public static JSONObject getModuleJSON(Module module) {
    JSONObject moduleDetails = new JSONObject();
    moduleDetails.put(MODULE_TITLE, module.getTitle());
    moduleDetails.put(COMMENTS, getCommentsJSON(module.getComments()));
    moduleDetails.put(MODULE_LESSONS, getLessonsJSON(module.getLessons()));
    moduleDetails.put(MODULE_QUIZ, getQuizJSON(module.getAssessment()));
    return moduleDetails;
  }

  /**
   * Creates JSONArray from given list of lessons
   * @param lessons
   * @return JSONArray of lessons
   */
  public static JSONArray getLessonsJSON(ArrayList<Lesson> lessons) {
    JSONArray jsonLessons = new JSONArray();
    for (int i = 0; i < lessons.size(); ++i) {
      jsonLessons.add(getLessonJSON(lessons.get(i)));
    }
    return jsonLessons;
  }

  /**
   * Creates JSONObject of the given lesson
   * @param lesson
   * @return JSONObject of the given lesson
   */
  public static JSONObject getLessonJSON(Lesson lesson) {
    JSONObject lessonDetails = new JSONObject();
    lessonDetails.put(LESSON_TITLE, lesson.getTitle());
    lessonDetails.put(LESSON_CONTENT, lesson.getContent());
    return lessonDetails;
  }

  /**
   * Creates JSONArray of questions representing the quiz of a module
   * @param quiz
   * @return JSONArray of questions
   */
  public static JSONArray getQuizJSON(Assessment quiz) {
    JSONArray jsonQuestions = new JSONArray();
    ArrayList<Question> questions = quiz.getQuestions();
    for (int i = 0; i < questions.size(); ++i) {
      jsonQuestions.add(getQuestionJSON(questions.get(i)));
    }
    return jsonQuestions;
  }

  /**
   * Creates JSONObject of question 
   * @param question
   * @return JSONObject of question 
   */
  public static JSONObject getQuestionJSON(Question question) {
    JSONObject questionDetails = new JSONObject();
    questionDetails.put(QUIZ_QUESTION, question.getQuestion());
    questionDetails.put(QUIZ_ANSWER, question.getCorrectAnswer());
    questionDetails.put(QUIZ_CHOICES, getChoicesJSON(question.getAnswerChoices()));
    return questionDetails;
  }

  /**
   * Creates JSONArray of choices for a question
   * @param choices
   * @return JSONArray of String choices
   */
  public static JSONArray getChoicesJSON(ArrayList<String> choices) {
    JSONArray jsonChoices = new JSONArray();
    for (int i = 0; i < choices.size(); ++i) {
      jsonChoices.add(choices.get(i));
    }
    return jsonChoices;
  }

  /**
   * Saves user data into a JSON file
   * @param users
   */
	public static void saveUsers(ArrayList<User> users) {
		JSONArray jsonUsers = new JSONArray();
		for (int i = 0; i < users.size(); ++i) {
			jsonUsers.add(getUserJSON(users.get(i)));
		}
    try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
      file.write(jsonUsers.toJSONString());
      file.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
	
  /**
   * Creates JSONObject with User data
   * @param user
   * @return JSONObject
   */
	public static JSONObject getUserJSON(User user) {
		JSONObject userDetails = new JSONObject();
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
		userDetails.put(USER_ID, user.getId().toString());
		userDetails.put(USERNAME, user.getUsername());
    userDetails.put(PASSWORD, user.getPassword());
		userDetails.put(FIRST_NAME, user.getFirstName());
		userDetails.put(LAST_NAME, user.getLastName());
		userDetails.put(EMAIL, user.getEmail());
    userDetails.put(TYPE, user.getType());
    userDetails.put(USER_DOB, formatter.format(user.getBirthday()));
    return userDetails;
	}
}
