package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

/**
 * Example JPA entity defined as a Panache Entity. An ID field of Long type is
 * provided, if you want to define your own ID field extends
 * <code>PanacheEntityBase</code> instead.
 *
 * This uses the active record pattern, you can also use the repository pattern
 * instead: .
 *
 * Usage (more example on the documentation)
 *
 * {@code
 *     public void doSomething() {
 *         Movie entity1 = new Movie();
 *         entity1.field = "field-1";
 *         entity1.persist();
 *
 *         List<Movie> entities = Movie.listAll(); } }
 */
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
