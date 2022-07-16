package cc.pogoda.backend.dao.repository;

import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.TelemetryModel;

public interface TelemetryRepo extends Repository<TelemetryModel, Integer> {

	TelemetryModel findTopByStationOrderByIdDesc(String station);
}
