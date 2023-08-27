package com.loga.QuestionService.Service;

import com.loga.QuestionService.Entity.Questions;
import com.loga.QuestionService.Entity.onlyAnswer;
import com.loga.QuestionService.Entity.onlyQuestion;
import com.loga.QuestionService.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class QuestionService {

    int i;
    @Autowired
  private MongoTemplate mongoTemplate;
    @Autowired
  private QuestionRepository questionRepository;

    public List<Questions> save(List<Questions> questions) {
        return questionRepository.saveAll(questions);
    }

    public List<Questions> get() {
        return questionRepository.findAll();
    }

    public Optional<Questions> deleteById(Integer id) {
        Optional<Questions> send = findByID(id);
        questionRepository.deleteById(id);
        return send;
    }

    private Optional<Questions> findByID(Integer id) {
        return questionRepository.findById(id);
    }

    public Questions updateById(Questions questions) {
        return questionRepository.save(questions);
    }



    public List<onlyQuestion> createQuestionsForQuiz(String quizName, Integer noOfQuestions, String topic) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("topic").is(topic));
        SampleOperation sampleOperation = Aggregation.sample(noOfQuestions);
        Aggregation questionAggregation = Aggregation.newAggregation(matchOperation, sampleOperation);
        List<Questions> questionsList = mongoTemplate.aggregateStream(questionAggregation, "Questions", Questions.class).toList();
        if (!questionsList.isEmpty()) {
            List<String>anwer= new ArrayList<>();
            List<String> explanation= new ArrayList<>();
  List<onlyQuestion> onlyQuestionList= questionsList.stream().map(questions -> { explanation.add(questions.getExplanation());anwer.add(questions.getAnswer());
    return   new  onlyQuestion(questions.getQuestion(),questions.getOption1(),questions.getOption2(),questions.getOption3(),questions.getOption4());}).toList();
  mongoTemplate.save(new onlyAnswer(quizName,anwer,explanation));
  return onlyQuestionList;
        }
        return null;
    }



//
    public Long getMarksForQuiz(String id,List<String> answerList) {
       onlyAnswer onlyanswer= mongoTemplate.findById(id, onlyAnswer.class);

        if(onlyanswer !=null){
        Long mark= answerList.stream().filter(a->a.equals(onlyanswer.getAnswer().get(i++))).count();
        i=0;
        return mark;
        }

return null;

    }
    public Long getMarksForQuiz(String id) {

        return 0L;

    }

    public onlyAnswer getAnswerForQuiz(String id) {

  return mongoTemplate.findById(id, onlyAnswer.class);

    }
}
