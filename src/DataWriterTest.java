package src;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataWriterTest {
    @BeforeClass
	public void oneTimeSetup() {
		
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		
	}
	
	@BeforeEach
	public void setup() {
		//runs before each test
	}
	
	@AfterEach
	public void tearDown() {
		//runs after each test
	}
	
    @Test
    public void testGetJSONQuestion() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("a1");
        answers.add("a2");
        answers.add("a3");
        Question question = new Question("I'm a question", answers, 2);
        JSONObject result = DataWriter.getQuestionJSON(question);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("question", "I'm a question");
        expectedResult.put("correctAnswer", 2);
        expectedResult.put("answerChoices", answers);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testQuestionHasNullQuestion() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("a1");
        answers.add("a2");
        answers.add("a3");
        Question question = new Question(null, answers, 2);
        JSONObject result = DataWriter.getQuestionJSON(question);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("question", null);
        expectedResult.put("correctAnswer", 2);
        expectedResult.put("answerChoices", answers);
        assertEquals(result, expectedResult);
    }
    
    @Test
    public void testQuestionHasNullAnswer() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(null);
        answers.add("a2");
        answers.add("a3");
        Question question = new Question("I'm a question", answers, 2);
        JSONObject result = DataWriter.getQuestionJSON(question);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("question", "I'm a question");
        expectedResult.put("correctAnswer", 2);
        expectedResult.put("answerChoices", answers);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testQuestionHasNullAsAnswerChoicesArray() {
        Question question = new Question("I'm a question", null, 2);
        JSONObject result = DataWriter.getQuestionJSON(question);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("question", "I'm a question");
        expectedResult.put("correctAnswer", 2);
        expectedResult.put("answerChoices", null);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testNullQuestion() {
        Question question = null;
        JSONObject result = DataWriter.getQuestionJSON(question);
        JSONObject expectedResult = null;
        assertEquals(result, expectedResult);
    }

    @Test
    public void testGetQuizJSON() {
        ArrayList<String> answers1 = new ArrayList<>();
        answers1.add("a1");
        answers1.add("a2");
        answers1.add("a3");
        Question question1 = new Question("I'm a question", answers1, 2);
        ArrayList<String> answers2 = new ArrayList<>();
        answers2.add("1");
        answers2.add("2");
        answers2.add("3");
        Question question2 = new Question("I'm a question", answers2, 1);
        Assessment quiz = new Assessment();
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        JSONArray result = DataWriter.getQuizJSON(quiz);
        JSONArray expectedResult = new JSONArray();
        JSONObject jsonQuestion1 = new JSONObject();
        JSONObject jsonQuestion2 = new JSONObject();
        jsonQuestion1.put("question", "I'm a question");
        jsonQuestion1.put("correctAnswer", 2);
        jsonQuestion1.put("answerChoices", answers1);
        jsonQuestion2.put("question", "I'm a question");
        jsonQuestion2.put("correctAnswer", 1);
        jsonQuestion2.put("answerChoices", answers2);
        expectedResult.add(jsonQuestion1);
        expectedResult.add(jsonQuestion2);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testNullQuiz() {
        Assessment quiz = null;
        JSONArray result = DataWriter.getQuizJSON(quiz);
        JSONArray expectedResult = null;
        assertEquals(result, expectedResult);
    }

    @Test
    public void testGetUserJson() {
        try {
            Date birthday = new SimpleDateFormat("MM/dd/yyyy").parse("04/9/2023");
            User user = new Student("bob", "bob", "bob","bob@email.com", "bob", birthday);
            JSONObject result = DataWriter.getUserJSON(user);
            JSONObject expectedResult = new JSONObject();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
            expectedResult.put("id", user.getId().toString());
            expectedResult.put("username", "bob");
            expectedResult.put("password", "bob");
            expectedResult.put("firstName", "bob");
            expectedResult.put("lastName", "bob");
            expectedResult.put("email", "bob@email.com");
            expectedResult.put("type", "student");
            expectedResult.put("dob", "04/09/2023");
            assertEquals(result, expectedResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNullUser() {
        User user = null;
        JSONObject result = DataWriter.getUserJSON(user);
        JSONObject expectedResult = null;
        assertEquals(result, expectedResult);
    }

    @Test
    public void testGetChoicesJSON() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("1");
        answers.add("2");
        answers.add("3");
        JSONArray result = DataWriter.getChoicesJSON(answers);
        JSONArray expectedResult = new JSONArray();
        expectedResult.add("1");
        expectedResult.add("2");
        expectedResult.add("3");
        assertEquals(result, expectedResult);
    }

    @Test
    public void testNullChoice() {
        ArrayList<String> answers = new ArrayList<>();
        answers.add("1");
        answers.add(null);
        answers.add("3");
        JSONArray result = DataWriter.getChoicesJSON(answers);
        JSONArray expectedResult = new JSONArray();
        expectedResult.add("1");
        expectedResult.add(null);
        expectedResult.add("3");
        assertEquals(result, expectedResult);
    }

    @Test
    public void testNullAnswers() {
        ArrayList<String> answers = null;
        JSONArray result = DataWriter.getChoicesJSON(answers);
        JSONArray expectedResult = new JSONArray();
        expectedResult.add(null);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testGetLessonJSON() {
        Lesson lesson = new Lesson("title", "content");
        JSONObject result = DataWriter.getLessonJSON(lesson);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("title", "title");
        expectedResult.put("content", "content");
        assertEquals(result, expectedResult);
    }
    
    @Test
    public void testNullTitle() {
        Lesson lesson = new Lesson(null, "content");
        JSONObject result = DataWriter.getLessonJSON(lesson);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("title", null);
        expectedResult.put("content", "content");
        assertEquals(result, expectedResult);
    } 

    @Test
    public void testNullContent() {
        Lesson lesson = new Lesson("title", null);
        JSONObject result = DataWriter.getLessonJSON(lesson);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("title", "title");
        expectedResult.put("content", null);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testNullLesson() {
        Lesson lesson = null;
        JSONObject result = DataWriter.getLessonJSON(lesson);
        JSONObject expectedResult = null;
        assertEquals(result, expectedResult);
    }

    @Test
    public void testGetLessonsJSON() {
        Lesson lesson1 = new Lesson("title", "content");
        Lesson lesson2 = new Lesson("title2", "content2");
        ArrayList<Lesson> lessons = new ArrayList();
        lessons.add(lesson1);
        lessons.add(lesson2);
        JSONArray result = DataWriter.getLessonsJSON(lessons);
        JSONArray expectedResult = new JSONArray();
        JSONObject object1 = new JSONObject();
        JSONObject object2 = new JSONObject();
        object1.put("title", "title");
        object1.put("content", "content");
        object2.put("title", "title2");
        object2.put("content", "content2");
        expectedResult.add(object1);
        expectedResult.add(object2);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testGetLessonsJSONNullLesson() {
        Lesson lesson1 = new Lesson("title", "content");
        Lesson lesson2 = null;
        ArrayList<Lesson> lessons = new ArrayList();
        lessons.add(lesson1);
        lessons.add(lesson2);
        JSONArray result = DataWriter.getLessonsJSON(lessons);
        JSONArray expectedResult = new JSONArray();
        JSONObject object1 = new JSONObject();
        object1.put("title", "title");
        object1.put("content", "content");
        expectedResult.add(object1);
        expectedResult.add(null);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testGetModuleJSON() {
        ArrayList<String> answers1 = new ArrayList<>();
        answers1.add("a1");
        answers1.add("a2");
        answers1.add("a3");
        Question question1 = new Question("I'm a question", answers1, 2);
        ArrayList<String> answers2 = new ArrayList<>();
        answers2.add("1");
        answers2.add("2");
        answers2.add("3");
        Question question2 = new Question("I'm a question", answers2, 1);
        Assessment quiz = new Assessment();
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        Lesson lesson = new Lesson("title", "content");
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);
        Module module = new Module("title", quiz, lessons);
        JSONObject result = DataWriter.getModuleJSON(module);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("title", "title");
        JSONArray comments = new JSONArray();
        expectedResult.put("comments", comments);
        JSONArray jsonLessons = new JSONArray();
        JSONObject jsonLesson = new JSONObject();
        jsonLesson.put("title", "title");
        jsonLesson.put("content", "content");
        jsonLessons.add(jsonLesson);
        expectedResult.put("lessons", jsonLessons);
        JSONArray jsonQuestions = new JSONArray();
        JSONObject jsonQuestion1 = new JSONObject();
        jsonQuestion1.put("question", "I'm a question");
        jsonQuestion1.put("correctAnswer", 2);
        jsonQuestion1.put("answerChoices", answers1);
        jsonQuestions.add(jsonQuestion1);
        JSONObject jsonQuestion2 = new JSONObject();
        jsonQuestion2.put("question", "I'm a question");
        jsonQuestion2.put("correctAnswer", 1);
        jsonQuestion2.put("answerChoices", answers2);
        jsonQuestions.add(jsonQuestion2);
        expectedResult.put("quiz", jsonQuestions);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testGetModuleJSONNullModule() {
        Module module = null;
        JSONObject result = DataWriter.getModuleJSON(module);
        JSONObject expectedResult = null;
        assertEquals(result, expectedResult);
    }
    
    @Test
    public void testGetModuleJSONNullTitle() {
        ArrayList<String> answers1 = new ArrayList<>();
        answers1.add("a1");
        answers1.add("a2");
        answers1.add("a3");
        Question question1 = new Question("I'm a question", answers1, 2);
        ArrayList<String> answers2 = new ArrayList<>();
        answers2.add("1");
        answers2.add("2");
        answers2.add("3");
        Question question2 = new Question("I'm a question", answers2, 1);
        Assessment quiz = new Assessment();
        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        Lesson lesson = new Lesson("title", "content");
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson);
        Module module = new Module(null, quiz, lessons);
        JSONObject result = DataWriter.getModuleJSON(module);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("title", null);
        JSONArray comments = new JSONArray();
        expectedResult.put("comments", comments);
        JSONArray jsonLessons = new JSONArray();
        JSONObject jsonLesson = new JSONObject();
        jsonLesson.put("title", "title");
        jsonLesson.put("content", "content");
        jsonLessons.add(jsonLesson);
        expectedResult.put("lessons", jsonLessons);
        JSONArray jsonQuestions = new JSONArray();
        JSONObject jsonQuestion1 = new JSONObject();
        jsonQuestion1.put("question", "I'm a question");
        jsonQuestion1.put("correctAnswer", 2);
        jsonQuestion1.put("answerChoices", answers1);
        jsonQuestions.add(jsonQuestion1);
        JSONObject jsonQuestion2 = new JSONObject();
        jsonQuestion2.put("question", "I'm a question");
        jsonQuestion2.put("correctAnswer", 1);
        jsonQuestion2.put("answerChoices", answers2);
        jsonQuestions.add(jsonQuestion2);
        expectedResult.put("quiz", jsonQuestions);
        assertEquals(result, expectedResult);
    }

    @Test
    public void testGetReviewJSON() {
        try {
            Date birthday = new SimpleDateFormat("MM/dd/yyyy").parse("04/9/2023");
            User user = new Student("bob", "bob", "bob","bob@email.com", "bob", birthday);
            Review review = new Review(user, 4, "good");
            JSONObject result = DataWriter.getReviewJSON(review);
            JSONObject expectedResult = new JSONObject();
            expectedResult.put("userid", user.getId().toString());
            expectedResult.put("rating", 4);
            expectedResult.put("review", "good");
            assertEquals(result, expectedResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetReviewJSONNullUser() {
        Review review = new Review(null, 4, "good");
        JSONObject result = DataWriter.getReviewJSON(review);
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("userid", null);
        expectedResult.put("rating", 4);
        expectedResult.put("review", "good");
        assertEquals(result, expectedResult);
    }

    @Test
    public void testGetReviewJSONNullReview() {
        Review review = null;
        JSONObject result = DataWriter.getReviewJSON(review);
        JSONObject expectedResult = null;
        assertEquals(result, expectedResult);
    }
}
