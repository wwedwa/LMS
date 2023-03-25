package src;

import java.util.ArrayList;

/**
 * assessment class
 * @author The Lobsters
 */
public class Assessment {
    private ArrayList<Question> questions;
    /**
    * assessment constructor
    */
    public Assessment() {
        this.questions = new ArrayList<Question>();
    }
    /**
    * assessment constructor
    *@param questions
    */
    public Assessment(ArrayList<Question> questions) {
        this.questions = questions;
    }
    /**
    * add question to assessment
    *@param question
    */
    public boolean addQuestion(Question question) {
        if (questions.size() >= 10)
            return false;
        return questions.add(question);
    }
    /**
    * returns questions in assessment
    */
    public ArrayList<Question> getQuestions() {
        return questions;
    }
    /**
    * evaluate assessment 
    *@param answers
    */
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

