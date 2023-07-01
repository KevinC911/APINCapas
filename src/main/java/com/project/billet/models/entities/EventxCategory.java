package com.project.billet.models.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@Entity
@Table(name = "eventxcategory")
public class EventxCategory {
	
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@ManyToOne
	@JoinColumn(name = "id_event")
	Event event;
	
	@ManyToOne
	@JoinColumn(name = "id_category")
	Category category;

	public EventxCategory(Event event, Category category) {
		super();
		this.event = event;
		this.category = category;
	}
	
}
