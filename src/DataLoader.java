package src;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

  public static ArrayList<Course> loadCourses() {
    ArrayList<Course> courses = new ArrayList<Course>();
    
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i = 0; i < coursesJSON.size(); ++i) {
				JSONObject courseJSON = (JSONObject)coursesJSON.get(i);
				UUID id = UUID.fromString((String)courseJSON.get(COURSE_ID));
        UUID authorId = UUID.fromString((String)courseJSON.get(AUTHOR_ID));
        String rating = (String)courseJSON.get(RATING);
        String title = (String)courseJSON.get(TITLE);
        String difficulty = (String)courseJSON.get(DIFFICULTY);
        String language = (String)courseJSON.get(LANGUAGE);
        String description = (String)courseJSON.get(DESCRIPTION);
        UserList userList = UserList.getInstance();
        User author = userList.getUserByUUID(authorId);
        JSONArray modulesJSON = (JSONArray)courseJSON.get(MODULES);
        ArrayList<Module> modules = loadModules(modulesJSON);
        //courses.add(new Course());
			}
			return courses;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
  }

  private static ArrayList<Module> loadModules(JSONArray modulesJSON) {
    ArrayList<Module> modules = new ArrayList<Module>();
    for (int i = 0; i < modulesJSON.size(); ++i) {
      JSONObject moduleJSON = (JSONObject)modulesJSON.get(i);
      String title = (String)moduleJSON.get(MODULE_TITLE);
    }
    return modules;
  }

  private static ArrayList<Comment> loadComments(JSONArray commentsJSON) {
    ArrayList<Comment> comments = new ArrayList<Comment>();
    return comments;
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
				  users.add(new User(firstName, lastName, email, username, password, id));
        } else if (type.equals("author")) {
          users.add(new Author(firstName, lastName, email, username, password, id));
        }
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
  }
}
