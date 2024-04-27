package com.cex.backend.models.entity;

/*
 * @author Ángel Albaladejo Flores
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "users")
public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "email", nullable = false, unique = true)
	@NotNull(message = "Email required")
	private String email;
	@Column(name = "name", nullable = false)
	@NotNull(message = "Name required")
	private String name;
	@Column(name = "password", nullable = false)
	@NotNull(message = "Password required")
	private String password;
	@Column(name = "token")
	private String token;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role", nullable = false)
	@NotNull(message = "Role required")
	private Role role;
}
