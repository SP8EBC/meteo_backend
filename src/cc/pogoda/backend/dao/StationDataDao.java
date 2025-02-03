package cc.pogoda.backend.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import cc.pogoda.backend.dao.repository.StationDataRepo;
import cc.pogoda.backend.types.model.StationDataModel;
import cc.pogoda.backend.types.view.ListOfStationData;

@Repository
public class StationDataDao {

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private StationDataRepo repo;
	
	@PersistenceContext
	EntityManager em;
	
	public StationDataModel getCurrentStationData(String name) {
		return repo.findFirstByStationOrderByEpochDesc(name);
	}
	
	public List<StationDataModel> getStationDataPerName(String name) {
		
		List<StationDataModel> out;
				
		out = repo.findFirst50ByStationOrderByEpochDesc(name);
		
		return out;
	}
	
	public List<StationDataModel> getStationDataPerName(String name, boolean ascendingOrder, boolean isLong) {
		
		List<StationDataModel> out = null;
		
		if (ascendingOrder == false && isLong == false)
			out = repo.findFirst50ByStationOrderByEpochDesc(name);
		else if (ascendingOrder == true && isLong == false)
			out = repo.findFirst50ByStationOrderByEpochAsc(name);
		else if (ascendingOrder == false && isLong == true)
			out = repo.findFirst2000ByStationOrderByEpochDesc(name);
		else if (ascendingOrder == true && isLong == true)
			out = repo.findFirst2000ByStationOrderByEpochAsc(name);


		return out;
	}
	
	public List<StationDataModel> getStationData(String name, long timestampFrom, long timestampTo) {
		List<StationDataModel> out = null;
		
		out = repo.findDataBetweenEpoch(name, timestampFrom, timestampTo);
		
		return out;
	}
}
