package com.project.billet.models.dtos;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventDataDTO {
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String image;
	
	@NotEmpty
	private Timestamp date_hour;
	
	@NotEmpty
	private int duration_min;
	
	@NotEmpty
	private boolean status;

}
