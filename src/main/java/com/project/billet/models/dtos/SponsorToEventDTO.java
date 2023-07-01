package com.project.billet.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SponsorToEventDTO {
	
	@NotEmpty
	private String id;
	
	@NotEmpty
	private String sponsorName;

}
