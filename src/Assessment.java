package src;

import java.util.ArrayList;

/**
 * assessment class
 * @author The Lobsters
 */
public class Assessment {
    private ArrayList<Question> questions;

    public Assessment() {
        this.questions = new ArrayList<Question>();
    }
    public Assessment(ArrayList<Question> questions) {
        this.questions = questions;
    }
    public boolean addQuestion(Question question) {
        if (questions.size() >= 10)
            return false;
        return questions.add(question);
    }
    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public double evaluateAssessment(ArrayList<Integer> answers) {
        double score = 0;
        for(int i = 0; i < questions.size(); i++) {
            Integer correctAnswer = questions.get(i).getCorrectAnswer();
            Integer userAnswer = answers.get(i);
            if(userAnswer == correctAnswer) {
                score++;
            }
        }
        score /= questions.size();
        return score;
    }
}

