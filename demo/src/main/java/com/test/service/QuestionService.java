package com.test.service;

import com.test.entity.Question;
import com.test.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class QuestionService {

    private static final String RANDOM_API_URL = "https://jservice.io/api/random";

    private final QuestionRepository questionRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, RestTemplate restTemplate) {
        this.questionRepository = questionRepository;
        this.restTemplate = restTemplate;
    }

    public void fetchAndSaveRandomQuestions() {
        Question[] questions = restTemplate.getForObject(RANDOM_API_URL, Question[].class);
        if (questions != null && questions.length > 0) {
            questionRepository.saveAll(Arrays.asList(questions));
        }
    }
}

