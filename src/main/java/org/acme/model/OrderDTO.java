package org.acme.model;

import java.util.Date;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class OrderDTO extends PanacheEntity {
	public Long id;
	public String name;
	public Long petId;
	public Integer quantity;
	public Date shipDate;
}
