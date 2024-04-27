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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality", nullable = false)
	@NotNull(message = "Speciality required")
	private Speciality speciality;
	@Column(name = "name", nullable = false)
	@NotNull(message = "Name required")
	private String name;
	@Column(name = "active", precision = 1, scale = 0)
	private int active;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
	@JsonIgnore
	private Set<Listing> lists = new HashSet<Listing>(0);

	public Doctor(int id, Speciality speciality, String name, int active) {
		this.id = id;
		this.speciality = speciality;
		this.name = name;
		this.active = active;
	}
}
