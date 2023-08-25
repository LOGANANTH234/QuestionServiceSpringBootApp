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
import java.util.*;
import java.util.stream.Collectors;


@Service
public class QuestionService {
   List<Integer> integerList;
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

    public List<onlyQuestion> getQuestionsList(List<Integer> integerList) {
      return    integerList.stream().filter(a->findByID(a).isPresent()).map(a->{return  new onlyQuestion(findByID(a).get().getQuestion(),findByID(a).get().getOption1(),findByID(a).get().getOption2(),findByID(a).get().getOption3(),findByID(a).get().getOption4());
      }).collect(Collectors.toList());
    }
    public List<onlyAnswer> getAnswerList(  ) {
        return    integerList.stream().filter(a->findByID(a).isPresent()).map(a->new onlyAnswer(findByID(a).get().getExplanation(),findByID(a).get().getAnswer())).collect(Collectors.toList());
    }

    public List<onlyQuestion> createQuestionsForQuiz(String quizName, Integer noOfQuestions, String topic) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("topic").is(topic));
        SampleOperation sampleOperation = Aggregation.sample(noOfQuestions);
        Aggregation questionAggregation = Aggregation.newAggregation(matchOperation, sampleOperation);
        List<Questions> questionsList = mongoTemplate.aggregateStream(questionAggregation, "Questions", Questions.class).toList();
        if (!questionsList.isEmpty()) {
          return  getQuestionsList(integerList(questionsList));
        }
        return null;
    }

    public List<Integer> integerList(List<Questions> questionsList){
        integerList=questionsList.stream().map(Questions::getId).collect(Collectors.toList());
return integerList;

    }


}
