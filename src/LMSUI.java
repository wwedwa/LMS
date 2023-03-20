package src;

import java.util.ArrayList;
import java.util.Scanner;

public class LMSUI {
  private static Scanner scanner;
  private static LMS application;

  public static void main(String[] args) {
    LMSUI ui = new LMSUI();
    application = new LMS();
    scanner = new Scanner(System.in);
    ui.run();
  }

  private void run() {
    System.out.print("Do you already have an account? (y/n): ");
    if (scanner.nextLine().toLowerCase().equals("y")) {
      displayLogin();
    }
    else {
      createAccount();
    }
    displayMainMenu();

  }

  private void displayLogin() {
    System.out.print("\nEnter your username: ");
    String username = scanner.nextLine();
    System.out.print("\nEnter your password: ");
    String password = scanner.nextLine();
    boolean success = application.login(username, password);
    if (success) {
      System.out.println("\nLogging in...");
      return;
    }
    else {
      System.out.println("\nInvalid Username or Password");
      displayLogin();
    }

  }

  private void displayMainMenu() {
    System.out.println("Welcome " + application.getUser().getUsername());
    String welcomeScreen = """
      Navigation:
        1. View Registered Courses
        2. Search for Courses
        3. View Account Details
        4. Create Course
        5. Logout

        Enter where you want to go:  """;
    System.out.print(welcomeScreen);
    int choice = scanner.nextInt();
    scanner.nextLine();
    switch(choice) {
      case 1: 
        displayRegisteredCourses();
        break;
      case 2:
        searchCourses();
        break;
      case 3: 
        displayUser();
        break;
      case 4:
        createCourse();
        break;
      case 5:
        application.logout();
        break;

    }
  }

  private void displayUser() {

  }

  private void displayLesson(Lesson lesson) {

  }

  private void displayCourseDescription(Course course) {

  }

  private void displayFinishedCourses() {

  }

  private void displayRegisteredCourses() {

  }

  private void displayAllCourses() {

  }

  private void searchCourses() {

  }

  private void navigateCourse() {

  }

  private void navigateModule() {

  }

  private void performAssessment(Assessment assessment) {

  }

  private void createAccount() {
    System.out.print("\n");
  }

  private void createCourse() {
    ArrayList<Module> modules = new ArrayList<>();
    System.out.println("\nEnter the name of the course: ");
    String courseName = scanner.nextLine();
    System.out.println("\nEnter the course Description (Enter a blank line to end): ");
    String description = "";
    String tempLine;
    do {
      tempLine = scanner.nextLine();
      description += tempLine;
    } while(tempLine != "");
    System.out.println("Enter the course language: ");
    Language language = null;
    try {
      language = Language.valueOf(scanner.nextLine());
    } catch(Exception e) {
      while(language == null) {
        try {
        System.out.println("Please enter a valid language: ");
        language = Language.valueOf(scanner.nextLine());
        } catch (Exception err) {}
      }
    }
    System.out.println("\nEnter the Course Difficulty: ");
    Difficulty difficulty = null;
    try {
      difficulty = Difficulty.valueOf(scanner.nextLine());
    } catch(Exception e) {
      while(difficulty == null) {
        try {
        System.out.println("Please enter a valid difficulty (EASY, MEDIUM, HARD): ");
        difficulty = Difficulty.valueOf(scanner.nextLine());
        } catch (Exception err) {}
      }
    }

    boolean addingModules = true;
    int moduleNum = 1;
    while(addingModules) {
      ArrayList<Lesson> lessons = new ArrayList<>();
      System.out.println("Module " + moduleNum);
      boolean addingLessons = true;
      while (addingLessons) {
        System.out.println("Please enter the name of the lesson: ");
        String lessonName = scanner.nextLine();
        System.out.println("Enter all text for the module (Enter a line with just (finished) to end):");
        String moduleText = "";
        tempLine = "";
        do {
          moduleText += tempLine;
          tempLine = scanner.nextLine();
        } while(!tempLine.equals("finished"));
        Lesson lesson = new Lesson(lessonName, moduleText);
        lessons.add(lesson);
        System.out.println("Would you like to add another lesson? (y/n)");
        if (scanner.nextLine().equals("n")) {
          addingLessons = false;
        }
      }
      ArrayList<Question> questions = new ArrayList<>();
      System.out.println("You will now create a quiz for the lesson that consists of up to 5 questions");
      boolean addingQuestions = true;
      while (addingQuestions) {
        System.out.print("Enter the question: ");
        String question = scanner.nextLine();
        System.out.print("Enter the number of answer choices (up to 4): ");
        int choices = scanner.nextInt();
        scanner.nextLine();
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < choices; i++) {
          System.out.print("Enter answer choice " + (i + 1) + ": ");
          answers.add(scanner.nextLine());
        }
        System.out.println("Enter the number of the correct answer: ");
        int correctAnswer = scanner.nextInt();
        scanner.nextLine();
        Question quest = new Question(question, answers, correctAnswer);
        questions.add(quest);
        if (questions.size() < 5) {
          System.out.println("Would you like to keep adding questions? (y/n) ");
          if (scanner.nextLine().equals("n")) {
            addingQuestions = false;
          }
        }
        else {
          System.out.println("5 Questions reached");
          addingQuestions = false;
        }
      }
      Assessment assessment = new Assessment(questions);
      System.out.println("Would you like to add another Module? (y/n)");
      if (scanner.nextLine().equals("n")) {
        addingModules = false;
      }
      else {
        moduleNum += 1;
      }
      Module module = new Module(courseName, assessment, lessons);
      modules.add(module);
    }
    Course course = new Course(courseName, application.getUser(), language, description, modules, difficulty);
    displayMainMenu();
  }

  private void displayComments(ArrayList<Comment> comments) {

  }

  private void displayReviews(ArrayList<Review> reviews) {

  }

  private void createReply(Comment comment) {

  }

  private void createModuleComment() {

  }

  private void createCourseComment() {

  }

  private void createReview() {

  }
}