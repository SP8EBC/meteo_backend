package cc.pogoda.backend.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.config.Consts;
import cc.pogoda.backend.dao.StationsDefinitionDao;
import cc.pogoda.backend.dao.StationsGroupDao;
import cc.pogoda.backend.dao.TelemetryDao;
import cc.pogoda.backend.dao.repository.IconRepo;
import cc.pogoda.backend.types.HumidityQualityFactor;
import cc.pogoda.backend.types.PressureQualityFactor;
import cc.pogoda.backend.types.TemperatureQualityFactor;
import cc.pogoda.backend.types.WindQualityFactor;
import cc.pogoda.backend.types.model.StationDefinitionModel;
import cc.pogoda.backend.types.model.StationGroupsBondingsModel;
import cc.pogoda.backend.types.model.StationsGroupModel;
import cc.pogoda.backend.types.model.TelemetryModel;
import cc.pogoda.backend.types.model.web.IconModel;
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
	
	@Autowired
	IconRepo iconRepo;
	
	@RequestMapping(value = "/station/{stationName}/icon", produces = "application/json;charset=UTF-8")
	public IconView getIconForStation(@PathVariable(required = true) String stationName)
	{
		IconView out = new IconView();
		
		IconModel icons = null;
		
		// current time
		long currentUtcTimestamp = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).toEpochSecond();
		
		// get station definition for that name, what also get mappings to groups
		StationDefinitionModel stationDefinition = stationsDefinitionDao.getStationByName(stationName);
		
		// check if that station is connected to any stations group
		if (stationDefinition.groupsStationBelongsTo != null && stationDefinition.groupsStationBelongsTo.size() > 0) {
			// get an id of first group this station belongs to
			final int group = stationDefinition.groupsStationBelongsTo.get(0).groupId;
			
			// get station group definition for that id
			StationsGroupModel groupModel = stationsGroupDao.getGroupDefinition(group);
			
			// get icons for that groups
			icons = groupModel.icons;
		}
		else {
			// set default values
			icons = new IconModel();
			
			icons.full = Consts.DEFAULT_AVAILABLE_ICON;
			icons.degraded = Consts.DEFAULT_DEGR_ICON;
			icons.notavailable = Consts.DEFAULT_NAVBLE_ICON;
			
			//icons = iconRepo.findFirstByStationgroupid(IconRepo.DEFAULT_GROUP_ICON);
		}
		
		// get current telemetry 
		TelemetryModel t = telemetry.getTelemetryFromStationName(stationName);
		
		if (t != null) {
			// quality factors we need
			WindQualityFactor wind_qf;
			TemperatureQualityFactor temperature_qf;
			PressureQualityFactor pressure_qf;
			HumidityQualityFactor humidity_qf;
			
			boolean noData = false;
			boolean ok = false;
			
			if (t != null && stationDefinition.telemetryVersion != 0) {
				wind_qf =  WindQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
				temperature_qf = TemperatureQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
				pressure_qf = PressureQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
				humidity_qf = HumidityQualityFactor.fromBits((byte) t.digital, stationDefinition.telemetryVersion);
			}
			else {
				wind_qf = WindQualityFactor.FULL;
				temperature_qf = TemperatureQualityFactor.FULL;
				pressure_qf = PressureQualityFactor.FULL;
				humidity_qf = HumidityQualityFactor.FULL;
				
				ok = true;
			}
			
			if (!stationDefinition.hasWind) {
				wind_qf = WindQualityFactor.NOT_AVALIABLE;
			}
			
			if (!stationDefinition.hasQnh) {
				pressure_qf = PressureQualityFactor.NOT_AVALIABLE;
			}
			
			if (!stationDefinition.hasHumidity) {
				humidity_qf = HumidityQualityFactor.NOT_AVALIABLE;
			}
			
			if ((currentUtcTimestamp - t.epoch > 3600)) {
				wind_qf = WindQualityFactor.NO_DATA;
				pressure_qf = PressureQualityFactor.NO_DATA;
				humidity_qf = HumidityQualityFactor.NO_DATA;
				
				noData = true;
			}
			
			// now choose an icon
			if (noData) {
				// if there is no data
				out.icon = icons.notavailable;
			}
			else if (ok) {
				out.icon = icons.full;
			}
			
		}
				
		return out;
	}
}
