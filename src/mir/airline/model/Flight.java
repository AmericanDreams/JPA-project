package mir.airline.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import mir.airline.util.City;

/**
 * Entity implementation class for Entity: Flight
 *
 */
@Entity
@NamedQuery(name="Flight.getFlightById" , query="SELECT f FROM Flight f WHERE f.id =:id")
public class Flight implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String model;
	private Integer code;
	
	@Enumerated(EnumType.STRING)
	private City fromCity;
	
	@Enumerated(EnumType.STRING)
	private City toCity;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private Integer capacity;
	
	@OneToOne
	@JoinColumn(name="AIRPLANE_ID")
	private Airplane airplane;
	
	@OneToMany(mappedBy="flight")
	private List<Pilot> pilots;
	

	public Flight() {
		super();
	}

	

	public Flight( String model, Integer code, City fromCity,
			City toCity, Date date, Integer capacity) {
		super();
		this.model = model;
		this.code = code;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.date = date;
		this.capacity = capacity;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public City getFromCity() {
		return fromCity;
	}


	public void setFromCity(City fromCity) {
		this.fromCity = fromCity;
	}


	public City getToCity() {
		return toCity;
	}


	public void setToCity(City toCity) {
		this.toCity = toCity;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Integer getCapacity() {
		return capacity;
	}


	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}


	public Airplane getAirplane() {
		return airplane;
	}


	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}



	public List<Pilot> getPilots() {
		return pilots;
	}



	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}



	@Override
	public String toString() {
		return "Flight [id=" + id + ", model=" + model + ", code=" + code
				+ ", fromCity=" + fromCity + ", toCity=" + toCity + ", date="
				+ date + ", capacity=" + capacity + ", airplane=" + airplane
				+ ", pilots=" + pilots + "]";
	}
	
	
	
	
   
}
