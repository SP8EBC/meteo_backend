package cc.pogoda.backend.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import cc.pogoda.backend.dao.repository.TelemetryRepo;
import cc.pogoda.backend.types.model.TelemetryModel;

@Repository
public class TelemetryDao {

    @Autowired
    private WebApplicationContext context;
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	TelemetryRepo telemetryRepo;
	
	public TelemetryModel getTelemetryFromStationName(String name) {
		TelemetryModel telemetry = null;

		telemetry = telemetryRepo.findTopByStationOrderByIdDesc(name);
		
		return telemetry;
	}
}
