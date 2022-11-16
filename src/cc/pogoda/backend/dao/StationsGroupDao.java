package cc.pogoda.backend.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import cc.pogoda.backend.dao.repository.StationGroupsRepo;
import cc.pogoda.backend.types.model.StationGroupsBondingsModel;
import cc.pogoda.backend.types.model.StationsGroupModel;

@Repository
public class StationsGroupDao {

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private StationGroupsRepo repo;
	
	@PersistenceContext
	EntityManager em;
	
    private static Logger logger = LogManager.getLogger();
	
	public List<StationsGroupModel> getAll() {
		List<StationsGroupModel> out = new LinkedList<>();
		
		TypedQuery<StationsGroupModel> query = em.createQuery("SELECT m FROM StationsGroupModel m LEFT JOIN m.localeList", StationsGroupModel.class);
		
		out = query.getResultList();
		
		logger.debug("[StationsGroupDao][getAll][out.size() = " + out.size() + "]");
		
		return out;
	}
	
	public List<StationGroupsBondingsModel> getAllBondingsForGroup(int id) {
		
		List<StationGroupsBondingsModel> out = new LinkedList<>();
		
		TypedQuery<StationGroupsBondingsModel> query = em.createQuery("SELECT m FROM StationGroupsBondingsModel m WHERE m.groupId = " + id, StationGroupsBondingsModel.class);
		
		out = query.getResultList();
		
		return out;
	}
}
