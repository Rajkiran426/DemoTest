package com.test.payload;

import com.test.entity.Question;

public class NextQuestionResponse {
    private String correct_answer;
    private Question next_question;

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public Question getNext_question() {
        return next_question;
    }

    public void setNext_question(Question next_question) {
        this.next_question = next_question;
    }
}