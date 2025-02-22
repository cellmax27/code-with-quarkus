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
public class Work extends PanacheEntity {

    //@Column(name = "name")
    private String name;

    //@Column(name = "description")
    private String description;

    //@Column(name = "price")
    private double price;

    //@Column(name = "duration")
    private int duration;

    //@Column(name = "editable")
    private boolean editable;

    //@Column(name = "target")
    private String targetCustomer;

    //@ManyToMany
    //@JoinTable(name = "works_providers", joinColumns = @JoinColumn(name = "id_work"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    //private List<User> providers;
	
	// put your custom logic here as instance methods

	public Work findByName(String name) {
		return find("name", name).firstResult();
	}

//   public List<Actor> findAlive(){
//       return list("status", Status.Alive);
//   }

	public void deleteStefs() {
		delete("name", "Stef");
	}
}
