package cc.pogoda.backend.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cc.pogoda.backend.types.model.StationData;

public interface StationDataRepo extends Repository<StationData, Integer> {

	List<StationData> findFirst50ByStationOrderByEpochDesc(String station);
	List<StationData> findFirst50ByStationOrderByEpochAsc(String station);
	
	List<StationData> findFirst2000ByStationOrderByEpochDesc(String station);
	List<StationData> findFirst2000ByStationOrderByEpochAsc(String station);
	
	@Query("Select d from StationData d where d.station = :station and d.epoch  > :from and d.epoch < :to")
	List<StationData> findDataBetweenEpoch(@Param("station") String station, @Param("from")long from, @Param("to")long to);

}
