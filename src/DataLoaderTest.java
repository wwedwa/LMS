package src;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataLoaderTest {
  private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private ArrayList<User> users = new ArrayList<User>();
  private ArrayList<Course> courses = new ArrayList<Course>();
	
	@BeforeEach
	public void setup() {
		users.clear();
    try {
		users.add(new Student("wwedwa", "William", "Edwards", "wwe1@email.sc.edu", "password!", formatter.parse("03/14/2003")));
    users.add(new Author("cfainberg", "Casey", "Fainberg", "fainberg@email.sc.edu", "password123", formatter.parse("12/24/2003")));
    } catch (ParseException e) {
      e.getStackTrace();
    }
    DataWriter.saveUsers(users);
    Lesson lesson1 = new Lesson("Lesson 1", "Lesson 1 content");
    Lesson lesson2 = new Lesson("", "");
    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    lessons.add(lesson1);
    lessons.add(lesson2);
    Assessment assessment = new Assessment();
    ArrayList<String> answers = new ArrayList<String>();
    answers.add("answer 1");
    answers.add("answer 2");
    assessment.addQuestion(new Question("question 1", answers, 1));
    assessment.addQuestion(new Question("", new ArrayList<String>(), 0));
    Module module1 = new Module("Module 1", assessment, lessons);
    ArrayList<Module> modules = new ArrayList<Module>();
    modules.add(module1);
    courses.add(new Course("Course 1", users.get(1), Language.JAVA, "description of course", modules, Difficulty.EASY));
    courses.add(new Course("Course 2", users.get(1), Language.JAVA, "description for course 2", modules, Difficulty.EASY));
    DataWriter.saveCourses(courses);
	}
	
	@AfterEach
	public void tearDown() {
    users.clear();
    courses.clear();
		DataWriter.saveUsers(users);
    DataWriter.saveCourses(courses);
	}
	
	@Test
	void testLoadUsersSizeTwo() {
		assertEquals(2, users.size());
	}

	@Test
	void testLoadUsersSizeZero() {
		DataWriter.saveUsers(new ArrayList<User>());
		assertEquals(0, DataLoader.loadUsers().size());
	}
	
	@Test
	void testLoadStudentUserName() {
		assertEquals("wwedwa", DataLoader.loadUsers().get(0).getUsername());
	}

  @Test
  void testLoadStudentFirstName() {
		assertEquals("William", DataLoader.loadUsers().get(0).getFirstName());
  }

  @Test
  void testLoadStudentLastName() {
		assertEquals("Edwards", DataLoader.loadUsers().get(0).getLastName());
  }

  @Test
  void testLoadStudentPassword() {
		assertEquals("password!", DataLoader.loadUsers().get(0).getPassword());
  }

  @Test
  void testLoadStudentEmail() {
		assertEquals("wwe1@email.sc.edu", DataLoader.loadUsers().get(0).getEmail());
  }

  @Test
  void testLoadStudentBirthday() {
		assertEquals("03/14/2003", formatter.format(users.get(0).getBirthday()));
  }

  @Test
	void testLoadAuthorUserName() {
		assertEquals("cfainberg", DataLoader.loadUsers().get(1).getUsername());
	}

  @Test
  void testLoadAuthorFirstName() {
		assertEquals("Casey", DataLoader.loadUsers().get(1).getFirstName());
  }

  @Test
  void testLoadAuthorLastName() {
		assertEquals("Fainberg", DataLoader.loadUsers().get(1).getLastName());
  }

  @Test
  void testLoadAuthorPassword() {
		assertEquals("password123", DataLoader.loadUsers().get(1).getPassword());
  }

  @Test
  void testLoadAuthorEmail() {
		assertEquals("fainberg@email.sc.edu", DataLoader.loadUsers().get(1).getEmail());
  }

  @Test
  void testLoadAuthorBirthday() {
		assertEquals("12/24/2003", formatter.format(users.get(1).getBirthday()));
  }

  @Test
	void testLoadEmptyUserUserName() {
		users.clear();
    try {
      users.add(new Student("", "", "", "", "", formatter.parse("03/14/2003")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DataWriter.saveUsers(users);
		assertEquals("", DataLoader.loadUsers().get(0).getUsername());
	}

  @Test
  void testLoadEmptyUserFirstName() {
    users.clear();
    try {
      users.add(new Student("", "", "", "", "", formatter.parse("03/14/2003")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DataWriter.saveUsers(users);
		assertEquals("", DataLoader.loadUsers().get(0).getFirstName());
  }

  @Test
  void testLoadEmptyUserLastName() {
    users.clear();
    try {
      users.add(new Student("", "", "", "", "", formatter.parse("03/14/2003")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DataWriter.saveUsers(users);
		assertEquals("", DataLoader.loadUsers().get(0).getLastName());
  }

  @Test
  void testLoadEmptyUserPassword() {
    users.clear();
    try {
      users.add(new Student("", "", "", "", "", formatter.parse("03/14/2003")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DataWriter.saveUsers(users);
		assertEquals("", DataLoader.loadUsers().get(0).getPassword());
  }

  @Test
  void testLoadEmptyUserEmail() {
    users.clear();
    try {
      users.add(new Student("", "", "", "", "", formatter.parse("03/14/2003")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DataWriter.saveUsers(users);
		assertEquals("", DataLoader.loadUsers().get(0).getEmail());
  }

  @Test
  void testLoadEmptyUserBirthday() {
    users.clear();
    try {
      users.add(new Student("", "", "", "", "", formatter.parse("")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DataWriter.saveUsers(users);
		assertEquals("", formatter.format(DataLoader.loadUsers().get(0).getBirthday()));
  }

  @Test
	void testLoadNullUserUserName() {
		users.clear();
    try {
      users.add(new Student(null, "firstNameFiller", "lastNameFiller", "emailFiller", "passwordFiller", formatter.parse("03/14/2003")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DataWriter.saveUsers(users);
		assertEquals(null, DataLoader.loadUsers().get(0).getUsername());
	}

  @Test
  void testLoadNullUserFirstName() {
    users.clear();
    try {
      users.add(new Student("userNameFiller", null, "lastNameFiller", "emailFiller", "passwordFiller", formatter.parse("03/14/2003")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DataWriter.saveUsers(users);
		assertEquals(null, DataLoader.loadUsers().get(0).getFirstName());
  }

  @Test
  void testLoadNullUserLastName() {
    users.clear();
    try {
      users.add(new Student("userNameFiller", "firstNameFiller", null, "emailFiller", "passwordFiller", formatter.parse("03/14/2003")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DataWriter.saveUsers(users);
		assertEquals(null, DataLoader.loadUsers().get(0).getLastName());
  }

  @Test
  void testLoadNullUserPassword() {
    users.clear();
    try {
      users.add(new Student("userNameFiller", "firstNameFiller", "lastNameFiller", "emailFiller", null, formatter.parse("03/14/2003")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DataWriter.saveUsers(users);
		assertEquals(null, DataLoader.loadUsers().get(0).getPassword());
  }

  @Test
  void testLoadNullUserEmail() {
    users.clear();
    try {
      users.add(new Student("userNameFiller", "firstNameFiller", "lastNameFiller", null, "passwordFiller", formatter.parse("03/14/2003")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    DataWriter.saveUsers(users);
		assertEquals(null, DataLoader.loadUsers().get(0).getEmail());
  }

  @Test
  void testLoadNullUserBirthday() {
    users.clear();
    users.add(new Student("userNameFiller", "firstNameFiller", "lastNameFiller", "emailFiller", "passwordFiller", null));
    DataWriter.saveUsers(users);
		assertEquals(null, DataLoader.loadUsers().get(0).getBirthday());
  }

	@Test
	void testLoadCoursesSizeTwo() {
		assertEquals(2, DataLoader.loadCourses().size());
	}

	@Test
	void testLoadCoursesSizeZero() {
		DataWriter.saveCourses(new ArrayList<Course>());
		assertEquals(0, DataLoader.loadCourses().size());
	}

  @Test
  void testLoadFirstCourseTitle() {
    assertEquals("Course 1", DataLoader.loadCourses().get(0).getTitle());
  }

  @Test
  void testLoadFirstCourseAuthor() {
    assertEquals("cfainberg", DataLoader.loadCourses().get(0).getAuthor().getUsername());
  }

  @Test
  void testLoadFirstCourseLanguage() {
    assertEquals(Language.JAVA, DataLoader.loadCourses().get(0).getLanguage());
  }

  @Test
  void testLoadFirstCourseDescription() {
    assertEquals("description of course", DataLoader.loadCourses().get(0).getDescription());
  }

  @Test
  void testLoadFirstCourseModulesLength() {
    assertEquals(1, DataLoader.loadCourses().get(0).getModules().size());
  }

  @Test
  void testLoadFirstCourseLessonsLength() {
    assertEquals(2, DataLoader.loadCourses().get(0).getModules().get(0).getLessons().size());
  }

  @Test
  void testLoadFirstCourseLessonTitle() {
    assertEquals("Lesson 1", DataLoader.loadCourses().get(0).getModules().get(0).getLessons().get(0).getTitle());
  }

  @Test
  void testLoadFirstCourseLessonContent() {
    assertEquals("Lesson 1 content", DataLoader.loadCourses().get(0).getModules().get(0).getLessons().get(0).getContent());
  }

  @Test
  void testLoadFirstCourseEmptyLessonTitle() {
    assertEquals("", DataLoader.loadCourses().get(0).getModules().get(0).getLessons().get(1).getTitle());
  }

  @Test
  void testLoadFirstCourseEmptyLessonContent() {
    assertEquals("", DataLoader.loadCourses().get(0).getModules().get(0).getLessons().get(1).getContent());
  }

  @Test
  void testLoadFirstCourseAssessmentLength() {
    assertEquals(2, DataLoader.loadCourses().get(0).getModules().get(0).getAssessment().getQuestions().size());
  }

  @Test
  void testLoadFirstCourseFirstQuestion() {
    assertEquals("question 1", DataLoader.loadCourses().get(0).getModules().get(0).getAssessment().getQuestions().get(0).getQuestion());
  }

  @Test
  void testLoadFirstCourseFirstQuestionAnswers() {
    ArrayList<String> answers = new ArrayList<String>();
    answers.add("answer 1");
    answers.add("answer 2");
    assertEquals(answers, DataLoader.loadCourses().get(0).getModules().get(0).getAssessment().getQuestions().get(0).getAnswerChoices());
  }

  @Test
  void testLoadFirstCourseFirstQuestionAnswer() {
    assertEquals(1, DataLoader.loadCourses().get(0).getModules().get(0).getAssessment().getQuestions().get(0).getCorrectAnswer());
  }

  @Test
  void testLoadFirstCourseEmptyQuestion() {
    assertEquals("", DataLoader.loadCourses().get(0).getModules().get(0).getAssessment().getQuestions().get(1).getQuestion());
  }

  @Test
  void testLoadFirstCourseEmptyQuestionAnswers() {
    ArrayList<String> answers = new ArrayList<String>();
    assertEquals(answers, DataLoader.loadCourses().get(0).getModules().get(0).getAssessment().getQuestions().get(1).getAnswerChoices());
  }

  @Test
  void testLoadFirstCourseEmptyQuestionAnswer() {
    assertEquals(0, DataLoader.loadCourses().get(0).getModules().get(0).getAssessment().getQuestions().get(1).getCorrectAnswer());
  }

  @Test
  void testLoadFirstCourseDifficulty() {
    assertEquals(Difficulty.EASY, DataLoader.loadCourses().get(0).getDifficulty());
  }

  @Test
  void testLoadEmptyCourseTitle() {
    courses.clear();
    courses.add(new Course("", users.get(1), Language.PYTHON, "", new ArrayList<Module>(), Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals("", DataLoader.loadCourses().get(0).getTitle());
  }

  @Test
  void testLoadEmptyCourseDescription() {
    courses.clear();
    courses.add(new Course("", users.get(1), Language.PYTHON, "", new ArrayList<Module>(), Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals("", DataLoader.loadCourses().get(0).getDescription());
  }

  @Test
  void testLoadEmptyCourseModulesLength() {
    courses.clear();
    courses.add(new Course("", users.get(1), Language.PYTHON, "", new ArrayList<Module>(), Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(0, DataLoader.loadCourses().get(0).getModules().size());
  }

  @Test
  void testLoadNullCourseTitle() {
    courses.clear();
    courses.add(new Course(null, users.get(1), Language.PYTHON, "description filler", new ArrayList<Module>(), Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getTitle());
  }

  @Test
  void testLoadNullCourseDescription() {
    courses.clear();
    courses.add(new Course("Title filler", users.get(1), Language.PYTHON, null, new ArrayList<Module>(), Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getDescription());
  }

  @Test
  void testLoadNullCourseModules() {
    courses.clear();
    courses.add(new Course("Title filler", users.get(1), Language.PYTHON, "description filler", null, Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getModules());
  }

  @Test
  void testLoadNullCourseLanguage() {
    courses.clear();
    courses.add(new Course("Title filler", users.get(1), null, "description filler", new ArrayList<Module>(), Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getLanguage());
  }

  @Test
  void testLoadNullCourseDifficulty() {
    courses.clear();
    courses.add(new Course("Title filler", users.get(1), Language.PYTHON, "description filler", new ArrayList<Module>(), null));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getDifficulty());
  }

  @Test
  void testLoadNullCourseAuthor() {
    courses.clear();
    courses.add(new Course("Title filler", null, Language.PYTHON, "description filler", new ArrayList<Module>(), Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getAuthor());
  }

  @Test
  void testLoadNullAssessment() {
    courses.clear();
    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    Assessment assessment = null;
    Module module1 = new Module("Module 1", assessment, lessons);
    ArrayList<Module> modules = new ArrayList<Module>();
    modules.add(module1);
    courses.add(new Course("title filler", users.get(1), Language.JAVA, "description filler", modules, Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getModules().get(0).getAssessment());
  }

  @Test
  void testLoadNullQuestion() {
    courses.clear();
    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    Assessment assessment = new Assessment();
    assessment.addQuestion(null);
    Module module1 = new Module("Module 1", assessment, lessons);
    ArrayList<Module> modules = new ArrayList<Module>();
    modules.add(module1);
    courses.add(new Course("title filler", users.get(1), Language.JAVA, "description filler", modules, Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getModules().get(0).getAssessment().getQuestions().get(0));
  }

  @Test
  void testLoadNullQuestionQuestion() {
    courses.clear();
    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    Assessment assessment = new Assessment();
    assessment.addQuestion(new Question(null, new ArrayList<String>(), 0));
    Module module1 = new Module("Module 1", assessment, lessons);
    ArrayList<Module> modules = new ArrayList<Module>();
    modules.add(module1);
    courses.add(new Course("title filler", users.get(1), Language.JAVA, "description filler", modules, Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getModules().get(0).getAssessment().getQuestions().get(0).getQuestion());
  }

  @Test
  void testLoadNullQuestionAnswers() {
    courses.clear();
    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    Assessment assessment = new Assessment();
    assessment.addQuestion(new Question("filler question", null, 0));
    Module module1 = new Module("Module 1", assessment, lessons);
    ArrayList<Module> modules = new ArrayList<Module>();
    modules.add(module1);
    courses.add(new Course("title filler", users.get(1), Language.JAVA, "description filler", modules, Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getModules().get(0).getAssessment().getQuestions().get(0).getAnswerChoices());
  }

  @Test
  void testLoadNullLessonTitle() {
    courses.clear();
    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    lessons.add(new Lesson(null, "filler content"));
    Assessment assessment = new Assessment();
    Module module1 = new Module("Module 1", assessment, lessons);
    ArrayList<Module> modules = new ArrayList<Module>();
    modules.add(module1);
    courses.add(new Course("title filler", users.get(1), Language.JAVA, "description filler", modules, Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getModules().get(0).getLessons().get(0).getTitle());
  }

  @Test
  void testLoadNullLessonContent() {
    courses.clear();
    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    lessons.add(new Lesson("filler title", null));
    Assessment assessment = new Assessment();
    Module module1 = new Module("Module 1", assessment, lessons);
    ArrayList<Module> modules = new ArrayList<Module>();
    modules.add(module1);
    courses.add(new Course("title filler", users.get(1), Language.JAVA, "description filler", modules, Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getModules().get(0).getLessons().get(0).getContent());
  }

  @Test
  void testLoadNullLesson() {
    courses.clear();
    ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    lessons.add(null);
    Assessment assessment = new Assessment();
    Module module1 = new Module("Module 1", assessment, lessons);
    ArrayList<Module> modules = new ArrayList<Module>();
    modules.add(module1);
    courses.add(new Course("title filler", users.get(1), Language.JAVA, "description filler", modules, Difficulty.EASY));
    DataWriter.saveCourses(courses);
    assertEquals(null, DataLoader.loadCourses().get(0).getModules().get(0).getLessons().get(0));
  }
}
