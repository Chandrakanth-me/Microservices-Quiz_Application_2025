package com.telusko.quizapp_by_me.controller;

import com.telusko.quizapp_by_me.model.Cacs;
import com.telusko.quizapp_by_me.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("ques")
public class QuestionController
{
    @Autowired
    QuestionService questionService;

    @GetMapping("all")
    public ResponseEntity<List<Cacs>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Cacs> getCategory(@PathVariable String category)
    {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Cacs question)
    {
        return questionService.addQuestion(question);
    }

}
