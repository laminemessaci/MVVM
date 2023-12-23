package com.lamine.quiz.data;

import java.util.List;


public class Question {

    private final String question;
    private final List<String> answers;
    private final Integer answerIndex;


    public Question(String question, List<String> answers, Integer answerIndex) {
        this.question = question;
        this.answers = answers;
        this.answerIndex = answerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public Integer getAnswerIndex() {
        return answerIndex;
    }
}
