package mir.airline.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import mir.airline.util.PilotRank;

/**
 * Entity implementation class for Entity: Pilot
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name ="Pilot.getPilotById" , query="SELECT p FROM Pilot p WHERE p.id=:id ")
})
public class Pilot implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String surname;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Enumerated(EnumType.ORDINAL)
	private PilotRank rank;
	
	@ManyToOne
	@JoinColumn(name="FLIGHT_ID")
	private Flight flight;
	

	public Pilot() {
		super();
	}
	
	


	public Pilot(String name, String surname, Date dob, PilotRank rank) {
		super();
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.rank = rank;
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


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public PilotRank getRank() {
		return rank;
	}


	public void setRank(PilotRank rank) {
		this.rank = rank;
	}


	public Flight getFlight() {
		return flight;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	
   
}
