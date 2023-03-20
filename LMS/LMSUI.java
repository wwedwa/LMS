package LMS;

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
    System.out.print("\nEnter the name of the course: ");
    String courseName = scanner.nextLine();
    System.out.println("\nEnter the course Description: ");
    String description = "";
    String tempLine;
    do {
      tempLine = scanner.nextLine();
      description += tempLine;
    } while(tempLine != "");
    System.out.println("\nEnter the Course Difficulty: ");
    Difficulty difficulty = null;
    try {
      difficulty = Difficulty.valueOf(scanner.nextLine());
    } catch(Exception e) {
      while(difficulty != Difficulty.EASY && difficulty != Difficulty.MEDIUM && difficulty != Difficulty.HARD) {
        try {
        difficulty = Difficulty.valueOf(scanner.nextLine());
        } catch (Exception err) {}
      }
    }

    boolean addingModules = true;
    int moduleNum = 1;
    while(addingModules) {
      System.out.println("Module " + moduleNum);
      System.out.println("Enter all text for the module (Enter a line with just (finished) to end):");
      String moduleText = "";
      tempLine = "";
      do {
        moduleText += tempLine;
        tempLine = scanner.nextLine();
      } while(tempLine != "finished");
      System.out.println("You will now create a quiz for the lesson that consists of up to 5 questions");
      System.out.print("Enter the question: ");
      String question = scanner.nextLine();
      System.out.print("Enter the number of answer choices (up to 4): ");
      int choices = scanner.nextInt();
      String[] answers = new String[choices];
      for (int i = 0; i < choices; i++) {
        System.out.print("Enter answer choice " + i + ": ");
        answers[i] = scanner.nextLine();
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
}