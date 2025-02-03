package cc.pogoda.backend.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationsDefinitionDao;
import cc.pogoda.backend.types.model.StationDefinitionModel;
import cc.pogoda.backend.types.view.ListOfAllStations;

@RestController
public class StationsList {

	@Autowired
	StationsDefinitionDao definitionDao;
	
	private @Autowired HttpServletRequest request;
	
    private static Logger logger = LogManager.getLogger();
	
	@RequestMapping(value = "/listOfAllStations", produces = "application/json;charset=UTF-8")
	public ListOfAllStations listOfAllStationsController() {
		
		int i = 0;
		
		ListOfAllStations out = new ListOfAllStations();
		
		List<StationDefinitionModel> stations = definitionDao.getListOfAllStations();
		
		out.stations = new StationDefinitionModel[stations.size()];
		
		logger.info("[StationsList][ListOfAllStations][request.getRemoteAddr() = " + request.getRemoteAddr() +"][out.stations.length = " + out.stations.length + "]");
		
		for (StationDefinitionModel def : stations) {
			out.stations[i] = def;
			
			i++;
		}
		
		return out;
		
	}
}
