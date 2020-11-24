package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.StationData;

public interface StationDataRepo extends Repository<StationData, Integer> {

	List<StationData> findFirst50ByStationOrderByEpochDesc(String station);
}
