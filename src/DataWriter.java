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
		courseDetails.put(DIFFICULTY, course.getDifficulty());
		courseDetails.put(LANGUAGE, course.getLanguage());
    courseDetails.put(DESCRIPTION, course.getDescription());
    return courseDetails;
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
}
