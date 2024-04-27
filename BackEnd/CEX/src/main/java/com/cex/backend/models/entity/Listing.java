package com.cex.backend.models.entity;

/*
 * @author Ángel Albaladejo Flores
 */

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "listing", uniqueConstraints = @UniqueConstraint(columnNames = { "room", "date", "schedule" }))
public class Listing implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assistant")
	private Assistant assistant;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diary")
	private Diary diary;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor", nullable = false)
	@NotNull(message = "Doctor required")
	private Doctor doctor;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room", nullable = false)
	@NotNull(message = "Room required")
	private Room room;
	@Column(name = "doctor_comment")
	private String doctorComment;
	@Column(name = "assistant_comment")
	private String assistantComment;
	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 13)
	@NotNull(message = "Date required")
	private Date date;
	@Column(name = "schedule", nullable = false)
	@NotNull(message = "Schedule required")
	private String schedule;

	public Listing(int id, Assistant assistant, Diary diary, Doctor doctor, Room room, Date date, String schedule) {
		this.id = id;
		this.assistant = assistant;
		this.diary = diary;
		this.doctor = doctor;
		this.room = room;
		this.date = date;
		this.schedule = schedule;
	}
}
