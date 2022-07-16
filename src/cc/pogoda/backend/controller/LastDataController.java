package cc.pogoda.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationDataDao;
import cc.pogoda.backend.types.NotFoundException;
import cc.pogoda.backend.types.model.StationDataModel;
import cc.pogoda.backend.types.view.ListOfStationData;

@RestController
public class LastDataController {

	@Autowired
	StationDataDao dataDao;
	
    private static Logger logger = LogManager.getLogger();
	
	private @Autowired HttpServletRequest request;
    
	@RequestMapping(value = "/station/{stationName}/lastStationData", produces = "application/json;charset=UTF-8")
	public ListOfStationData getLastStationDataPerName(	@PathVariable(required = true)String stationName, 
														@RequestParam(value="ascendingOrder", defaultValue = "false")boolean ascendingOrder, 
														@RequestParam(value="isLong", defaultValue = "false")boolean isLong) throws NotFoundException {
		int i = 0;
		
		ListOfStationData out = new ListOfStationData();
		
		List<StationDataModel> data;
		
		data = dataDao.getStationDataPerName(stationName, false, isLong);
		
		logger.info("[LastDataController][getLastStationDataPerName][request.getRemoteAddr() = " + request.getRemoteAddr() +"][stationName = " + stationName + "][ascendingOrder = " + ascendingOrder +"][isLong = " + isLong +"]");
		
		if (data != null) {
			
			out.list_of_station_data = new StationDataModel[data.size()];
			
			if (ascendingOrder) {
				i = data.size() - 1;
				
				for (StationDataModel d : data) {
					out.list_of_station_data[i] = d;
					
					i--;
					
					if (i < 0)
						break;
				}
			}
			else {
				for (StationDataModel d : data) {
					out.list_of_station_data[i] = d;
					
					i++;
				}
			}
		}
		else {
			throw new NotFoundException();
		}
		
		logger.info("[LastDataController][getLastStationDataPerName][request.getRemoteAddr() = " + request.getRemoteAddr() +"][stationName = " + stationName + "][out.list_of_station_data.length = " + out.list_of_station_data.length +"]");
		
		return out;
	}
}
