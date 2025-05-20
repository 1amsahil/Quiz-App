package com.example.quiz.app.service;

import com.example.quiz.app.Repo.QuestionRepo;
import com.example.quiz.app.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.EOFException;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo repo;

    public ResponseEntity<?> getAllQuestions()
    {
        List<Question> questions = repo.findAll();

       try
        {
            return new ResponseEntity<>(questions,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("NOT FOUND, Check URL",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> load(List<Question> questions)
    {
        try {
            repo.saveAll(questions);
            return new ResponseEntity<>("Successful",HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Failed to Load",HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> getQuestionsByCategory(String category) {
        System.out.println(category);

        List<Question> questions = repo.findByCategory(category);

        if(questions == null || questions.isEmpty())
        {
            return new ResponseEntity<>("Not Found "+category,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(questions,HttpStatus.OK);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            repo.save(question);
            return new ResponseEntity<>("Successful",HttpStatus.CREATED);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Add");
        }
    }
}
