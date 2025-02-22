package org.acme.model;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Appointement extends PanacheEntity {
//    @Column(name = "start")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime start;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//    @Column(name = "end")
    private LocalDateTime end;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

//    @OneToOne
//    @JoinColumn(name = "id_canceler")
    //private User canceler;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
    private AppointementStatus status;
//
    @ManyToOne
//    @JoinColumn(name = "id_customer")
    private Customer customer;
//
    @ManyToOne
//    @JoinColumn(name = "id_provider")
    private Provider provider;
//
    @ManyToOne
//    @JoinColumn(name = "id_work")
    private Work work;

    //@OneToMany(mappedBy = "appointment")
    //private List<ChatMessage> chatMessages;

    @ManyToOne
//    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    //@OneToOne(mappedBy = "requested", cascade = {CascadeType.ALL})
    //private ExchangeRequest exchangeRequest;
	
	// put your custom logic here as instance methods

	public Appointement findByName(String name) {
		return find("name", name).firstResult();
	}

//   public List<Actor> findAlive(){
//       return list("status", Status.Alive);
//   }

	public void deleteStefs() {
		delete("name", "Stef");
	}
}
