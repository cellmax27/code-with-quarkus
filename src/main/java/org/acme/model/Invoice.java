package org.acme.model;

import java.time.LocalDateTime;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Invoice extends PanacheEntity {

    //@Column(name = "number")
    private String number;

    //@Column(name = "status")
    private String status;

    //@Column(name = "total_amount")
    private double totalAmount;

    //@DateTimeFormat(pattern = "MM/dd/yyyy")
    //@Column(name = "issued")
    private LocalDateTime issuedDate;

    //@OneToMany(mappedBy = "invoice")
    @OneToMany
    private List<Appointement> appointments;
    
    //@Column(name = "number")
    private String client;
	
//    id:
//        type: string
//    client:
//        type: string
	
	// put your custom logic here as instance methods

	public Invoice findByName(String name) {
		return find("name", name).firstResult();
	}

//   public List<Invoice> findAlive(){
//       return list("status", Status.Alive);
//   }

	public void deleteStefs() {
		delete("name", "Stef");
	}
}
