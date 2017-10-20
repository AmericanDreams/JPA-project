package mir.airline.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mir.airline.model.Passenger;

@LocalBean
@Stateless
public class PassengerService {
	
	@PersistenceContext(unitName = "airline")
	private EntityManager entity;
	
	
	public void addPassenger(Passenger p){
		entity.persist(p);
	}

}
