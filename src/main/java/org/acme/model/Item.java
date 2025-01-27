package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor 
@ToString
@EqualsAndHashCode(callSuper=false)
@Entity
//@Table(name="STUDENT")
public class Item extends PanacheEntity {
	private Long id;
	private String name;
	private String description;

//	// Getters et Setters
//	public Item() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public Item(long l, String name, String description) {
//		// TODO Auto-generated constructor stub
//		this.id = l;
//		this.name = name;
//		this.description = description;
//	}
}
