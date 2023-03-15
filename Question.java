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
        return true;
    }
    public String getQuestion() {
        return null;
    }
}
