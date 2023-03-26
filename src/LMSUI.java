package src;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LMSUI {
  private Scanner scanner;
  private LMS application;

  public static void main(String[] args) {
    LMSUI ui = new LMSUI();
    ui.run();
  }

  private LMSUI() {
    scanner = new Scanner(System.in);
    application = new LMS();
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
      Navigation:\n
        1. View Registered Courses
        2. Search for Courses
        3. View Account Details
        4. Create Course
        5. Logout

        Enter where you want to go: """;
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
        clearScreen();
        System.out.println("Saving progess...");
        sleep(500);
        System.out.println("Goodbye!");
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
    System.out.println("Enter a keyword to search for in the courses");
    String keyword = scanner.nextLine();
    ArrayList<Course> courses = application.findCourses(keyword);
    for (int i = 0; i < courses.size(); i++) {
      System.out.println(i+1 + ". " + courses.get(i));
    }

  }

  private void navigateCourse() {

  }

  private void navigateModule() {

  }

  private void performAssessment(Assessment assessment) {

  }

  private void createAccount() {
    System.out.println("Please enter your email address: ");
    String email = scanner.nextLine();
    System.out.println("Please enter your first name: ");
    String firstName = scanner.nextLine();
    System.out.println("Please enter your last name: ");
    String lastName = scanner.nextLine();
    System.out.println("Please enter a username: ");
    String username = scanner.nextLine();
    System.out.println("Please enter a password: ");
    String password = scanner.nextLine();
    System.out.println("Thank you for creating an account\nLogging in...");
    User user = new User(username, firstName, lastName, email, password);
    ArrayList<User> users = new ArrayList<>();
    users.add(user);
    DataWriter.saveUsers(users);
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
    createModules(modules); 
    Course course = new Course(courseName, application.getUser(), language, description, modules, difficulty);
    displayMainMenu();
  }

  private void createLessons(ArrayList<Lesson> lessons) {
    boolean addingLessons = true;
    while (addingLessons) {
      System.out.println("Please enter the name of the lesson: ");
      String lessonName = scanner.nextLine();
      System.out.println("Enter all text for the module (Enter a line with just (finished) to end):");
      String moduleText = "";
      String tempLine = "";
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
  }

  private void createModules(ArrayList<Module> modules) {
    System.out.println("Enter the module Title: ");
    String moduleTitle = scanner.nextLine();
    boolean addingModules = true;
    int moduleNum = 1;
    while(addingModules) {
    ArrayList<Lesson> lessons = new ArrayList<>();
      System.out.println("Module " + moduleNum);
      boolean addingLessons = true;
      createLessons(lessons);
      ArrayList<Question> questions = new ArrayList<>();
      System.out.println("You will now create a quiz for the lesson that consists of up to 5 questions");
      createQuestions(questions);
      
      Assessment assessment = new Assessment(questions);
      System.out.println("Would you like to add another Module? (y/n)");
      if (scanner.nextLine().equals("n")) {
        addingModules = false;
      }
      else {
        moduleNum += 1;
      }
      Module module = new Module(moduleTitle, assessment, lessons);
      modules.add(module);
    }
  }

  private void createQuestions(ArrayList<Question> questions) {
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

  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private void sleep(int milliseconds) {
    try {
      TimeUnit.MILLISECONDS.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}