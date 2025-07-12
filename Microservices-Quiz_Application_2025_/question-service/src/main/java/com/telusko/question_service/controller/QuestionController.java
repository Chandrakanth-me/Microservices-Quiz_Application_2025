package com.telusko.question_service.controller;

import com.telusko.question_service.model.Cacs;
import com.telusko.question_service.model.QuestionWrapper;
import com.telusko.question_service.model.Response;
import com.telusko.question_service.service.QuestionService;
import org.hibernate.cfg.Environment;
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

     @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions)
     {
         return questionService.getQuestionsForQuiz(categoryName,numQuestions);
     }

     @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestParam List<Integer> questionIds)
     {

         return questionService.getQuestionsFromId(questionIds);
     }

     @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
     {
         return questionService.getScore(responses);
     }


}
