package cc.pogoda.backend.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
	public Trend getTrendPerStationName(@RequestParam(name="station")String station, @RequestParam(defaultValue =  "false", name="notRounded", required = false)boolean notRounded) throws NotFoundException {
		
		Trend out = new Trend();
		
		// used to round double to certain significant figures
		MathContext mathContextTwo = new MathContext(2, RoundingMode.HALF_EVEN);
		
		// used to round double to certain significant figures
		BigDecimal rounded;
		
		StationData current = dataDao.getCurrentStationData(station);

		long currentUtcTimestamp = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).toEpochSecond();
		
		StationDefinition stationDefinition = stationsDefinitionDao.getStationByName(station);

		WindQualityFactor wind_qf = WindQualityFactor.NO_DATA;
		TemperatureQualityFactor temperature_qf = TemperatureQualityFactor.NO_DATA;
		PressureQualityFactor pressure_qf = PressureQualityFactor.NO_DATA;
		HumidityQualityFactor humidity_qf = HumidityQualityFactor.NO_DATA;
		
		if (current != null && stationDefinition != null) {
			
			Telemetry t = telemetry.getTelemetryFromStationName(station);
			if (t != null) {
				wind_qf =  WindQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
				temperature_qf = TemperatureQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
				pressure_qf = PressureQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
				humidity_qf = HumidityQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
			}
			
			long lastDataTimestamp = current.epoch;
			
			if (!stationDefinition.hasWind) {
				wind_qf = WindQualityFactor.NOT_AVALIABLE;
			}
			
			if (!stationDefinition.hasQnh) {
				pressure_qf = PressureQualityFactor.NOT_AVALIABLE;
			}
			
			if (!stationDefinition.hasHumidity) {
				humidity_qf = HumidityQualityFactor.NOT_AVALIABLE;
			}
			
			// check if station transmit data
			if (t == null || currentUtcTimestamp - lastDataTimestamp > 3600) {
				// if last data is older than one hour set all Quality factor to non avaliable
				wind_qf = WindQualityFactor.NO_DATA;
				pressure_qf = PressureQualityFactor.NO_DATA;
				humidity_qf = HumidityQualityFactor.NO_DATA;
				temperature_qf = TemperatureQualityFactor.NO_DATA;
			}
			
			if (!notRounded) {
				rounded = new BigDecimal(current.temperature, mathContextTwo);
				current.temperature = rounded.floatValue();
			}
			
			out.last_timestamp = current.epoch;		// a timestamp of current data in UTC timezone
			out.displayed_name = stationDefinition.displayedName;
			out.current_qnh_qf = pressure_qf.toString();
			out.current_wind_qf = wind_qf.toString();
			out.current_temperature_qf = temperature_qf.toString();
			out.current_humidity_qf = humidity_qf.toString();
			
			List<StationData> dataTwo = dataDao.getStationData(station, lastDataTimestamp - twoHours - averageWindowLn, lastDataTimestamp - twoHours + averageWindowLn);
			List<StationData> dataFour = dataDao.getStationData(station, lastDataTimestamp - fourHours - averageWindowLn, lastDataTimestamp - fourHours + averageWindowLn);
			List<StationData> dataSix = dataDao.getStationData(station, lastDataTimestamp - sixHours - averageWindowLn, lastDataTimestamp - sixHours + averageWindowLn);
			List<StationData> dataEight = dataDao.getStationData(station, lastDataTimestamp - eightHours - averageWindowLn, lastDataTimestamp - eightHours + averageWindowLn);
			
			StationData twoAverage = StationData.averageFromList(dataTwo, notRounded);
			StationData fourAverage = StationData.averageFromList(dataFour, notRounded);
			StationData sixAverage = StationData.averageFromList(dataSix, notRounded);
			StationData eightAverage = StationData.averageFromList(dataEight, notRounded);
			
			out.temperature_trend.current_value = current.temperature;
			out.temperature_trend.two_hours_value = twoAverage.temperature;
			out.temperature_trend.four_hours_value = fourAverage.temperature;
			out.temperature_trend.six_hours_value = sixAverage.temperature;
			out.temperature_trend.eight_hours_value = eightAverage.temperature;
			
			out.pressure_trend.current_value = current.pressure;
			out.pressure_trend.two_hours_value = twoAverage.pressure;
			out.pressure_trend.four_hours_value = fourAverage.pressure;
			out.pressure_trend.six_hours_value = sixAverage.pressure;
			out.pressure_trend.eight_hours_value = eightAverage.pressure;
			
			out.humidity_trend.current_value = current.humidity;
			out.humidity_trend.two_hours_value = twoAverage.humidity;
			out.humidity_trend.four_hours_value = fourAverage.humidity;
			out.humidity_trend.six_hours_value = sixAverage.humidity;
			out.humidity_trend.eight_hours_value = eightAverage.humidity;
			
			out.average_wind_speed_trend.current_value = current.windspeed;
			out.average_wind_speed_trend.two_hours_value = twoAverage.windspeed;
			out.average_wind_speed_trend.four_hours_value = fourAverage.windspeed;
			out.average_wind_speed_trend.six_hours_value = sixAverage.windspeed;
			out.average_wind_speed_trend.eight_hours_value = eightAverage.windspeed;
		
			out.maximum_wind_speed_trend.current_value = current.windgusts;
			out.maximum_wind_speed_trend.two_hours_value = twoAverage.windgusts;
			out.maximum_wind_speed_trend.four_hours_value = fourAverage.windgusts;
			out.maximum_wind_speed_trend.six_hours_value = sixAverage.windgusts;
			out.maximum_wind_speed_trend.eight_hours_value = eightAverage.windgusts;
			
			out.wind_direction_trend.current_value = current.winddir;
			out.wind_direction_trend.two_hours_value = twoAverage.winddir;
			out.wind_direction_trend.four_hours_value = fourAverage.winddir;
			out.wind_direction_trend.six_hours_value = sixAverage.winddir;
			out.wind_direction_trend.eight_hours_value = eightAverage.winddir;
		}
		else {
			throw new NotFoundException();
		}
		
		
		return out;
	}
	
}
