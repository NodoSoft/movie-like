package cl.everis.beca.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="movies")
public class Movie {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private int year;
	
	@Column
    private String description;
	
	@Column
	private String trailer;
	
	@Column
	private String imagen;
	
	@Column
	private int activa;

	@Column
	private  int likes;
    


	
}
