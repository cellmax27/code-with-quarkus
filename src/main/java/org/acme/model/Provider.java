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
public class Provider extends PanacheEntity {
	public String name;
	public String profile_path;
	public String character;
	public Long id;
//	
//    id:
//        type: string
//        description: Unique identifier for the provider
//      name:
//        type: string
//        description: Name of the provider
//      contactEmail:
//        type: string
//        description: Contact email of the provider
//      phoneNumber:
//        type: string
//        description: Phone number of the provider
//      address:
//        type: string
//        description: Address of the provider
	
	// put your custom logic here as instance methods

	public Provider findByName(String name) {
		return find("name", name).firstResult();
	}

//   public List<Actor> findAlive(){
//       return list("status", Status.Alive);
//   }

	public void deleteStefs() {
		delete("name", "Stef");
	}
}
