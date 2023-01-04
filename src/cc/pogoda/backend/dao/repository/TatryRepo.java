package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.TatryModel;

public interface TatryRepo extends Repository<TatryModel, Integer> {

	List<TatryModel> findFirst5000ByStationOrderByEpochDesc(String station);
	
	TatryModel findFirstByStationOrderByEpochDesc(String station);
}
