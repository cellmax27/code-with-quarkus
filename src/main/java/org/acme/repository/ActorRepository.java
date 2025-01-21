package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

import org.acme.model.Actor;


@ApplicationScoped
public class ActorRepository implements PanacheRepository<Actor> {

   // put your custom logic here as instance methods

   public Actor findByName(String name){
       return find("name", name).firstResult();
   }

//   public List<Actor> findAlive(){
//       return list("status", Status.Alive);
//   }

   public void deleteStefs(){
       delete("name", "Stef");
  }
}
