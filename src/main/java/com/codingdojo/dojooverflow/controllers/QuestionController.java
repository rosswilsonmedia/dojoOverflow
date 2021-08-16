package com.codingdojo.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.dojooverflow.models.Question;
import com.codingdojo.dojooverflow.models.Tag;
import com.codingdojo.dojooverflow.services.QuestionService;
import com.codingdojo.dojooverflow.services.TagService;

@Controller
public class QuestionController {
	private final QuestionService questionService;
	private final TagService tagService;
	
	public QuestionController(QuestionService questionService, TagService tagService) {
		this.questionService=questionService;
		this.tagService=tagService;
	}
	
	@RequestMapping("/questions")
	public String getQuestions(Model model) {
		model.addAttribute("questions", questionService.getAll());
		return "/questions/index.jsp";
	}
	
	@RequestMapping("/questions/new")
	public String addNewQuestion() {
		return "/questions/new.jsp";
	}
	
	@PostMapping("/questions")
	public String addQuestionToDb(@RequestParam("question") String question, @RequestParam("tags") String tags, RedirectAttributes redirectAttributes) {
		if(question==null || question=="") {
			redirectAttributes.addFlashAttribute("questionError", "*must have a question");
			return "redirect:/questions/new";
		}
		
		String[] tagsArr = tags.toLowerCase().split(",");
		if(tagsArr.length>3) {
			redirectAttributes.addFlashAttribute("questionError", "*must have 3 tags or less");
			return "redirect:/questions/new";
		}
		
		
		List<Tag> tagListForQuestion = new ArrayList<Tag>();
		for(String tagStr:tagsArr) {
			String subject = tagStr.trim();
			if(subject!="" && !tagService.existsBySubject(subject)) {
				Tag tag = new Tag();
				tag.setSubject(subject);
				Tag createdTag = tagService.createOne(tag);
				tagListForQuestion.add(createdTag);
			} else if(tagService.existsBySubject(subject)) {
				Tag exisitingTag = tagService.findOneBySubject(subject);
				tagListForQuestion.add(exisitingTag);
			}
		}
		
		Question questionToBeAdded = new Question();
		questionToBeAdded.setQuestion(question);
		questionToBeAdded.setTags(tagListForQuestion);
		questionService.createOne(questionToBeAdded);
		
		
		return "redirect:/questions";
	}
}
