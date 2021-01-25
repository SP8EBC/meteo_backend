package cc.pogoda.backend.dao.repository;

import org.springframework.data.repository.Repository;

import cc.pogoda.backend.types.model.Telemetry;

public interface TelemetryRepo extends Repository<Telemetry, Integer> {

	Telemetry findTopByStationOrderByIdDesc(String station);
}
