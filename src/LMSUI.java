package src;

import java.util.ArrayList;
import java.util.HashMap;
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
    clearScreen();
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
      sleep(500);
      clearScreen();
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

        Enter number corresponding to where you want to go:  """;
    System.out.print(welcomeScreen);
    boolean validChoice = false;
    while (!validChoice) {
      String choice = scanner.nextLine();
      validChoice = true;
      switch(choice) {
        case "1": 
          clearScreen();
          displayRegisteredCourses();
          break;
        case "2":
          clearScreen();
          searchCourses();
          break;
        case "3": 
        clearScreen();
          displayUser();
          break;
        case "4":
          clearScreen();
          createCourse();
          break;
        case "5":
          clearScreen();
          System.out.println("Saving progess...");
          sleep(500);
          System.out.println("Goodbye!");
          application.logout();
          break;
        default:
          validChoice = false;
          System.out.print("Please enter a valid number: ");
    }

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
    clearScreen();
    ArrayList<String> options = application.getRegisteredCourseStrings();
    options.add("Return to Main Menu");
    displayOptions(options);
    System.out.print("Enter number to view a course or return to main menu: ");
    int choice = getUserChoice(options);
    if (choice <= application.getRegisteredCourses().size()) {
      application.moveToCourse(choice);
      displayRegisteredCourse();
    } else {
      displayMainMenu();
    }
  }

  private void displayRegisteredCourse() {
    clearScreen();
    System.out.println(application.getCourse().getDescription() + "\n");
    System.out.println("Grade: " + application.getCourseGrade() + "\n");
    ArrayList<String> options = application.getModuleStrings();
    options.add("Return to Registered Courses");
    options.add("Return to Main Menu");
    displayOptions(options);
    System.out.print("Enter number to view module lessons or return to another menu: ");
    int choice = getUserChoice(options);
    if (choice <= application.getModules().size()) {
      application.moveToModule(choice);
      displayModule();
    } else if (choice == options.size() - 2) {
      displayRegisteredCourses();
    } else if (choice == options.size() - 1) {
      displayMainMenu();
    }
  }

  private void displayModule() {
    clearScreen();
    ArrayList<Lesson> lessons = application.getLessons();
    String[] options = {"Take Module Quiz", "Return to Modules", "Return to Registered Courses", "Return to Main Menu"};
    for (Lesson lesson : lessons) {
      System.out.println(lesson + "\n");
    }
    displayOptions(options);
    int choice = getUserChoice(options);
    switch (choice) {
      case 1:
        performAssessment();
      case 2:
        displayRegisteredCourse();
      case 3:
        displayRegisteredCourses();
      case 4:
        displayMainMenu();
    }
  }

  private void performAssessment() {
    clearScreen();
    Assessment quiz = application.getQuiz();
    ArrayList<Integer> answers = new ArrayList<Integer>();
    for (Question question : quiz.getQuestions()) {
      System.out.println(question.getQuestion());
      displayOptions(question.getAnswerChoices());
      int choice = getUserChoice(question.getAnswerChoices());
      answers.add(choice - 1);
    }
    double score = application.evaluateAssessment(answers);
    displayScore(score);
  }

  private void displayScore(double score) {
    clearScreen();
    System.out.println("You scored a " + score);
  }

  private void displayAllCourses() {

  }

  private void searchCourses() {
    System.out.println("Enter a keyword to search for (or press ENTER to see all courses): ");
    String keyword = scanner.nextLine();
    ArrayList<Course> courses = application.findCourses(keyword);
    displayCourses(courses);
  }

  private void navigateCourse() {

  }

  private void navigateModule() {

  }

  private void createAccount() {
    System.out.print("Please enter your email address: ");
    String email = scanner.nextLine();
    System.out.print("Please enter your first name: ");
    String firstName = scanner.nextLine();
    System.out.print("Please enter your last name: ");
    String lastName = scanner.nextLine();
    System.out.print("Please enter a username: ");
    String username = scanner.nextLine();
    System.out.print("Please enter a password: ");
    String password = scanner.nextLine();
    clearScreen();
    System.out.println("Thank you for creating an account\nLogging in...");
    application.addUser(firstName, lastName, email, username, password);
    sleep(500);
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

  private void displayCourses(ArrayList<Course> courses) {
    for (int i = 0; i < courses.size(); i++) {
      System.out.println((i + 1) + ". " + courses.get(i));
    }
  }

  private void displayOptions(ArrayList<String> options) {
    for (int i = 0; i < options.size(); i++) {
      System.out.println((i + 1) + ". " + options.get(i));
    }
  }

  private int getUserChoice(ArrayList<String> options) {
    boolean validChoice = false;
    int numChoice = -1;
    while (!validChoice) {
      validChoice = true;
      String choice = scanner.nextLine();
      try {
        numChoice = Integer.parseInt(choice);
      } catch (NumberFormatException e) {
        numChoice = -1;
      }
      if (numChoice <= 0 && numChoice > options.size()) {
        validChoice = false;
        System.out.print("Please enter a valid number: ");
      }
    }
    return numChoice;
  }

  private void displayOptions(String[] options) {
    for (int i = 0; i < options.length; i++) {
      System.out.println((i + 1) + ". " + options[i]);
    }
  }

  private int getUserChoice(String[] options) {
    boolean validChoice = false;
    int numChoice = -1;
    while (!validChoice) {
      validChoice = true;
      String choice = scanner.nextLine();
      try {
        numChoice = Integer.parseInt(choice);
      } catch (NumberFormatException e) {
        numChoice = -1;
      }
      if (numChoice <= 0 && numChoice > options.length) {
        validChoice = false;
        System.out.print("Please enter a valid number: ");
      }
    }
    return numChoice;
  }
}