package com.cex.backend.models.services;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cex.backend.models.dao.IRoleDAO;
import com.cex.backend.models.entity.Role;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRoleDAO roleDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll(){
		return (List<Role>) roleDao.findAll();
	}	

}
