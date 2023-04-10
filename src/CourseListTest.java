package src;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseListTest {
    private UserList userList = UserList.getInstance();
    private User user = userList.getUser("nparker");
    private Language language;
    private Difficulty difficulty;
    private static CourseList courseList = CourseList.getInstance();

    @BeforeEach
    public void setup() {
        Assessment assessment = new Assessment();

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        lessons.add(new Lesson("test", "test"));

        ArrayList<Module> modules = new ArrayList<Module>();
        modules.add(new Module ("test", assessment, lessons));

        courseList.addCourse("test_course2", user, language.C, "test", modules, difficulty.EASY);
    }

    @Test
    public void testAddCourse() {
        Assessment assessment = new Assessment();

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        lessons.add(new Lesson("test", "test"));

        ArrayList<Module> modules = new ArrayList<Module>();
        modules.add(new Module ("test", assessment, lessons));

        boolean courseCreated = courseList.addCourse("test_course1", user, language.C, "test", modules, difficulty.EASY);
        assertEquals(true, courseCreated);
    }

    @Test
    public void testAddCourseNullModules() {
        Assessment assessment = new Assessment();

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        lessons.add(new Lesson("test", "test"));

        ArrayList<Module> modules = new ArrayList<Module>();
        modules.add(new Module ("test", assessment, lessons));

        boolean courseCreated = courseList.addCourse("test_course1", user, language.C, "test", null, difficulty.EASY);
        assertEquals(false, courseCreated);
    }

    @Test
    public void testAddCourseNullLanguage() {
        Assessment assessment = new Assessment();

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        lessons.add(new Lesson("test", "test"));

        ArrayList<Module> modules = new ArrayList<Module>();
        modules.add(new Module ("test", assessment, lessons));

        boolean courseCreated = courseList.addCourse("test_course1", user, null, "test", modules, difficulty.EASY);
        assertEquals(false, courseCreated);
    }

    @Test
    public void testAddCourseNullDificulty() {
        Assessment assessment = new Assessment();

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        lessons.add(new Lesson("test", "test"));

        ArrayList<Module> modules = new ArrayList<Module>();
        modules.add(new Module ("test", assessment, lessons));

        boolean courseCreated = courseList.addCourse("test_course1", user, language.C, "test", modules, null);
        assertEquals(false, courseCreated);
    }

    @Test
    public void testContains() {
        assertEquals(true, courseList.contains("test_course2"));
    }

    @Test
    public void testContainsNull() {
        assertEquals(false, courseList.contains(null));
    }
}
