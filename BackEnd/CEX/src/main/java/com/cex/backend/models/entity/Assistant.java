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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "assistant")
public class Assistant implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@NotNull(message = "Name required")
	@Column(name = "name", nullable = false)
	private String name;
	@NotEmpty(message="Color required")
	@Column(name = "color", nullable = false)
	private String color;
	@Column(name = "active", precision = 1, scale = 0)
	private int active;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assistant")
	@JsonIgnore
	private Set<Listing> lists = new HashSet<Listing>(0);

	public Assistant(int id, String name, String color, int active) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.active = active;
	}
}
