package cc.pogoda.backend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationDataDao;
import cc.pogoda.backend.types.TooLongException;
import cc.pogoda.backend.types.model.StationDataModel;
import cc.pogoda.backend.types.view.ListOfStationData;

@RestController
public class DataController{

	final static int maxLenght = 3600 * 24 * 9;		// 7 days
	
	@Autowired
	StationDataDao dataDao;
	
    private static Logger logger = LogManager.getLogger();
	
	@RequestMapping(value = "/station/{stationName}/stationData", produces = "application/json;charset=UTF-8")
	public ListOfStationData getStationDataPerName(
													@PathVariable(required = true)String stationName, 
													@RequestParam(value="from")long from,
													@RequestParam(value="to")long to) throws TooLongException 
	{
		
		if (to - from > maxLenght) {
			throw new TooLongException();
		}
	
		ListOfStationData out = new ListOfStationData();
		
		out.list_of_station_data = dataDao.getStationData(stationName, from, to).toArray(new StationDataModel[0]);
		
		return out;
		
	}
}
