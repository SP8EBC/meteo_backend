package cc.pogoda.backend.dao;

import java.time.ZonedDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cc.pogoda.backend.dao.repository.PMeteoEventCrudRepo;
import cc.pogoda.backend.types.model.parameteo.PmeteoEventModel;
import cc.pogoda.backend.types.view.parameteo.PmeteoEvent;

@Repository
public class PMeteoEventDao {

	@Autowired
	PMeteoEventCrudRepo repo;
	
	@PersistenceContext
	EntityManager em;
	
	public void insertEvent(PmeteoEvent m, String stationName) {
		
		PmeteoEventModel model = new PmeteoEventModel();
		
		model.stationName = stationName;
		model.source = m.source;
		model.severity = m.severity;
		model.event = m.event;
		model.eventCounterId = m.event_counter_id;
		model.eventMasterTime = m.event_master_time;
		model.lparam = m.lparam;
		model.lparam2 = m.lparam2;
		model.param = m.param;
		model.param2 = m.param2;
		model.wparam = m.wparam;
		model.wparam2 = m.wparam2;
		
		repo.save(model);
		
	}
}
