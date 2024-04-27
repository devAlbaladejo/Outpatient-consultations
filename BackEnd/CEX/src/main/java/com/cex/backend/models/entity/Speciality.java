package com.cex.backend.models.entity;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "speciality")
public class Speciality implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "code", unique = true, nullable = false)
	private String code;
	@Column(name = "name", nullable = false)
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality")
	@JsonIgnore
	private Set<Doctor> doctors = new HashSet<Doctor>(0);

	public Speciality(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
