package LMS;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
  
  public static void saveCourses(ArrayList<Course> courses) {
    
  }

	public static void saveUsers() {
		UserList users = UserList.getInstance();
		ArrayList<User> userList = users.getUsers();
		JSONArray jsonUsers = new JSONArray();
		
		for (int i = 0; i < userList.size(); ++i) {
			jsonUsers.add(getUserJSON(userList.get(i)));
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
