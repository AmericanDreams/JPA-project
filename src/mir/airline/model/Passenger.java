package mir.airline.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import mir.airline.util.FlightClass;
import mir.airline.util.Gender;

/**
 * Entity implementation class for Entity: Passenger
 *
 */
@Entity
public class Passenger implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String surname;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	private FlightClass flightClass;
	
	
	public Passenger() {
		super();
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


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public FlightClass getFlightClass() {
		return flightClass;
	}


	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}
	
	
   
}
