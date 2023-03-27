package src;

import java.util.ArrayList;

public class Question {
  private String question;
  private ArrayList<String> answerChoices;
  private int correctAnswer;

  public Question(String question, ArrayList<String> answerChoices, int correctAnswer) {
    this.question = question;
    this.answerChoices = answerChoices;
    this.correctAnswer = correctAnswer;
  }
  
  public boolean evaulate(int userAnswer) {
    return correctAnswer == userAnswer;
  }

  public String getQuestion() {
    return question;
  }

  public ArrayList<String> getAnswerChoices() {
    return answerChoices;
  }

  public int getCorrectAnswer() {
    return correctAnswer;
  }

  public String toString() {
    return this.question;
  }
}
