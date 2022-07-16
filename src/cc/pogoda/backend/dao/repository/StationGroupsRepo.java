package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.StationsGroupModel;

public interface StationGroupsRepo extends Repository<StationsGroupModel, Integer> {

	public List<StationsGroupModel> findAll();
}
