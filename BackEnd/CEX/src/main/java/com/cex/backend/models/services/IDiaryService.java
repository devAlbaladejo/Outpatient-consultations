package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import com.cex.backend.models.entity.Diary;

public interface IDiaryService {

	public List<Diary> findAll();
}
