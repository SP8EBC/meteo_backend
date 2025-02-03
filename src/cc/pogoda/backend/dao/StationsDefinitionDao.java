package cc.pogoda.backend.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import cc.pogoda.backend.dao.repository.StationsDefinitionRepo;
import cc.pogoda.backend.types.model.StationDefinitionModel;

@Repository
public class StationsDefinitionDao {

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private StationsDefinitionRepo repo;
	
	@PersistenceContext
	EntityManager em;
	
	public List<StationDefinitionModel> getListOfAllStations() {
		
		List<StationDefinitionModel> out = null;
		
		out = repo.findAll();
		
		return out;
	}
	
	public StationDefinitionModel getStationByName(String name) {
		
		return repo.findByName(name);
	}
} 
