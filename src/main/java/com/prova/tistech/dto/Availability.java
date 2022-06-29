package com.prova.tistech.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Availability extends BaseDto{

	private String description;
	
	private Room room;
	
	@JsonFormat(pattern = "yyyy/MM/dd")
	private LocalDate data;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
}
