package LMS;

import java.util.ArrayList;

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
}

