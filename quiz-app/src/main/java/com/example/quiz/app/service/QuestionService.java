package com.example.quiz.app.service;

import com.example.quiz.app.Repo.QuestionRepo;
import com.example.quiz.app.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo repo;

    public List<Question> getAllQuestions()
    {
        return repo.findAll();
    }

    public void load(List<Question> questions)
    {
        repo.saveAll(questions);
    }
}
