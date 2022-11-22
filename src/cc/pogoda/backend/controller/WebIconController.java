package cc.pogoda.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationsDefinitionDao;
import cc.pogoda.backend.dao.StationsGroupDao;
import cc.pogoda.backend.dao.TelemetryDao;
import cc.pogoda.backend.types.model.StationDefinitionModel;
import cc.pogoda.backend.types.model.StationGroupsBondingsModel;
import cc.pogoda.backend.types.model.TelemetryModel;
import cc.pogoda.backend.types.view.web.IconView;

/**
 * This endpoint is used only for 
 * @author mateusz
 *
 */
@RestController
public class WebIconController {
	
	@Autowired
	TelemetryDao telemetry;
	
	@Autowired
	StationsDefinitionDao stationsDefinitionDao;
	
	@Autowired
	StationsGroupDao stationsGroupDao;
	
	@RequestMapping(value = "/station/{stationName}/icon", produces = "application/json;charset=UTF-8")
	public IconView getIconForStation(@PathVariable(required = true) String stationName)
	{
		IconView out = new IconView();
		
		// get station definition for that name, what also get mappings to groups
		StationDefinitionModel stationDefinition = stationsDefinitionDao.getStationByName(stationName);
		
		// check if that station is connected to any stations group
		if (stationDefinition.groupsStationBelongsTo != null && stationDefinition.groupsStationBelongsTo.size() > 0) {
		// get an id of first group this station belongs to
		}
		
		
		
		//List<StationGroupsBondingsModel> stationBondings = stationsGroupDao.getAllBondingsForStation(stationDefinition.id);
		
		
		
		TelemetryModel t = telemetry.getTelemetryFromStationName(stationName);
		
		out.icon = "test";
		
		return out;
	}
}
