package com.example.quiz.app.service;

import com.example.quiz.app.Repo.QuestionRepo;
import com.example.quiz.app.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public List<Question> getQuestionsByCategory(String category) {
        System.out.println(category);
        return repo.findByCategory(category);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            repo.save(question);
            return ResponseEntity.status(HttpStatus.OK).body("Successful");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Add");
        }
    }
}
