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
@Table(name = "role")
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	@JsonIgnore
	private Set<Users> user = new HashSet<Users>(0);

	public Role(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
