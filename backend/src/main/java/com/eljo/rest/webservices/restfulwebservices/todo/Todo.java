package com.eljo.rest.webservices.restfulwebservices.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
@Entity
public class Todo {

	public Todo() {
		
	}
	public Todo(Integer id, String username, String description, LocalDate targetDate, TodoEnum done, String notes) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
		this.notes = notes;
	}
@Id
@GeneratedValue
	private Integer id;
	private String notes;
	private String username;
	private String description;
	private LocalDate targetDate;
	private TodoEnum done;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public String getNotes() { return notes; }

	public void setNotes(String notes) { this.notes = notes; }

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public TodoEnum getDone() {
		return done;
	}

	public void setDone(TodoEnum done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", " +
				" username=" + username +
				", description=" + description +
				", notes=" + notes +
				", targetDate= " + targetDate +
				", done=" + done + "]";
	}

}