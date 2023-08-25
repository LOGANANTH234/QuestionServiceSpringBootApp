package com.loga.QuestionService.Controller;
import com.loga.QuestionService.Entity.Questions;
import com.loga.QuestionService.Entity.dao;
import com.loga.QuestionService.Entity.onlyAnswer;
import com.loga.QuestionService.Entity.onlyQuestion;
import com.loga.QuestionService.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
@Autowired
    Environment environment;
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

    @GetMapping("getQuestionsByIds")
    public List<onlyQuestion>questionsList(@RequestBody List<Integer>integerList){
        return questionService.getQuestionsList(integerList);
    }

        @PostMapping("createQuestionsForQuiz")
    public List<onlyQuestion>createQuestionsForQuiz(@RequestBody  dao d){

        return questionService.createQuestionsForQuiz(d.getQuizName(),d.getNoOfQuestions(),d.getTopic());
    }
    @GetMapping("getAnswersByIds")
    public List<onlyAnswer>getAnswerList(){
        return questionService.getAnswerList();
    }
}
