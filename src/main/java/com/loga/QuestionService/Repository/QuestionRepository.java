package com.loga.QuestionService.Repository;

import com.loga.QuestionService.Entity.Questions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Questions, Integer> {
}
