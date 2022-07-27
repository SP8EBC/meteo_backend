package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.StationDefinitionModel;

public interface StationsDefinitionRepo extends Repository<StationDefinitionModel, Integer> {

	@Query("Select d from StationDefinitionModel d")
	public List<StationDefinitionModel> findAll();
	
	public StationDefinitionModel findByName(String name);
}
