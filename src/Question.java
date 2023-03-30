package src;

import java.util.ArrayList;
/**
 * Question class
 * @author The Lobsters
 */
public class Question {
  private String question;
  private ArrayList<String> answerChoices;
  private int correctAnswer;
  /**
   * Question constructor
   * @param question
   * @param answerChoices
   * @param correctAnswer
   */
  public Question(String question, ArrayList<String> answerChoices, int correctAnswer) {
    this.question = question;
    this.answerChoices = answerChoices;
    this.correctAnswer = correctAnswer;
  }
  /** 
   * evaluates answer
   * @param userAnswer
   * @return boolean
   */
  public boolean evaulate(int userAnswer) {
    return correctAnswer == userAnswer;
  }
  /** 
   * question accessor
   * @return String
   */
  public String getQuestion() {
    return question;
  }
  /** 
   * answer choices accessor
   * @return ArrayList<String>
   */
  public ArrayList<String> getAnswerChoices() {
    return answerChoices;
  }
  /** 
   * correct answer accessor
   * @return int
   */
  public int getCorrectAnswer() {
    return correctAnswer;
  }
  /** 
   * toString
   * @return String
   */
  public String toString() {
    return this.question;
  }
}
