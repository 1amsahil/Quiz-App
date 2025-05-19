package com.example.quiz.app.controller;

import com.example.quiz.app.entity.Question;
import com.example.quiz.app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/all")
    public List<?> allQuestion()
    {
        return questionService.getAllQuestions();
    }

    @PostMapping("/load")
        public String load(@RequestBody List<Question> questions)
        {
            questionService.load(questions);
            return "Successful";
        }


}
