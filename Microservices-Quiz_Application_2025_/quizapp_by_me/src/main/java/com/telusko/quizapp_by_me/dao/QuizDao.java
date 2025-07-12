package com.telusko.quizapp_by_me.dao;

import com.telusko.quizapp_by_me.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
