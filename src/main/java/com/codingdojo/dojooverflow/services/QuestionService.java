package com.codingdojo.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.dojooverflow.models.Question;
import com.codingdojo.dojooverflow.repositories.QuestionRepository;

@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository=questionRepository;
	}
	
	public Question createOne(Question question) {
		return questionRepository.save(question);
	}
	
	
	public Question getOne(Long id) {
		Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        } else {
            return null;
        }
	}
	
	public List<Question> getAll(){
		return questionRepository.findAll();
	}
	
	public Question updateOne(Question question) {
		return questionRepository.save(question);
	}
}
