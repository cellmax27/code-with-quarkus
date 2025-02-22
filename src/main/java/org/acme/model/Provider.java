package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@NoArgsConstructor 
//@AllArgsConstructor 
//@ToString
//@EqualsAndHashCode(callSuper=false)
@Entity
public class Provider extends PanacheEntity {
	public String name;
	public String profile_path;
	public String character;
	public Long id;
      public String contactEmail;
      public String phoneNumber;
    public String   address;
	
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
