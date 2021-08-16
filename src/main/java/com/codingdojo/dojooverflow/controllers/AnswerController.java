package com.codingdojo.dojooverflow.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.dojooverflow.models.Answer;
import com.codingdojo.dojooverflow.models.Question;
import com.codingdojo.dojooverflow.services.AnswerService;
import com.codingdojo.dojooverflow.services.QuestionService;

@Controller
public class AnswerController {
	private final QuestionService questionService;
	private final AnswerService answerService;
	
	public AnswerController(QuestionService questionService, AnswerService answerService) {
		this.questionService=questionService;
		this.answerService=answerService;
	}

	
	@RequestMapping("/questions/{id}")
	public String viewOrAddAnswer(@ModelAttribute("answer") Answer answer, @PathVariable("id") Long id, Model model) {
		Question question = questionService.getOne(id);
		if(question==null) {
			return "redirect:/questions";
		}
		model.addAttribute("question", question);
		return "/answers/new.jsp";
	}
	
	@PostMapping("/questions/{id}/answer")
	public String addAnswer(@PathVariable("id") Long id, @RequestParam("answer") String answer, RedirectAttributes redirectAttributes) {
		if(answer==null || answer=="") {
			redirectAttributes.addFlashAttribute("questionError", "*must have a valid answer");
			return "redirect:/questions/"+id;
		}
		
		Answer newAnswer = new Answer();
		newAnswer.setAnswer(answer);
		Question question = questionService.getOne(id);
		newAnswer.setQuestion(question);
		answerService.createOne(newAnswer);
		
		
		return "redirect:/questions/"+id;
	}
}
