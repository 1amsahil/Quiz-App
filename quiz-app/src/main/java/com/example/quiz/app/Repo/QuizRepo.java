package com.example.quiz.app.Repo;

import com.example.quiz.app.entity.Question;
import com.example.quiz.app.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {

    @Query(value = "SELECT * FROM question q Where category=:category", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category);

}
