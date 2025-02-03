package cc.pogoda.backend.dao;

import java.time.ZonedDateTime;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cc.pogoda.backend.dao.repository.PMeteoMeasurementCrudRepo;
import cc.pogoda.backend.types.model.parameteo.PmeteoMeasurementsModel;
import cc.pogoda.backend.types.view.parameteo.PmeteoMeasurements;

@Repository
public class PMeteoMeasurementsDao {

	@Autowired
	PMeteoMeasurementCrudRepo repo;
	
	@PersistenceContext
	EntityManager em;
	
	public void insertMeasurement(PmeteoMeasurements m, String stationName) {
		
		PmeteoMeasurementsModel model = new PmeteoMeasurementsModel();
		
		model.stationName = stationName;
		
		model.epoch = ZonedDateTime.now().toEpochSecond();
		
		model.apiMac = m.api_mac;
		model.humidity = m.humidity;
		model.masterTime = m.master_time;
		model.pm10 = m.pm10;
		model.pm2a5 = m.pm2_5;
		model.pressure = m.pressure;
		model.temperatureDallas = m.temperature_dallas;
		model.temperatureInternal = m.temperature_internal;
		model.temperatureModbus = m.temperature_modbus;
		model.temperaturePt = m.temperature_pt;
		model.windAverage = m.wind_average;
		model.windCurrent = m.wind_current;
		model.windDirection = m.wind_direction;
		model.windGust = m.wind_gust;
		model.windMinimal = m.wind_minimal;
		
		repo.save(model);
		
	}
}
