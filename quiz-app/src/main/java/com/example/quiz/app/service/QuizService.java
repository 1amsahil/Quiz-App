package com.example.quiz.app.service;

import com.example.quiz.app.Repo.QuizRepo;
import com.example.quiz.app.entity.Question;
import com.example.quiz.app.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;

    public ResponseEntity<?> createQuiz(String category,String title) {

        List<Question> question = quizRepo.findRandomQuestionByCategory(category);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(question);

        quizRepo.save(quiz);
        return new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
    }
}
