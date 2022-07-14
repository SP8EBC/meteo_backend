package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.StationsGroup;

public interface StationGroupsRepo extends Repository<StationsGroup, Integer> {

	public List<StationsGroup> findAll();
}
