package cc.pogoda.backend.controller;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationsGroupDao;
import cc.pogoda.backend.types.model.StationGroupsBondingsModel;
import cc.pogoda.backend.types.model.StationsGroupModel;
import cc.pogoda.backend.types.view.LocaleEntry;
import cc.pogoda.backend.types.view.StationGroupStations;
import cc.pogoda.backend.types.view.StationsGroup;

@RestController
public class StationGroupsController {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    private StationsGroupDao dao;
    
	@RequestMapping(value = "/groups/all", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public List<StationsGroup> getAllGroups() {
		
		List<StationsGroup> output = new LinkedList<>();
		
		List<StationsGroupModel> all = dao.getAll();
		
		for (StationsGroupModel g : all) {
			StationsGroup view = new StationsGroup();
			view.isBuiltin = false;
			view.isEmpty = false;
			view.category = g.category;
			view.id = g.id;
			view.locale = LocaleEntry.fromModelList(g.localeList);
			view.isEnabled = g.enabled;
			
			output.add(view);
		}
		
    	return output;
    }
	
	@RequestMapping(value = "/groups/stations/{id}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
	public List<StationGroupStations> getAllStationsForGroup(@PathVariable(required = true) int id) {
		
		List<StationGroupStations> out = new LinkedList<StationGroupStations>();
		
		List<StationGroupsBondingsModel> r = dao.getAllBondingsForGroup(id);
		
		return out;
		
	}
}
