package cc.pogoda.backend.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationsDefinitionDao;
import cc.pogoda.backend.dao.SummaryDao;
import cc.pogoda.backend.dao.TelemetryDao;
import cc.pogoda.backend.types.HumidityQualityFactor;
import cc.pogoda.backend.types.NotFoundException;
import cc.pogoda.backend.types.PressureQualityFactor;
import cc.pogoda.backend.types.TemperatureQualityFactor;
import cc.pogoda.backend.types.WindQualityFactor;
import cc.pogoda.backend.types.model.StationDefinition;
import cc.pogoda.backend.types.model.Telemetry;
import cc.pogoda.backend.types.view.Summary;

@RestController
public class SummaryController {

	@Autowired
	SummaryDao dao;
	
	@Autowired
	TelemetryDao telemetry;
	
	@Autowired
	StationsDefinitionDao stationsDefinitionDao;
	
	@RequestMapping(value = "/summary", produces = "application/json;charset=UTF-8")
	public Summary summaryControler(@RequestParam(value="station")String station) throws NotFoundException {
		Summary s = dao.getSummaryPerStationName(station);
	
		long currentUtcTimestamp = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).toEpochSecond();
		
		WindQualityFactor wind_qf;
		TemperatureQualityFactor temperature_qf;
		PressureQualityFactor pressure_qf;
		HumidityQualityFactor humidity_qf;
		
		if (s != null) {
			
			StationDefinition stationDefinition = stationsDefinitionDao.getStationByName(station);
			
			Telemetry t = telemetry.getTelemetryFromStationName(station);
			
			if (t != null && stationDefinition.telemetryVersion != 0) {
				wind_qf =  WindQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
				temperature_qf = TemperatureQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
				pressure_qf = PressureQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
				humidity_qf = HumidityQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
			}
			else {
				wind_qf = WindQualityFactor.FULL;
				temperature_qf = TemperatureQualityFactor.FULL;
				pressure_qf = PressureQualityFactor.FULL;
				humidity_qf = HumidityQualityFactor.FULL;
			}
			
			if (!stationDefinition.hasWind) {
				wind_qf = WindQualityFactor.NOT_AVALIABLE;
			}
			
			if (!stationDefinition.hasQnh) {
				pressure_qf = PressureQualityFactor.NOT_AVALIABLE;
			}
			
			if (!stationDefinition.hasHumidity) {
				humidity_qf = HumidityQualityFactor.NOT_AVALIABLE;
			}
			
			if ((currentUtcTimestamp - s.last_timestamp > 3600)) {
				wind_qf = WindQualityFactor.NO_DATA;
				pressure_qf = PressureQualityFactor.NO_DATA;
				humidity_qf = HumidityQualityFactor.NO_DATA;
			}
			
			s.qnh_qf = pressure_qf.toString();
			s.wind_qf = wind_qf.toString();
			s.temperature_qf = temperature_qf.toString();
			s.humidity_qf = humidity_qf.toString();
			
			return s;
		}
		else {
			throw new NotFoundException();
		}
		
	}
	
	
}
