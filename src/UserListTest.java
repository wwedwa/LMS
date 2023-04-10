package src;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserListTest {
	private UserList users = UserList.getInstance();
	private ArrayList<User> userList = users.getUsers();
  private SimpleDateFormat formatter  = new SimpleDateFormat("MM/dd/yyyy");
	
	@BeforeEach
	public void setup() {
		userList.clear();
		try {
      userList.add(new Student("cfain", "Casey", "Fainberg", "fainberg@email.sc.edu", "casey1", formatter.parse("12/24/2003")));
      userList.add(new Student("wwedwa", "William", "Edwards", "wwe1@email.sc.edu", "william1", formatter.parse("03/14/2003")));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
		users.saveUsers();
	}
	
	@AfterEach
	public void tearDown() {
		UserList.getInstance().getUsers().clear();
		users.saveUsers();
	}
	
	
	@Test
	public void testHaveUserValidFirstItem() {
		boolean hasCasey = users.contains("cfain");
		assertTrue(hasCasey);
	}
	
	@Test
	public void testHaveUserValidLastItem() {
		boolean hasWilliam = users.contains("wwedwa");
		assertTrue(hasWilliam);
	}
	
	@Test
	public void testHaveUserInvalid() {
		boolean hasNoah = users.contains("nparker");
		assertFalse(hasNoah);
	}
	
	@Test
	public void testHaveUserEmpty() {
		boolean hasEmpty = users.contains("");
		assertFalse(hasEmpty);
	}
	
	@Test
	public void testHaveUserNull() {
		boolean hasNull = users.contains(null);
		assertFalse(hasNull);
	}

	@Test
	public void emptyUsername() throws ParseException {
		boolean addEmpty = userList.add(new Student("", "Casey", "Fainberg", "fainberg@email.sc.edu", "casey1", formatter.parse("12/24/2003")));
		assertFalse(addEmpty);
	}

	@Test
	public void emptyFirstName() throws ParseException {
		boolean addEmpty = userList.add(new Student("cfain", "", "Fainberg", "fainberg@email.sc.edu", "casey1", formatter.parse("12/24/2003")));
		assertFalse(addEmpty);
	}

	@Test
	public void emptyLastName() throws ParseException {
		boolean addEmpty = userList.add(new Student("cfain", "Casey", "", "fainberg@email.sc.edu", "casey1", formatter.parse("12/24/2003")));
		assertFalse(addEmpty);
	}

	@Test
	public void emptyEmail() throws ParseException {
		boolean addEmpty = userList.add(new Student("cfain", "Casey", "Fainberg", "", "casey1", formatter.parse("12/24/2003")));
		assertFalse(addEmpty);
	}

	@Test
	public void emptyPassword() throws ParseException {
		boolean addEmpty = userList.add(new Student("cfain", "Casey", "Fainberg", "fainberg@email.sc.edu", "", formatter.parse("12/24/2003")));
		assertFalse(addEmpty);
	}
	@Test
	public void nullUsername() throws ParseException {
		boolean addNull = userList.add(new Student(null, "Casey", "Fainberg", "fainberg@email.sc.edu", "casey1", formatter.parse("12/24/2003")));
		assertFalse(addNull);
	}

	@Test
	public void nullFirstName() throws ParseException {
		boolean addNull = userList.add(new Student("cfain", null, "Fainberg", "fainberg@email.sc.edu", "casey1", formatter.parse("12/24/2003")));
		assertFalse(addNull);
	}

	@Test
	public void nullLastName() throws ParseException {
		boolean addNull = userList.add(new Student("cfain", "Casey", null, "fainberg@email.sc.edu", "casey1", formatter.parse("12/24/2003")));
		assertFalse(addNull);
	}

	@Test
	public void nullEmail() throws ParseException {
		boolean addNull = userList.add(new Student("cfain", "Casey", "Fainberg", null, "casey1", formatter.parse("12/24/2003")));
		assertFalse(addNull);
	}

	@Test
	public void nullPassword() throws ParseException {
		boolean addNull = userList.add(new Student("cfain", "Casey", "Fainberg", "fainberg@email.sc.edu", null, formatter.parse("12/24/2003")));
		assertFalse(addNull);
	}
	@Test
	public void nullBirthday() throws ParseException {
		boolean addNull = userList.add(new Student("cfain", "Casey", "Fainberg", "fainberg@email.sc.edu", "casey1", null));
		assertFalse(addNull);
	}
	
	@Test
	public void hasValidUsername() {
		User testUser = userList.get(0);
		boolean validUsername = testUser.getUsername().equals("cfain");
		assertTrue(validUsername);
	}

	@Test
	public void hasValidFirstName() {
		User testUser = userList.get(0);
		boolean validFirstName = testUser.getFirstName().equals("Casey");
		assertTrue(validFirstName);
	}

	@Test
	public void hasValidLastName() {
		User testUser = userList.get(0);
		boolean validLastName = testUser.getLastName().equals("Fainberg");
		assertTrue(validLastName);
	}

	@Test
	public void hasValidEmail() {
		User testUser = userList.get(0);
		boolean validEmail = testUser.getEmail().equals("fainberg@email.sc.edu");
		assertTrue(validEmail);
	}

	@Test
	public void hasValidPassword() {
		User testUser = userList.get(0);
		boolean validPassword = testUser.getPassword().equals("casey1");
		assertTrue(validPassword);
	}
}