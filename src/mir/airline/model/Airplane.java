package mir.airline.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Airplane
 *
 */
@Entity

public class Airplane implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	@OneToOne(mappedBy="airplane")
	private Flight flight;
	

	public Airplane() {
		super();
	}
	
	


	public Airplane(String name) {
		super();
		this.id = id;
		this.name = name;
	}




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Flight getFlight() {
		return flight;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
	}




	@Override
	public String toString() {
		return "Airplane [id=" + id + ", name=" + name + " ]";
	}
	
   
}
