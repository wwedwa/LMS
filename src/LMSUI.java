package src;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LMSUI {
  private Scanner scanner;
  private LMS application;
  String[] welcomeOptions = {"View Registered Courses", "Rate Registered Courses", "View Created Courses", "Register for Courses", 
  "View Account Details", "Create Course", "Logout"};

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
    String choice = getYesNoChoice();
    if (choice.equals("y")) {
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
    while (true) {
      clearScreen();
      System.out.println("Welcome " + application.getUser().getUsername());
      displayOptions(welcomeOptions);
      System.out.println("Enter number corresponding to what you want to do: ");
      int choice = getUserChoice(welcomeOptions.length);
      switch(choice) {
        case 1: 
          displayRegisteredCourses();
          break;
        case 2:
          rateRegisteredCourses();
          break;
        case 3:
          displayCreatedCourses();
          break;
        case 4: 
          searchCourses();
          break;
        case 5:
          displayUser();
          break;
        case 6:
          createCourse();
          break;
        case 7:
          System.out.println("Saving progess...");
          sleep(500);
          System.out.println("Goodbye!");
          application.logout();
      }
    }
  }

  private void displayUser() {
    clearScreen();
  }

  private void displayRegisteredCourses() {
    ArrayList<String> options = application.getRegisteredCourseStrings();
    options.add("Return to Main Menu");
    while (true) {
      clearScreen();
      displayOptions(options);
      System.out.print("Enter number to view a course or return to main menu: ");
      int choice = getUserChoice(options.size());
      if (choice < options.size()) {
        application.moveToRegisteredCourse(choice);
        displayRegisteredCourse();
      } else if (choice == options.size()) {
        return;
      }
    }
  }

  private void displayRegisteredCourse() {
    ArrayList<String> options = application.getModuleGradeStrings();
    options.add("Return to Registered Courses");
    while (true) {
      clearScreen();
      System.out.println(application.getCourse().getDescription() + "\n");
      System.out.println("Grade: " + application.getCourseGrade() + "\n");
      displayOptions(options);
      System.out.print("Enter number to view module lessons or return to previous menu: ");
      int choice = getUserChoice(options.size());
      if (choice < application.getModules().size()) {
        application.moveToModule(choice);
        displayModule();
      } else if (choice == options.size()) {
        return;
      }
      // Recreate options incase changes to grades
      options = application.getModuleGradeStrings();
      options.add("Return to Registered Courses");
    }
  }

  private void displayModule() {
    ArrayList<Lesson> lessons = application.getLessons();
    String[] options = {"Take Module Quiz", "Return to Module List"};
    for (Lesson lesson : lessons) {
      System.out.println(lesson + "\n");
    }
    displayOptions(options);
    int choice = getUserChoice(options.length);
    if (choice == 1) {
      performAssessment();
    }
    return;
  }

  private void performAssessment() {
    clearScreen();
    Assessment quiz = application.getQuiz();
    if (quiz.getQuestions().size() == 0) {
      System.out.println("No quiz for this module!\n");
      String[] options = {"Return to Lessons"};
      displayOptions(options);
      int choice = getUserChoice(options.length);
      if (choice == 1) {
        return;
      }
    } else {
      ArrayList<Integer> answers = new ArrayList<Integer>();
      for (Question question : quiz.getQuestions()) {
        System.out.println(question.getQuestion());
        displayOptions(question.getAnswerChoices());
        int choice = getUserChoice(question.getAnswerChoices().size());
        answers.add(choice - 1);
      }
      double score = application.evaluateAssessment(answers);
      displayScore(score);
    }
  }

  private void displayScore(double score) {
    clearScreen();
    System.out.println("You scored a " + (score * 100) + "%");
    String[] options = {"Take Quiz Again", "Return to List of Modules"};
    displayOptions(options);
    int choice = getUserChoice(options.length);
    switch (choice) {
      case 1:
        performAssessment();
        break;
      case 2:
        return;
    }
  }

  private void displayCreatedCourses() {

  }

  private void searchCourses() {
    clearScreen();
    System.out.println("Enter a keyword to search for (or press ENTER to see all courses): ");
    String keyword = scanner.nextLine();
    ArrayList<Course> courses = application.findCourses(keyword);
    ArrayList<String> options = new ArrayList<String>();
    for (Course course : courses) {
      options.add(course.toString());
    }
    options.add("Return to Main Menu");
    displayOptions(options);
    int choice = getUserChoice(options.size());
    if (choice < options.size()) {
      application.moveToCourse(choice);
      displayCourse();
    } else if (choice == options.size()) {
      return;
    }
  }

  private void displayCourse() {
    String description = application.getCourseDescription();
    String[] options = {"View Reviews", "Register for Course", "Return to Main Menu"};
    while (true) {
      clearScreen();
      System.out.println("----- " + application.getCourse() + " -----\n" + description + "\n");
      displayOptions(options);
      int choice = getUserChoice(options.length);
      switch (choice) {
        case 1:
          displayReviews();
          break;
        case 2:
          application.register();
          System.out.println("You are registered!");
          sleep(1000);
          return;
        case 3:
          return;
      }
    }
  }

  private void rateRegisteredCourses() {
    ArrayList<String> options = application.getRegisteredCourseStrings();
    options.add("Return to Main Menu");
    clearScreen();
    displayOptions(options);
    System.out.print("Enter number to rate a course or return to main menu: ");
    int choice = getUserChoice(options.size());
    if (choice < options.size()) {
      application.moveToRegisteredCourse(choice);
      createReview();
    } else if (choice == options.size()) {
      return;
    }
  }

  private void createAccount() {
    clearScreen();
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
    application.addUser(username, firstName, lastName, email, password);
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
    application.addCourse(courseName, language, description, modules, difficulty);
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
      String choice = getYesNoChoice();
      if (choice.equals("n")) {
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
      String choice = getYesNoChoice();
      if (choice.equals("n")) {
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
      int choices = getUserChoice(4);
      scanner.nextLine();
      ArrayList<String> answers = new ArrayList<>();
      for (int i = 0; i < choices; i++) {
        System.out.print("Enter answer choice " + (i + 1) + ": ");
        answers.add(scanner.nextLine());
      }
      System.out.println("Enter the number of the correct answer: ");
      int correctAnswer = scanner.nextInt();
      scanner.nextLine();
      Question quest = new Question(question, answers, correctAnswer - 1);
      questions.add(quest);
      if (questions.size() < 5) {
        System.out.println("Would you like to keep adding questions? (y/n) ");
        String choice = getYesNoChoice();
        if (choice.equals("n")) {
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

  private void displayReviews() {
    clearScreen();
    ArrayList<Review> reviews = application.getReviews();
    for (Review review : reviews) {
      System.out.println(review);
    }
    String[] options = {"Return to Course Description"};
    displayOptions(options);
    int choice = getUserChoice(options.length);
    if (choice == 1) {
      return;
    }
  }

  private void createReply(Comment comment) {

  }

  private void createModuleComment() {

  }

  private void createCourseComment() {

  }

  private void createReview() {
    clearScreen();
    if (application.hasReviewed()) {
      System.out.println("Sorry, you have already written a review for this course\n");
    } else {
      System.out.print("Write review: ");
      String review = scanner.nextLine();
      System.out.print("Enter whole number rating (1 - 5): ");
      int rating = getUserChoice(5);
      application.addReview(review, rating);
      System.out.println("Thank you for the review!\n");
    }
    String[] options = {"Return to Modules"};
    displayOptions(options);
    int choice = getUserChoice(options.length);
    if (choice == 1) {
      return;
    }
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

  private void displayOptions(ArrayList<String> options) {
    for (int i = 0; i < options.size(); i++) {
      System.out.println((i + 1) + ". " + options.get(i));
    }
  }

  private void displayOptions(String[] options) {
    for (int i = 0; i < options.length; i++) {
      System.out.println((i + 1) + ". " + options[i]);
    }
  }

  private int getUserChoice(int numOfOptions) {
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
      if (numChoice <= 0 || numChoice > numOfOptions) {
        validChoice = false;
        System.out.print("Please enter a valid number: ");
      }
    }
    return numChoice;
  }

  private String getYesNoChoice() {
    boolean validChoice = false;
    String choice = "";
    while (!validChoice) {
      validChoice = true;
      choice = scanner.nextLine().toLowerCase();
      if (!choice.equals("y") && !choice.equals("n")) {
        validChoice = false;
        System.out.print("Please enter a valid number: ");
      }
    }
    return choice;
  }
}