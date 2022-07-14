package cc.pogoda.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import cc.pogoda.backend.dao.repository.StationGroupsRepo;
import cc.pogoda.backend.types.model.StationsGroup;

@Repository
public class StationsGroupDao {

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private StationGroupsRepo repo;
	
	@PersistenceContext
	EntityManager em;
	
    private static Logger logger = LogManager.getLogger();
	
	public List<StationsGroup> getAll() {
		logger.debug("[StationsGroupDao][getAll]");
		return repo.findAll();
	}
}
