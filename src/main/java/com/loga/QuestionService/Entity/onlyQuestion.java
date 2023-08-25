package com.loga.QuestionService.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor
public class onlyQuestion {
    private Integer id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String topic;
    public onlyQuestion( String question, String option1, String option2, String option3, String option4) {

        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;

    }


}
