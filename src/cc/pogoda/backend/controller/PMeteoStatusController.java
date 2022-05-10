package cc.pogoda.backend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.PMeteoStatusDao;
import cc.pogoda.backend.types.view.parameteo.PmeteoStatus;

@RestController
public class PMeteoStatusController {

    private static Logger logger = LogManager.getLogger();
	
    @Autowired
    PMeteoStatusDao dao;
    
	@RequestMapping(value = "/parameteo/{stationName}/status/v1", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public void putMeteoStatusForStation(@RequestBody PmeteoStatus status, @PathVariable(required = true)String stationName) {
		
		logger.info("[PMeteoStatusController][putMeteoStatusForStation][stationName = " + stationName +"][status = " + status +"]");
		
		dao.updateOrInsertStatus(status, stationName);
	}
}
