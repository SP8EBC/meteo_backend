package cc.pogoda.backend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationsDefinitionDao;
import cc.pogoda.backend.types.model.AvailableParametersModel;
import cc.pogoda.backend.types.model.StationDefinitionModel;

@RestController
public class AvailableParametersController {

	@Autowired
	StationsDefinitionDao definitionDao;
	
    private static Logger logger = LogManager.getLogger();
	
	@RequestMapping(value = "/station/{stationName}/availableParameters", produces = "application/json;charset=UTF-8")
	public AvailableParametersModel getAvailableParametersForStation(@PathVariable(required = true)String stationName) {
		
		AvailableParametersModel out = new AvailableParametersModel();
		
		StationDefinitionModel station_def = definitionDao.getStationByName(stationName);
		
		out.hasHumidity = station_def.hasHumidity;
		out.hasQnh = station_def.hasQnh;
		out.hasRain = station_def.hasRain;
		out.hasWind = station_def.hasWind;
		out.telemetryVersion = station_def.telemetryVersion;
		
		return out;
	}
 
}
