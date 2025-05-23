package com.example.quiz.app.Repo;

import com.example.quiz.app.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>
{
    List<Question> findByCategory(String category);
}
