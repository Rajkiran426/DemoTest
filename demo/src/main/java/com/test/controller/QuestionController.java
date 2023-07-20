package com.test.controller;

import com.test.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/fetch-questions")
    public String fetchAndStoreRandomQuestions() {
        questionService.fetchAndSaveRandomQuestions();
        return "5 random questions fetched and stored in the database.";
    }
}

