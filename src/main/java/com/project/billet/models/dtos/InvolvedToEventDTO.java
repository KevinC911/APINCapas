package com.project.billet.models.dtos;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvolvedToEventDTO {
	@NotEmpty
	private String id;
	
	@NotEmpty
	private String involvedName;
}
