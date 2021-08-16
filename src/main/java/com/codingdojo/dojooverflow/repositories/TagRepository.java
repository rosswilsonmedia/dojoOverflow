package com.codingdojo.dojooverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.dojooverflow.models.Tag;

@Repository
public interface TagRepository extends CrudRepository <Tag, Long> {
	List<Tag> findAll();
	Boolean existsBySubject(String subject);
	Tag findOneBySubject(String subject);
}
