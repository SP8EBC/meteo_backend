package cc.pogoda.backend.controller;

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
	
		
		if (s != null) {
			
			StationDefinition stationDefinition = stationsDefinitionDao.getStationByName(station);
			
			Telemetry t = telemetry.getTelemetryFromStationName(station);
			
			WindQualityFactor wind_qf =  WindQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
			TemperatureQualityFactor temperature_qf = TemperatureQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
			PressureQualityFactor pressure_qf = PressureQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
			HumidityQualityFactor humidity_qf = HumidityQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
			
			if (!stationDefinition.hasWind) {
				wind_qf = WindQualityFactor.NOT_AVALIABLE;
			}
			
			if (!stationDefinition.hasQnh) {
				pressure_qf = PressureQualityFactor.NOT_AVALIABLE;
			}
			
			if (!stationDefinition.hasHumidity) {
				humidity_qf = HumidityQualityFactor.NOT_AVALIABLE;
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
