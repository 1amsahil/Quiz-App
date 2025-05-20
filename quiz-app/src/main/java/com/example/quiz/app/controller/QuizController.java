package com.example.quiz.app.controller;

import com.example.quiz.app.entity.Response;
import com.example.quiz.app.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestParam String category, @RequestParam String title)
    {
        return quizService.createQuiz(category, title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable int id)
    {
        return quizService.getQuiz(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses )
    {
        return quizService.calculate(id, responses);
    }

}
