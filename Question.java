package LMS;
public class Question {
    private String question;
    private ArrayList<String> answerChoices;
    private int correctAnswer;

    public Question(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }
    public boolean evaulate(int userAnswer) {
        return True;
    }
    public String getQuestion() {
        return null;
    }
}
