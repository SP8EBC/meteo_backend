package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cc.pogoda.backend.types.model.StationDataModel;

public interface StationDataRepo extends Repository<StationDataModel, Integer> {

	List<StationDataModel> findFirst50ByStationOrderByEpochDesc(String station);
	List<StationDataModel> findFirst50ByStationOrderByEpochAsc(String station);
	
	List<StationDataModel> findFirst2000ByStationOrderByEpochDesc(String station);
	List<StationDataModel> findFirst2000ByStationOrderByEpochAsc(String station);
	
	StationDataModel findFirstByStationOrderByEpochDesc(String station);
	
	@Query("Select d from StationDataModel d where d.station = :station and d.epoch  > :from and d.epoch < :to")
	List<StationDataModel> findDataBetweenEpoch(@Param("station") String station, @Param("from")long from, @Param("to")long to);

}
