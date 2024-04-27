package com.cex.backend.models.dao;

/*
 * @author Ángel Albaladejo Flores
 */

import org.springframework.data.repository.CrudRepository;

import com.cex.backend.models.entity.Diary;

public interface IDiaryDAO extends CrudRepository<Diary, Long> {

}
