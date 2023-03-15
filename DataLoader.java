package LMS;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

  public static ArrayList<Course> LoadCourses() {
    return null;
  }

  public static ArrayList<User> LoadUsers() {
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
				  users.add(new User(firstName, lastName, email, username, password, type, id));
        } else if (type.equals("author")) {
          users.add(new Author(firstName, lastName, email, username, password, type, id));
        }
			}
			
			return users;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
  }
}
