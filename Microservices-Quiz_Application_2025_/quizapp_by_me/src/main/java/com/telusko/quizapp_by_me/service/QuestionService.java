package com.telusko.quizapp_by_me.service;

import com.telusko.quizapp_by_me.model.Cacs;
import com.telusko.quizapp_by_me.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    public Object addQuestion;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Cacs>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    public List<Cacs> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String addQuestion(Cacs question) {
        questionDao.save(question);
        return "success";
    }
}
