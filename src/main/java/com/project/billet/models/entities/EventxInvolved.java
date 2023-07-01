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
@Table(name = "eventxinvolved")
public class EventxInvolved {
	
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@ManyToOne
	@JoinColumn(name = "id_event")
	Event event;
	
	@ManyToOne
	@JoinColumn(name = "id_involved")
	Involved involved;

	public EventxInvolved(Event event, Involved involved) {
		super();
		this.event = event;
		this.involved = involved;
	}
	
	

}
