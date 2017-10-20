package mir.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.internal.jpa.EJBQueryImpl;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.DatabaseQuery;
import org.eclipse.persistence.sessions.DatabaseRecord;
import org.eclipse.persistence.sessions.Session;

import mir.airline.model.Airplane;
import mir.airline.model.Flight;
import mir.airline.model.Pilot;

@LocalBean
@Stateless
public class FlightService {
	
	@PersistenceContext(unitName="airline")
	private EntityManager entity;
	
	public void addFlight(Flight f , Airplane a ){
		entity.persist(f);
		entity.persist(a);
	}
	
	public void addPilotToFlight(int pilotId , int flightId ){
		
		TypedQuery<Flight> fQuery = entity.createNamedQuery("Flight.getFlightById", Flight.class);
		fQuery.setParameter("id", flightId);
		Flight flight = fQuery.getSingleResult();
		
//		Session session = entity.unwrap(JpaEntityManager.class).getActiveSession(); 
//		DatabaseQuery databaseQuery = ((EJBQueryImpl)fQuery).getDatabaseQuery(); 
//		databaseQuery.prepareCall(session, new DatabaseRecord());
//
//		String sqlString = databaseQuery.getSQLString();
//		System.out.println(sqlString);
		
		TypedQuery<Pilot> pQuery = entity.createNamedQuery("Pilot.getPilotById", Pilot.class);
		pQuery.setParameter("id", pilotId);
		Pilot pilot = pQuery.getSingleResult();
		
		pilot.setFlight(flight);
		flight.getPilots().add(pilot);

	}
	
	public List<Flight> getAllFlights(){
		TypedQuery<Flight> fQuery = entity.createQuery("SELECT f FROM Flight f", Flight.class);
		return fQuery.getResultList();
		
	}

}
