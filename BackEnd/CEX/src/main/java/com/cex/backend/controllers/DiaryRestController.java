package com.cex.backend.controllers;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cex.backend.models.entity.Diary;
import com.cex.backend.models.services.IDiaryService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class DiaryRestController {

	@Autowired
	private IDiaryService diaryService;
	
	@GetMapping("/diaries")
	public List<Diary> showDiaries(){
		return diaryService.findAll();
	}
}
