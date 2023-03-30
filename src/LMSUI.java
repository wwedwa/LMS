package src;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LMSUI {
  private Scanner scanner;
  private LMS application;
  String[] welcomeAuthorOptions = {"Explore Registered Courses", "Rate Registered Courses", "Register for Courses", "Create Course", "Edit Created Courses", "Logout"};
  String[] welcomeStudentOptions = {"Explore Registered Courses", "Rate Registered Courses", "Register for Courses", "Logout"};

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
    if (application.getUser().getType().equals("author")) {
      displayAuthorMainMenu();
    } else {
      displayStudentMainMenu();
    }

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

  private void displayStudentMainMenu() {
    while (true) {
      clearScreen();
      System.out.println("Welcome " + application.getUser().getUsername());
      displayOptions(welcomeStudentOptions);
      System.out.print("\nEnter number corresponding to what you want to do: ");
      int choice = getUserChoice(welcomeStudentOptions.length);
      switch(choice) {
        case 1: 
          displayRegisteredCourses();
          break;
        case 2:
          rateRegisteredCourses();
          break;
        case 3: 
          searchCourses();
          break;
        case 4:
          System.out.println("Saving progess...");
          sleep(500);
          System.out.println("Goodbye!");
          application.logout();
      }
    }
  }

  private void displayAuthorMainMenu() {
    while (true) {
      clearScreen();
      System.out.println("Welcome " + application.getUser().getUsername());
      displayOptions(welcomeAuthorOptions);
      System.out.print("\nEnter number corresponding to what you want to do: ");
      int choice = getUserChoice(welcomeAuthorOptions.length);
      switch(choice) {
        case 1: 
          displayRegisteredCourses();
          break;
        case 2:
          rateRegisteredCourses();
          break;
        case 3: 
          searchCourses();
          break;
        case 4:
          createCourse();
          break;
        case 5:
          editCourse();
          break;
        case 6:
          System.out.println("Saving progess...");
          sleep(500);
          System.out.println("Goodbye!");
          application.logout();
      }
    }
  }

  private void viewCourseForum() {
    ArrayList<String> options = new ArrayList<String>();
    while (true) {
      clearScreen();
      options = application.generateComments(application.getCourseComments());
      displayOptions(options);
      System.out.println("\n" + (options.size() + 1) + ". Add Comment");
      System.out.println((options.size() + 2) + ". Return to Course Information");
      System.out.print("\nChoose a comment to reply to, create a comment, or return to previous menu: ");
      int choice = getUserChoice(options.size() + 2);
      if (choice <= options.size()) {
        System.out.print("Enter reply: ");
        String reply = scanner.nextLine();
        application.addReply(choice, reply, application.getCourseComments());
      } else if (choice == options.size() + 1) {
        System.out.print("Enter comment: ");
        String comment = scanner.nextLine();
        application.addCourseComment(comment);
      } else if (choice == options.size() + 2) {
        return;
      }
    }
  }

  private void viewModuleForum() {
    ArrayList<String> options = new ArrayList<String>();
    while (true) {
      clearScreen();
      options = application.generateComments(application.getModuleComments());
      displayOptions(options);
      System.out.println("\n" + (options.size() + 1) + ". Add Comment");
      System.out.println((options.size() + 2) + ". Return to Module");
      System.out.print("\nChoose a comment to reply to, create a comment, or return to previous menu: ");
      int choice = getUserChoice(options.size() + 2);
      if (choice <= options.size()) {
        System.out.print("Enter reply: ");
        String reply = scanner.nextLine();
        application.addReply(choice, reply, application.getModuleComments());
      } else if (choice == options.size() + 1) {
        System.out.print("Enter comment: ");
        String comment = scanner.nextLine();
        application.addModuleComment(comment);
      } else if (choice == options.size() + 2) {
        return;
      }
    }
  }

  private void displayRegisteredCourses() {
    ArrayList<String> options = application.getRegisteredCourseStrings();
    options.add("Return to Main Menu");
    while (true) {
      clearScreen();
      displayOptions(options);
      System.out.print("\nEnter number to view a course or return to main menu: ");
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
    options.add("View Course Comments");
    options.add("Download Certificate of Completion");
    options.add("Return to Registered Courses");
    while (true) {
      clearScreen();
      System.out.println(application.getCourse().getDescription() + "\n");
      System.out.println("Grade: " + String.format("%.2f", application.getCourseGrade()) + "%\n");
      displayOptions(options);
      System.out.print("\nEnter number to view module lessons or return to previous menu: ");
      int choice = getUserChoice(options.size());
      if (choice < options.size() - 2) {
        application.moveToModule(choice);
        displayModule();
      } else if (choice == options.size() - 2) {
        viewCourseForum();
      } else if (choice == options.size() - 1) {
        if (application.isCourseCompleted()) {
          application.createCertificate();
          System.out.println("Your certificate has been downloaded!");
        } else {
          System.out.println("Please complete the course first.");
        }
        sleep(1000);
      } else if (choice == options.size()) {
        return;
      }
      // Recreate options incase changes to grades
      options = application.getModuleGradeStrings();
      options.add("View Course Comments");
      options.add("Download Certificate of Completion");
      options.add("Return to Registered Courses");
    }
  }

  private void displayModule() {
    ArrayList<Lesson> lessons = application.getLessons();
    String[] options = {"Take Module Quiz", "View Module Comments", "Download Module Content", "Return to Course Information"};
    while (true) {
      clearScreen();
      for (Lesson lesson : lessons) {
        System.out.println(lesson + "\n");
      }
      displayOptions(options);
      int choice = getUserChoice(options.length);
      switch (choice) {
        case 1:
          performAssessment();
          return;
        case 2:
          viewModuleForum();
          break;
        case 3:
          if (application.saveModule()) {
            System.out.println("The module has been downloaded!");
          } else {
            System.out.println("There was a problem downloading the module. Please try again later.");
          }
          sleep(1000);
          break;
        case 4:
          return;
      }
    }
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
    System.out.println("You scored a " + score + "%");
    String[] options = {"Take Quiz Again", "Return to Course Information"};
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

  private void searchCourses() {
    clearScreen();
    System.out.print("Enter a keyword to search for (or press ENTER to see all courses): ");
    String keyword = scanner.nextLine();
    ArrayList<Course> courses = application.findCourses(keyword);
    ArrayList<String> options = new ArrayList<String>();
    for (Course course : courses) {
      options.add(course.toString());
    }
    options.add("Return to Main Menu");
    displayOptions(options);
    System.out.print("Choose a course number to see details or return to main menu: ");
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
    String difficulty = application.getCourse().getDifficulty().toString();
    String[] options = {"View Reviews", "Register for Course", "Return to Main Menu"};
    while (true) {
      clearScreen();
      System.out.println("----- " + application.getCourse() + " -----\n\n" + "Difficulty: " + difficulty + "\n" + description + "\n");
      displayOptions(options);
      System.out.println("\nEnter number corresponding to what you want to do: ");
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
    clearScreen();
    ArrayList<String> options = application.getRegisteredCourseStrings();
    options.add("Return to Main Menu");
    displayOptions(options);
    System.out.print("\nEnter number to rate a course or return to main menu: ");
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
    Date birthday = getBirthday();
    String username = getField("Please enter a username: ");
    String password = getField("Please enter a password: ");
    String type = getType();
    clearScreen();
    while (!application.addUser(username, firstName, lastName, email, password, type, birthday)) {
      System.out.print("Username is taken. Please enter new username: ");
      username = scanner.nextLine();
    }
    System.out.println("Thank you for creating an account\nLogging in...");
    sleep(1000);
  }

  private String getType() {
    System.out.print("Do you want to be an (a)uthor or (s)tudent (Enter \"a\" or \"s\"): ");
    String type = scanner.nextLine();
    while (!type.toLowerCase().equals("a") && !type.toLowerCase().equals("s")) {
      System.out.print("Invalid choice. Choose \"a\" or \"s\": ");
      type = scanner.nextLine();
    }
    return (type.equals("a") ? "author" : "student");
  }

  private Date getBirthday() {
    boolean valid = false;
    Date birthday = new Date();
    while (!valid) {
      try {
        valid = true;
        System.out.print("Please enter birthday in the form of MM/DD/YYYY: ");
        String dob = scanner.nextLine();
        birthday = new SimpleDateFormat("MM/dd/yyyy").parse(dob);
      } catch (ParseException e) {
        valid = false;
      }
    }
    return birthday;
  }

  private void createCourse() {
    clearScreen();
    System.out.println("Current size of course list: " + application.getAllCourses().size());
    ArrayList<Module> modules = new ArrayList<Module>();
    String courseName = getField("Enter the name of the course: ");
    String description = getField("\nEnter the course Description: \n");
    Language language = getLanguage();
    System.out.println("\nEnter the Course Difficulty: ");
    Difficulty difficulty = getDifficulty();
    createModules(modules); 
    System.out.println("Thank you! Checking new course...");
    sleep(500);
    while (!application.addCourse(courseName, language, description, modules, difficulty)) {
      courseName = getField("Course title is taken. Please enter new title for your course: ");
    }
    System.out.println("Saving new course...");
    sleep(1000);
  }

  private void createLessons(ArrayList<Lesson> lessons) {
    boolean addingLessons = true;
    while (addingLessons) {
      System.out.print("Please enter the name of the lesson: ");
      String lessonName = scanner.nextLine();
      System.out.println("Enter all text for the lesson (Enter a line with just \"finished\" to end):");
      String moduleText = "";
      String tempLine = scanner.nextLine();
      do {
        moduleText += tempLine;
        tempLine = scanner.nextLine();
        tempLine = "\n" + tempLine;
      }  while(!tempLine.equals("\nfinished"));
      Lesson lesson = new Lesson(lessonName, moduleText);
      lessons.add(lesson);
      System.out.print("Would you like to add another lesson? (y/n): ");
      String choice = getYesNoChoice();
      if (choice.equals("n")) {
        addingLessons = false;
      }
    }
  }

  private Language getLanguage() {
    clearScreen();
    Language[] languages = Language.values();
    ArrayList<String> options = new ArrayList<String>();
    for (Language language : languages) {
      options.add(language.toString());
    }
    displayOptions(options);
    System.out.print("Please select the number corresponding to the language your course is on: ");
    int choice = getUserChoice(options.size());
    return languages[choice - 1];
  }

  private Difficulty getDifficulty() {
    clearScreen();
    Difficulty[] difficulties = Difficulty.values();
    ArrayList<String> options = new ArrayList<String>();
    for (Difficulty difficulty : difficulties) {
      options.add(difficulty.toString());
    }
    displayOptions(options);
    System.out.print("Please select the number corresponding to the difficulty of your course: ");
    int choice = getUserChoice(options.size());
    return difficulties[choice - 1];
  }

  private void createModules(ArrayList<Module> modules) {
    clearScreen();
    System.out.println("You will now create modules for your course. Add as many as you want.");
    boolean addingModules = true;
    int moduleNum = 1;
    while(addingModules) {
      String moduleTitle = getField("Enter title for module: ");
      ArrayList<Lesson> lessons = new ArrayList<Lesson>();
      System.out.println("Module " + moduleNum);
      createLessons(lessons);
      ArrayList<Question> questions = new ArrayList<Question>();
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
      ArrayList<String> answers = new ArrayList<String>();
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
    String[] options = {"Return to Menu"};
    displayOptions(options);
    System.out.println("Enter number corresponding to what you want to do: ");
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
        System.out.print("Please enter a valid number (1 - " + numOfOptions + "): ");
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
        System.out.print("Please enter \"y\" or \"n\": ");
      }
    }
    return choice;
  }

  private String getField(String question) {
    System.out.print(question);
    return scanner.nextLine();
  }

  private void editCourse() {
    clearScreen();
    ArrayList<Course> courses = application.getCreatedCourses();
    ArrayList<String> options = new ArrayList<String>();
    for (Course course : courses) {
      options.add(course.getTitle());
    }
    displayOptions(options);
    System.out.println("Enter the number of the course you would like to edit: ");
    int choice = getUserChoice(options.size());
    choice -= 1;
    Course course = application.getCreatedCourses().get(choice);
    System.out.println(course.getTitle());
    System.out.println(course.getDescription());
    for (int i = 0; i < course.getModuleCount(); i++) {
      System.out.println("Module " + (i + 1) + ": " + course.getModules().get(i).getTitle());
    }
    System.out.println("Please enter the number of the module you would like to edit: ");
    int moduleNum = getUserChoice(course.getModuleCount());
    moduleNum -= 1;
    Module module = course.getModules().get(moduleNum); 
    System.out.println("Would you like to edit/add a lesson or quiz? (l/q)");
    String type = scanner.nextLine();
    if (type.equals("q")) {
      editQuestion(module);
    }
    else {
      editLesson(module);
    }
    return;
  }

  private void editLesson(Module module) {
    clearScreen();
    for (int i = 0; i < module.getLessons().size(); i++) {
      System.out.println("Lesson " + (i + 1) + ": " + module.getLessons().get(i).getTitle());
    }
    boolean running = true;
    do {
      System.out.println("Would you like to edit or add a lesson? (e/a/quit)");
      String edit = scanner.nextLine();
      if (edit.equals("a")) {
        System.out.println("You will now make the lesson");
        Lesson lesson = makeLesson();
        System.out.println("Which lesson number do you want this to be? ");
        int choice = getUserChoice(module.getLessons().size());
        module.getLessons().add(choice - 1, lesson);
        running = false;
        System.out.println("Lesson successfully added, returning to main menu...");
        sleep(1000);
      }
      else if (edit.equals("e")) {
        System.out.println("Enter the number of the lesson you would like to edit: ");
        int choice = getUserChoice(module.getLessons().size());
        choice -= 1;
        Lesson lesson = module.getLessons().get(choice);
        System.out.println("You will now re create this lesson.");
        lesson = makeLesson();
        module.getLessons().set(choice, lesson);
        running = false;
        System.out.println("Lesson successfully edited, returning to main menu...");
        sleep(1000);
      }
      else if (edit.equals("quit")) {
        return;
      }
      else {
        System.out.println("Please enter either \"e\" or \"a\" or \"quit\"");
      }
    } while(running);
    
  }

  private void editQuestion(Module module) {
    clearScreen();
    boolean running = true;
    for (int i = 0; i < module.getAssessment().getQuestions().size(); i++) {
      System.out.println("Question " + (i + 1) + ": " + module.getAssessment().getQuestions().get(i));
    }
    do {
      System.out.println("Would you like to edit or add a question? (e/a/quit)");
      String edit = scanner.nextLine();
      if (edit.equals("a")) {
        System.out.println("You will now make the question");
        Question question = makeQuestion();
        System.out.println("Which question number do you want this to be? ");
        int choice = getUserChoice(module.getAssessment().getQuestions().size() + 1);
        module.getAssessment().getQuestions().add(choice - 1, question);
        running = false;
        System.out.println("Question successfully added, returning to main menu...");
        sleep(1000);
      }
      else if (edit.equals("e")) {
        System.out.println("Enter the number of the Question you would like to edit: ");
        int choice = getUserChoice(module.getAssessment().getQuestions().size());
        choice -=1;
        System.out.println("You will now recreate the question");
        Question quest = makeQuestion();
        module.getAssessment().getQuestions().set(choice, quest);
        running = false;
        System.out.println("Question successfully edited, returning to main menu...");
        sleep(1000);
      }
      else if (edit.equals("quit")) {
        return;
      }
      else {
        System.out.println("Please enter either \"e\" or \"a\" or \"quit\"");
      }
    } while (running);
  }

  private Lesson makeLesson() {
    System.out.println("Please enter the name of the lesson: ");
      String lessonName = scanner.nextLine();
      System.out.println("Enter all text for the lesson (Enter a line with just (finished) to end):");
      String moduleText = "";
      String tempLine = scanner.nextLine();
      do {
        moduleText += tempLine;
        tempLine = scanner.nextLine();
        tempLine = "\n" + tempLine;
      } while(!tempLine.equals("\nfinished"));
      return new Lesson(lessonName, moduleText);
    }

    private Question makeQuestion() {
      System.out.print("Enter the question: ");
      String questionContent = scanner.nextLine();
      System.out.print("Enter the number of answer choices (up to 4): ");
      int choices = getUserChoice(4);
      ArrayList<String> answers = new ArrayList<String>();
      for (int i = 0; i < choices; i++) {
        System.out.print("Enter answer choice " + (i + 1) + ": ");
        answers.add(scanner.nextLine());
      }
      System.out.println("Enter the number of the correct answer: ");
      int correctAnswer = scanner.nextInt();
      scanner.nextLine();
      return new Question(questionContent, answers, correctAnswer - 1);
    }
}