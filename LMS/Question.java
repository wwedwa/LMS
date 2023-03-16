package LMS;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> answerChoices;
    private int correctAnswer;

    public Question(String question, int correctAnswer) {
        this.question = question;
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
}
