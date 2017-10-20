package mir.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mir.airline.model.Pilot;

@LocalBean
@Stateless
public class PilotService {
	
	@PersistenceContext(unitName="airline")
	private EntityManager entity;
	
	public void addPilot(Pilot p){
		entity.persist(p);
	}
	
	public List<Pilot> getAllPilots(){
		Query pQuery = entity.createNativeQuery("select * from PILOT", Pilot.class);
		List<Pilot> list = pQuery.getResultList();
		return list;
		
	}
	
	public Pilot getPilotById(int id) throws RuntimeException{
		
		TypedQuery<Pilot> pQuery = entity.createNamedQuery("Pilot.getPilotById" , Pilot.class);
		pQuery.setParameter("id", id);
		Pilot p = pQuery.getSingleResult();
		return p;
	}
	
	public void editPilotById(int id , Pilot newPilot) throws RuntimeException{
		Pilot pilot = entity.find(Pilot.class, id);
		
			pilot.setName(newPilot.getName());
			pilot.setSurname(newPilot.getSurname());
			pilot.setDob(newPilot.getDob());
			pilot.setRank(newPilot.getRank());
		
	}
	
	public void deletePilotById(int id) throws RuntimeException{
		Pilot pilot = entity.find(Pilot.class, id);
		entity.remove(pilot);
	}

}
