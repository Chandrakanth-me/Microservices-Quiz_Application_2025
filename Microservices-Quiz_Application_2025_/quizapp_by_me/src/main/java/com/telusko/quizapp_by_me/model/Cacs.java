package com.telusko.quizapp_by_me.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cacs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String dl;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String ques;
    private String rightanswer;
}
