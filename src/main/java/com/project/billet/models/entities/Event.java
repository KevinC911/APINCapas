package com.project.billet.models.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name= "event")
@ToString(exclude = {"relationshipCategory", "relationshipInvolved", "relationshipSponsor"})
public class Event {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "date_hour")
	private Timestamp date_hour;
	
	@Column(name = "duration_min")
	private int duration_min;
	
	@Column(name = "status")
	private boolean status;
	
	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
	@JsonIgnore
	List<EventxCategory> relationshipCategory;
	
	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
	@JsonIgnore
	List<EventxInvolved> relationshipInvolved;
	
	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
	@JsonIgnore
	List<EventxSponsor> relationshipSponsor;

	public Event(String title, String image, Timestamp date_hour, int duration_min, boolean status) {
		super();
		this.title = title;
		this.image = image;
		this.date_hour = date_hour;
		this.duration_min = duration_min;
		this.status = status;
	}
	
	
}
