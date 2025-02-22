package org.acme.model;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class Movie extends PanacheEntity {
	public String id;
	
	@NotBlank(message = "Title cannot be blank")
	public String name;
	public LocalDate birth;
//    public Status status;

	// TODO maj API file
	// angular attributes
	public boolean adult; // : boolean;
	public String backdrop_path; // : string;
	public Long[] genre_ids; // : number[];
	public String original_language; // : string;
	public String original_title; // : string;
	public String overview; // : string;
	
	@Min(message = "Author has been very lazy", value = 1)
	public Long popularity; // : number;
	public String poster_path; // : string;
	public LocalDate release_date;// : string;
	public String title; // : string;
	public boolean video; // : boolean;
	public Long vote_average; // : number;
	public Long vote_count; // : number;
//    public Long revenue?; //: number;
//    public String runtime?; //: string;

	// public Long[Genre] genres?; //: any[];

	// csharp
//    public string Director { get; set; }
//    public List<string> Actors { get; set; }
//    public double Rating { get; set; }
//    public string Description { get; set; }
//    public string Language { get; set; }
//    public int Duration { get; set; } // Duration in minutes
//    public string Country { get; set; }
//    public bool IsAvailableOnNetflix { get; set; }

	public static Movie findByName(String name) {
		return find("name", name).firstResult();
	}

	public static Movie findById(String id) {
		return find("id", id).firstResult();
	}

}
