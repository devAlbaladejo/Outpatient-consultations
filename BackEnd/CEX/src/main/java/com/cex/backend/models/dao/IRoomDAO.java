package com.cex.backend.models.dao;

/*
 * @author Ángel Albaladejo Flores
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.backend.models.entity.Room;

public interface IRoomDAO extends JpaRepository<Room, Long>{

}
