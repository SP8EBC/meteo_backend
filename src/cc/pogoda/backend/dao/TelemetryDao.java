package cc.pogoda.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import cc.pogoda.backend.dao.repository.TelemetryRepo;
import cc.pogoda.backend.types.model.Telemetry;

@Repository
public class TelemetryDao {

    @Autowired
    private WebApplicationContext context;
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	TelemetryRepo telemetryRepo;
	
	public Telemetry getTelemetryFromStationName(String name) {
		Telemetry telemetry = null;

		telemetry = telemetryRepo.findTopByAliasOrderByIdDesc("Skrzyczne");
		
		return telemetry;
	}
}
