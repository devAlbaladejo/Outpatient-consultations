package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cex.backend.models.dao.IUserDAO;
import com.cex.backend.models.entity.Users;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDAO userDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Users> findAll(){
		return (List<Users>) userDao.findAll();
	}
	
	@Override
	@Transactional
	public void save(Users user) {
		userDao.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public Users findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		userDao.deleteById(id);
	}
}