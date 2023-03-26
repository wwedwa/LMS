package src;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
  
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

  public static JSONObject getCourseJSON(Course course) {
		JSONObject courseDetails = new JSONObject();
		courseDetails.put(COURSE_ID, course.getCourseID().toString());
		courseDetails.put(AUTHOR_ID, course.getAuthor().getId().toString());
    courseDetails.put(RATING, course.getRating());
		courseDetails.put(TITLE, course.getTitle());
		courseDetails.put(DIFFICULTY, course.getDifficulty().toString());
		courseDetails.put(LANGUAGE, course.getLanguage().toString());
    courseDetails.put(DESCRIPTION, course.getDescription());
    courseDetails.put(MODULES, getModulesJSON(course.getModules()));
    courseDetails.put(REVIEWS, getReviewsJSON(course.getReviews()));
    return courseDetails;
	}

  public static JSONArray getModulesJSON(ArrayList<Module> modules) {
    JSONArray jsonModules = new JSONArray();
    for (int i = 0; i < modules.size(); ++i) {
      jsonModules.add(getModuleJSON(modules.get(i)));
    }
    return jsonModules;
  }

  public static JSONArray getReviewsJSON(ArrayList<Review> reviews) {
    JSONArray jsonReviews = new JSONArray();
    for (int i = 0; i < reviews.size(); ++i) {
      jsonReviews.add(getReviewJSON(reviews.get(i)));
    }
    return jsonReviews;
  }

  public static JSONObject getReviewJSON(Review review) {
    JSONObject reviewDetails = new JSONObject();
    reviewDetails.put(WRITER_ID, review.getWriter().getId().toString());
    reviewDetails.put(RATING, review.getRating());
    reviewDetails.put(REVIEW, review.getDescription());
    return reviewDetails;
  }

  public static JSONObject getModuleJSON(Module module) {
    JSONObject moduleDetails = new JSONObject();
    moduleDetails.put(MODULE_TITLE, module.getTitle());
    moduleDetails.put(MODULE_LESSONS, getLessonsJSON(module.getLessons()));
    moduleDetails.put(MODULE_QUIZ, getQuizJSON(module.getAssessment()));
    return moduleDetails;
  }

  public static JSONArray getLessonsJSON(ArrayList<Lesson> lessons) {
    JSONArray jsonLessons = new JSONArray();
    for (int i = 0; i < lessons.size(); ++i) {
      jsonLessons.add(getLessonJSON(lessons.get(i)));
    }
    return jsonLessons;
  }

  public static JSONObject getLessonJSON(Lesson lesson) {
    JSONObject lessonDetails = new JSONObject();
    lessonDetails.put(LESSON_TITLE, lesson.getTitle());
    lessonDetails.put(LESSON_CONTENT, lesson.getContent());
    return lessonDetails;
  }

  public static JSONArray getQuizJSON(Assessment quiz) {
    JSONArray jsonQuestions = new JSONArray();
    ArrayList<Question> questions = quiz.getQuestions();
    for (int i = 0; i < questions.size(); ++i) {
      jsonQuestions.add(getQuestionJSON(questions.get(i)));
    }
    return jsonQuestions;
  }

  public static JSONObject getQuestionJSON(Question question) {
    JSONObject questionDetails = new JSONObject();
    questionDetails.put(QUIZ_QUESTION, question.getQuestion());
    questionDetails.put(QUIZ_ANSWER, question.getCorrectAnswer());
    questionDetails.put(QUIZ_CHOICES, getChoicesJSON(question.getAnswerChoices()));
    return questionDetails;
  }

  public static JSONArray getChoicesJSON(ArrayList<String> choices) {
    JSONArray jsonChoices = new JSONArray();
    for (int i = 0; i < choices.size(); ++i) {
      jsonChoices.add(choices.get(i));
    }
    return jsonChoices;
  }

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
	
	public static JSONObject getUserJSON(User user) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(USER_ID, user.getId().toString());
		userDetails.put(USERNAME, user.getUsername());
    userDetails.put(PASSWORD, user.getPassword());
		userDetails.put(FIRST_NAME, user.getFirstName());
		userDetails.put(LAST_NAME, user.getLastName());
		userDetails.put(EMAIL, user.getEmail());
    userDetails.put(TYPE, user.getType());
    return userDetails;
	}

  public static void main(String[] args) {
    ArrayList<Course> course = DataLoader.loadCourses();
    saveCourses(course);
  }
}
