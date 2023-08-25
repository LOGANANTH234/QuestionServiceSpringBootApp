package com.loga.QuestionService.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Questions")

@Data

@AllArgsConstructor

public class Questions {
    @Id
    private Integer id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String Explanation;
    private String topic;
    private String answer;


}
