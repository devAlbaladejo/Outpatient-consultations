package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cex.backend.models.dao.IDiaryDAO;
import com.cex.backend.models.entity.Diary;

@Service
public class DiaryServiceImpl implements IDiaryService {
	
	@Autowired
	private IDiaryDAO diaryDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Diary> findAll(){
		return (List<Diary>) diaryDao.findAll();
	}

}
