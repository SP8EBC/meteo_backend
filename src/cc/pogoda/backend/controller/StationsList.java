package cc.pogoda.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationsDefinitionDao;
import cc.pogoda.backend.types.model.StationDefinition;
import cc.pogoda.backend.types.view.ListOfAllStations;


@RestController
public class StationsList {

	@Autowired
	StationsDefinitionDao definitionDao;
	
	@RequestMapping(value = "/listOfAllStations", produces = "application/json;charset=UTF-8")
	public ListOfAllStations listOfAllStationsController() {
		
		int i = 0;
		
		ListOfAllStations out = new ListOfAllStations();
		
		List<StationDefinition> stations = definitionDao.getListOfAllStations();
		
		out.stations = new StationDefinition[stations.size()];
		
		for (StationDefinition def : stations) {
			out.stations[i] = def;
			
			i++;
		}
		
		return out;
		
	}
}
