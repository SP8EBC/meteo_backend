package cc.pogoda.backend.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationsDefinitionDao;
import cc.pogoda.backend.types.NotFoundException;
import cc.pogoda.backend.types.model.StationDefinitionModel;
import cc.pogoda.backend.types.view.StationCurrentLocalTime;

@RestController
public class StationLocalTime {

	@Autowired
	StationsDefinitionDao definitionDao;
	
    private static Logger logger = LogManager.getLogger();
	
	@RequestMapping(value = "/station/{stationName}/stationCurrentLocalTime", produces = "application/json;charset=UTF-8")
	public StationCurrentLocalTime stationLocalTimeController(@PathVariable(required = true) String stationName) throws NotFoundException {
		
		StationCurrentLocalTime out = new StationCurrentLocalTime();
		
		StationDefinitionModel station = null;
		
		//String stationName;
		
		//stationName = (String)allParams.get("station");
		
		if (stationName == null) {
			throw new NotFoundException();

		}
		else {
			station = definitionDao.getStationByName(stationName);
		}
		
		if (station == null) {
			throw new NotFoundException();
		}
		else {
			ZonedDateTime stationTime = ZonedDateTime.now(ZoneId.of(station.timezone.trim()));
			
			out.year = stationTime.getYear();
			out.month = stationTime.getMonthValue();
			out.day = stationTime.getDayOfMonth();
			out.hour = stationTime.getHour();
			out.minute = stationTime.getMinute();
			out.second = stationTime.getSecond();
			
			out.timezone = station.timezone.trim();
			out.timezone_offset = stationTime.getOffset().getTotalSeconds();
		}
		
		return out;
		
	}
}
