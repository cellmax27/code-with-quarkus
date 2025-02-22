package org.acme.model;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
public class WorkPlan extends PanacheEntity {

//    @Id
//    @Column(name = "id_provider")
    private int id;

//    @MapsId
    @OneToOne
//    @JoinColumn(name = "id_provider")
    private Provider provider;

    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(columnDefinition = "json", name = "monday")
    private DayPlan monday;

@JdbcTypeCode(SqlTypes.JSON)
//    @Column(columnDefinition = "json", name = "tuesday")
    private DayPlan tuesday;

@JdbcTypeCode(SqlTypes.JSON)
//    @Column(columnDefinition = "json", name = "wednesday")
    private DayPlan wednesday;

@JdbcTypeCode(SqlTypes.JSON)
//    @Column(columnDefinition = "json", name = "thursday")
    private DayPlan thursday;

@JdbcTypeCode(SqlTypes.JSON)
//    @Column(columnDefinition = "json", name = "friday")
    private DayPlan friday;

@JdbcTypeCode(SqlTypes.JSON)
//    @Column(columnDefinition = "json", name = "saturday")
    private DayPlan saturday;

@JdbcTypeCode(SqlTypes.JSON)
//    @Column(columnDefinition = "json", name = "sunday")
    private DayPlan sunday;
	
	// put your custom logic here as instance methods

	public WorkPlan findByName(String name) {
		return find("name", name).firstResult();
	}

//   public List<Actor> findAlive(){
//       return list("status", Status.Alive);
//   }

	public void deleteStefs() {
		delete("name", "Stef");
	}
}
