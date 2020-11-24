package cc.pogoda.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import cc.pogoda.backend.dao.repository.StationDataRepo;
import cc.pogoda.backend.types.model.StationData;
import cc.pogoda.backend.types.view.ListOfStationData;

@Repository
public class StationDataDao {

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private StationDataRepo repo;
	
	@PersistenceContext
	EntityManager em;
	
	public List<StationData> getStationDataPerName(String name) {
		
		List<StationData> out;
				
		out = repo.findFirst50ByStationOrderByEpochDesc(name);
		
		return out;
	}
}
