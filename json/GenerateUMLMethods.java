package json;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class GenerateUMLMethods {
  // ***CHANGE BELOW VARIABLE FOR WHATEVER FILE YOU WANT THE METHODS OF***
  private static String FILE_NAME = "src/Course.java";
  public static void main(String[] args) {
    try {
      File myObj = new File(FILE_NAME);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        if (data.contains("public") && data.contains("(") && data.contains(")")) {
          int index = data.indexOf("public");
          data = data.substring(index + 6);
          String method = data.substring(data.indexOf(" ") + 1, data.length() - 2);
          String returnType = "";
          try {
            returnType = method.substring(0, method.indexOf(" "));
          } catch (StringIndexOutOfBoundsException e) {
            System.out.println("No return type (Probably constructor). Moving to next method...");
            continue;
          }
          String methodName = method.substring(method.indexOf(" ") + 1);
          data = "+ " + methodName + ": " + returnType;
          System.out.println(data);
        } else if (data.contains("private") && data.contains("(") && data.contains(")")) {
          int index = data.indexOf("private");
          data = data.substring(index + 7);
          String method = data.substring(data.indexOf(" ") + 1, data.length() - 2);
          String returnType = "";
          try {
            returnType = method.substring(0, method.indexOf(" "));
          } catch (StringIndexOutOfBoundsException e) {
            System.out.println("No return type (Probably constructor). Moving to next method...");
            continue;
          }          String methodName = method.substring(method.indexOf(" ") + 1);
          data = "- " + methodName + ": " + returnType;
          System.out.println(data);
        }
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
