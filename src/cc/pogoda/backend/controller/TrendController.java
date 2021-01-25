package cc.pogoda.backend.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationDataDao;
import cc.pogoda.backend.dao.StationsDefinitionDao;
import cc.pogoda.backend.dao.TelemetryDao;
import cc.pogoda.backend.types.HumidityQualityFactor;
import cc.pogoda.backend.types.NotFoundException;
import cc.pogoda.backend.types.PressureQualityFactor;
import cc.pogoda.backend.types.TemperatureQualityFactor;
import cc.pogoda.backend.types.WindQualityFactor;
import cc.pogoda.backend.types.model.StationData;
import cc.pogoda.backend.types.model.StationDefinition;
import cc.pogoda.backend.types.model.Telemetry;
import cc.pogoda.backend.types.view.Trend;

@RestController
public class TrendController {

	@Autowired
	StationDataDao dataDao;
	
	@Autowired
	TelemetryDao telemetry;
	
	@Autowired
	StationsDefinitionDao stationsDefinitionDao;
	
	private final static int twoHours = 7200;
	
	private final static int fourHours = 3600 * 4;
	
	private final static int sixHours = 3600 * 6;
	
	private final static int eightHours = 3600 * 8;
	
	private final static int averageWindowLn = 900;
	
	@RequestMapping(value = "/trend", produces = "application/json;charset=UTF-8")
	public Trend getTrendPerStationName(@RequestParam(value="station")String station) throws NotFoundException {
		
		Trend out = new Trend();
		
		StationData current = dataDao.getCurrentStationData(station);
		
		long currentUtcTimestamp = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).toEpochSecond();
		
		if (current != null) {
			
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
			
			out.currentTimestampUtc = currentUtcTimestamp;
			out.currentQnhQf = pressure_qf.toString();
			out.currentWindQf = wind_qf.toString();
			out.currentTemperatureQf = temperature_qf.toString();
			out.currentHumidityQf = humidity_qf.toString();
			
			List<StationData> dataTwo = dataDao.getStationData(station, currentUtcTimestamp - twoHours - averageWindowLn, currentUtcTimestamp - twoHours + averageWindowLn);
			List<StationData> dataFour = dataDao.getStationData(station, currentUtcTimestamp - fourHours - averageWindowLn, currentUtcTimestamp - fourHours + averageWindowLn);
			List<StationData> dataSix = dataDao.getStationData(station, currentUtcTimestamp - sixHours - averageWindowLn, currentUtcTimestamp - sixHours + averageWindowLn);
			List<StationData> dataEight = dataDao.getStationData(station, currentUtcTimestamp - eightHours - averageWindowLn, currentUtcTimestamp - eightHours + averageWindowLn);
			
			StationData twoAverage = StationData.averageFromList(dataTwo);
			StationData fourAverage = StationData.averageFromList(dataFour);
			StationData sixAverage = StationData.averageFromList(dataSix);
			StationData eightAverage = StationData.averageFromList(dataEight);
			
			out.temperatureTrend.currentValue = current.temperature;
			out.temperatureTrend.twoHoursValue = twoAverage.temperature;
			out.temperatureTrend.fourHoursValue = fourAverage.temperature;
			out.temperatureTrend.sixHoursValue = sixAverage.temperature;
			out.temperatureTrend.eightHoursValue = eightAverage.temperature;
			
			out.pressureTrend.currentValue = current.pressure;
			out.pressureTrend.twoHoursValue = twoAverage.pressure;
			out.pressureTrend.fourHoursValue = fourAverage.pressure;
			out.pressureTrend.sixHoursValue = sixAverage.pressure;
			out.pressureTrend.eightHoursValue = eightAverage.pressure;
			
			out.humidityTrend.currentValue = current.humidity;
			out.humidityTrend.twoHoursValue = twoAverage.humidity;
			out.humidityTrend.fourHoursValue = fourAverage.humidity;
			out.humidityTrend.sixHoursValue = sixAverage.humidity;
			out.humidityTrend.eightHoursValue = eightAverage.humidity;
			
			out.averageWindspeedTrend.currentValue = current.windspeed;
			out.averageWindspeedTrend.twoHoursValue = twoAverage.windspeed;
			out.averageWindspeedTrend.fourHoursValue = fourAverage.windspeed;
			out.averageWindspeedTrend.sixHoursValue = sixAverage.windspeed;
			out.averageWindspeedTrend.eightHoursValue = eightAverage.windspeed;
		
			out.maximumWindpseedTrend.currentValue = current.windgusts;
			out.maximumWindpseedTrend.twoHoursValue = twoAverage.windgusts;
			out.maximumWindpseedTrend.fourHoursValue = fourAverage.windgusts;
			out.maximumWindpseedTrend.sixHoursValue = sixAverage.windgusts;
			out.maximumWindpseedTrend.eightHoursValue = eightAverage.windgusts;
		}
		else {
			throw new NotFoundException();
		}
		
		
		return out;
	}
	
}
