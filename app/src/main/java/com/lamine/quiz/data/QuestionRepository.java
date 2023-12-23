package com.lamine.quiz.data;

import java.util.List;

public class QuestionRepository {

 private final QuestionBank mQuestionBank;

    public QuestionRepository(QuestionBank QuestionBank) {
        this.mQuestionBank = QuestionBank;
    }

    public List<Question> getQuestions() {
        return mQuestionBank.getQuestions();
    }
}

