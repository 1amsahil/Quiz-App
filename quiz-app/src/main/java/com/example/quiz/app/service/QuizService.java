package com.example.quiz.app.service;

import com.example.quiz.app.Repo.QuizRepo;
import com.example.quiz.app.entity.Question;
import com.example.quiz.app.entity.Quiz;
import com.example.quiz.app.entity.QuizQuestions;
import com.example.quiz.app.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<?> getQuiz(int id)
    {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question>  questionsFromDB = quiz.get().getQuestions();

        List<QuizQuestions> quizQuestions = new ArrayList<>();
        for(Question q : questionsFromDB)
        {
            QuizQuestions ques = new QuizQuestions(q.getId(), q.getQuestionTitle(),
                    q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());

            quizQuestions.add(ques);
        }

        return new ResponseEntity<>(quizQuestions,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculate(Integer id, List<Response> responses) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Question> ques = quiz.getQuestions();

        System.out.println(ques);
        System.out.println("Response : - "+responses);

        int right = 0;
        int index = 0;
        for(Response response : responses)
        {
            if(response.getResponse().equals(ques.get(index).getRightAnswer()))
            {
                right++;
            }
            index++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
