package org.acme.model;

import java.util.Date;

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
public class Order extends PanacheEntity {
	public Long id;
	public String name;
	public Long petId;
	public Integer quantity;
	public Date shipDate;
	public Date date;
}
