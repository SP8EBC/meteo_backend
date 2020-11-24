package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.StationDefinition;

public interface StationsDefinitionRepo extends Repository<StationDefinition, Integer> {

	@Query("Select d from StationsDefinition d")
	public List<StationDefinition> findAll();
}
