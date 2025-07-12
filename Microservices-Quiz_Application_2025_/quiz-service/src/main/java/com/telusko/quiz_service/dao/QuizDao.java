package com.telusko.quiz_service.dao;

import com.telusko.quiz_service.model.Quiz;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
