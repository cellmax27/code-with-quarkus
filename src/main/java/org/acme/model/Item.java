package org.acme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@Setter
@NoArgsConstructor // Génère un constructeur sans arguments @AllArgsConstructor
@AllArgsConstructor // Génère un constructeur avec tous les arguments public class Item
@Entity
//@Table(name="STUDENT")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;

	// Getters et Setters
	public Item() {
		// TODO Auto-generated constructor stub
	}
	public Item(long l, String name, String description) {
		// TODO Auto-generated constructor stub
		this.id = l;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
