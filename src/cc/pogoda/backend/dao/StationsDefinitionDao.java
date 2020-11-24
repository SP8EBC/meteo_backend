package cc.pogoda.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import cc.pogoda.backend.dao.repository.StationsDefinitionRepo;
import cc.pogoda.backend.types.model.StationsDefinition;

@Repository
public class StationsDefinitionDao {

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private StationsDefinitionRepo repo;
	
	@PersistenceContext
	EntityManager em;
	
	public List<StationsDefinition> getListOfAllStations() {
		
		List<StationsDefinition> out = null;
		
		out = repo.findAll();
		
		return out;
	}
}
