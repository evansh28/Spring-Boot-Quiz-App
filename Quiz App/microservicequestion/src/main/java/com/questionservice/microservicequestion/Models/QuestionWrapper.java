package com.questionservice.microservicequestion.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {
    
    private Integer id;

    private String questionTitle;
    private String options1;
    private String options2;
    private String options3;
    private String options4;

}
