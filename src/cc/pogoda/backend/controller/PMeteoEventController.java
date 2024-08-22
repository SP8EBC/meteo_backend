package cc.pogoda.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.PMeteoEventDao;
import cc.pogoda.backend.types.view.parameteo.PmeteoEvent;

@RestController
public class PMeteoEventController {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    PMeteoEventDao eventDao;
	
	private @Autowired HttpServletRequest request;
	
	@RequestMapping(value = "/parameteo/{stationName}/event/v1", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public void postMeteoEventForStation(@RequestBody PmeteoEvent event, @PathVariable(required = true)String stationName) {
		
		logger.info("[PMeteoEventController][postMeteoEventForStation][request.getRemoteAddr() = " + request.getRemoteAddr() + "][stationName = " + stationName +"][event = " + event.event +"]");

		eventDao.insertEvent(event, stationName);
		
	}
}
