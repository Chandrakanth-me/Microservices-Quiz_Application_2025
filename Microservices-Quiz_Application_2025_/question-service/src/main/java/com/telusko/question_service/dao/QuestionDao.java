package com.telusko.question_service.dao;


import com.telusko.question_service.model.Cacs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Cacs, Integer>
{
   List<Cacs> findByCategory(String category);

   @Query(value = "SELECT q.id FROM cacs c Where c.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
   List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}
