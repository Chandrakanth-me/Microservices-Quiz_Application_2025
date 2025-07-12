package com.telusko.quizapp_by_me.dao;

import com.telusko.quizapp_by_me.model.Cacs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Cacs, Integer>
{
   List<Cacs> findByCategory(String category);

   @Query(value = "SELECT * FROM cacs c Where c.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
   List<Cacs> findRandomQuestionsByCategory(String category, int numQ);
}
