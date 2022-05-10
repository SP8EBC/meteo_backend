package cc.pogoda.backend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.types.view.parameteo.PmeteoMeasurements;

@RestController
public class PMeteoMeasurementsController {

    private static Logger logger = LogManager.getLogger();

    
	@RequestMapping(value = "/parameteo/{stationName}/measurements/v1", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public void postMeteoStatusForStation(@RequestBody PmeteoMeasurements measurements, @PathVariable(required = true)String stationName) {
		
		logger.info("[PMeteoStatusController][postMeteoStatusForStation][stationName = " + stationName +"][measurements = " + measurements +"]");
		
		//dao.updateOrInsertStatus(status, stationName);
	}
}
