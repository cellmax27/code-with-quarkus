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
@NoArgsConstructor // Génère un constructeur sans arguments @AllArgsConstructor
@AllArgsConstructor // Génère un constructeur avec tous les arguments public class Item
@ToString
@EqualsAndHashCode
@Entity
public class Actor extends PanacheEntity {
	public String name;
	public String profile_path;
	public String character;
	public Long id;
	
	// put your custom logic here as instance methods

	public Actor findByName(String name) {
		return find("name", name).firstResult();
	}

//   public List<Actor> findAlive(){
//       return list("status", Status.Alive);
//   }

	public void deleteStefs() {
		delete("name", "Stef");
	}
}
