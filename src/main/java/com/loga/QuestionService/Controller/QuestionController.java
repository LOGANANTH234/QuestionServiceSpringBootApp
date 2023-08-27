package com.loga.QuestionService.Controller;

import com.loga.QuestionService.Entity.ObjectForCreateQuiz;
import com.loga.QuestionService.Entity.Questions;
import com.loga.QuestionService.Entity.onlyAnswer;
import com.loga.QuestionService.Entity.onlyQuestion;
import com.loga.QuestionService.Service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/saveQuestion")
    public List<Questions> save(@RequestBody List<Questions> questions) {
        return questionService.save(questions);
    }

    @GetMapping("/getQuestions")
    public List<Questions> get() {
        return questionService.get();
    }

    @DeleteMapping("/deleteById")
    public Optional<Questions> deleteById(@RequestParam("id") Integer id) {
        return questionService.deleteById(id);
    }

    @PutMapping("/updateQuestionsById")
    public Questions updateQuestion(@RequestBody Questions questions) {
        return questionService.updateById(questions);
    }



        @PostMapping("createQuestionsForQuiz")
    public List<onlyQuestion>createQuestionsForQuiz(@RequestBody   ObjectForCreateQuiz objectForCreateQuiz){

        return questionService.createQuestionsForQuiz(objectForCreateQuiz.getQuizName(),objectForCreateQuiz.getNoOfQuestions(),objectForCreateQuiz.getTopic());
    }

    @PostMapping("/getMarksForQuiz")
    public Long getMarksForQuiz( @RequestParam String id,@RequestBody List<String>answerList){
      return   questionService.getMarksForQuiz(id,answerList);
    }


    @GetMapping("/getAnswerForQuiz")
    public onlyAnswer getAnswerForQuiz(@RequestParam String id){
return  questionService.getAnswerForQuiz(id);
    }
}
