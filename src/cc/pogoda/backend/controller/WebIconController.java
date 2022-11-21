package cc.pogoda.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationsDefinitionDao;
import cc.pogoda.backend.dao.TelemetryDao;
import cc.pogoda.backend.types.model.StationDefinitionModel;
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

	@RequestMapping(value = "/station/{stationName}/icon", produces = "application/json;charset=UTF-8")
	public IconView getIconForStation(@PathVariable(required = true) String stationName)
	{
		IconView out = new IconView();
		
		StationDefinitionModel stationDefinition = stationsDefinitionDao.getStationByName(stationName);

		TelemetryModel t = telemetry.getTelemetryFromStationName(stationName);
		
		out.icon = "test";
		
		return out;
	}
}
