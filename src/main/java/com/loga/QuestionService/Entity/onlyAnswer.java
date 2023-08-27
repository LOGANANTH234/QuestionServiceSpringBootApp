package com.loga.QuestionService.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "Answers")
public class onlyAnswer {
    @Id
private String quizName;
    private List<String> answer;
    private List<String> Explanation;
}
