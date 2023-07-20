package com.test.controller;

import com.test.entity.Question;
import com.test.exception.QuestionNotFoundException;
import com.test.payload.AnswerPayload;
import com.test.payload.NextQuestionResponse;
import com.test.repository.QuestionRepository;
import com.test.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    @Autowired
    public GameController(QuestionRepository questionRepository, QuestionService questionService) {
        this.questionRepository = questionRepository;
        this.questionService = questionService;
    }

    @GetMapping("/play")
    public ResponseEntity<Question> play() {
        Question question = getRandomQuestion();
        return ResponseEntity.ok(question);
    }

    @PostMapping("/next")
    public ResponseEntity<?> next(@RequestBody AnswerPayload answerPayload) {
        Long questionId = answerPayload.getQuestion_id();
        String answer = answerPayload.getAnswer();


        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found"));

        String correctAnswer = question.getAnswer();
        
        Question nextQuestion = getRandomQuestion();
        NextQuestionResponse nextQuestionResponse = new NextQuestionResponse();
        nextQuestionResponse.setCorrect_answer(correctAnswer);
        nextQuestionResponse.setNext_question(nextQuestion);

        return ResponseEntity.ok(nextQuestionResponse);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<String> handleQuestionNotFoundException(QuestionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    private Question getRandomQuestion() {
        return questionRepository.findAll().stream().findAny()
                .orElseThrow(() -> new QuestionNotFoundException("No questions found"));
    }
}


