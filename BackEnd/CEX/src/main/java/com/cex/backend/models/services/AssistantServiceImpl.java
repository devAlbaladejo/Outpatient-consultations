package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cex.backend.models.dao.IAssistantDAO;
import com.cex.backend.models.entity.Assistant;

@Service
public class AssistantServiceImpl implements IAssistantService{
	
	@Autowired
	private IAssistantDAO assitantDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Assistant> findAll(){
		return (List<Assistant>) assitantDao.findAll(Sort.by(Sort.Direction.ASC,"name"));
	}

	@Override
	@Transactional
	public void save(Assistant assistant) {
		assitantDao.save(assistant);
	}

	@Override
	@Transactional(readOnly = true)
	public Assistant findById(Long id) {
		return assitantDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		assitantDao.deleteById(id);
	}

}
