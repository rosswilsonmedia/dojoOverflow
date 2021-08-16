package com.codingdojo.dojooverflow.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.dojooverflow.models.Tag;
import com.codingdojo.dojooverflow.repositories.TagRepository;

@Service
public class TagService {

	private final TagRepository tagRepository;
	
	public TagService(TagRepository tagRepository) {
		this.tagRepository=tagRepository;
	}
	
	public Tag createOne(Tag tag) {
		return tagRepository.save(tag);
	}
	
	
	public Tag getOne(Long id) {
		Optional<Tag> optionalTag = tagRepository.findById(id);
        if(optionalTag.isPresent()) {
            return optionalTag.get();
        } else {
            return null;
        }
	}
	
	public Boolean existsBySubject(String subject) {
		return tagRepository.existsBySubject(subject);
	}
	
	public Tag findOneBySubject(String subject) {
		return tagRepository.findOneBySubject(subject);
	}

	
	
}
