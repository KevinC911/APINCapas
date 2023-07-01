package com.project.billet.models.entities;

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
@Table(name = "category")
@ToString(exclude = {"relationshipCategory"})
public class Category {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	@JsonIgnore
	List<EventxCategory> relationshipCategory;

	public Category(String name) {
		super();
		this.name = name;
	}
	
	
}
