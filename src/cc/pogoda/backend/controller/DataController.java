package cc.pogoda.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationDataDao;
import cc.pogoda.backend.types.TooLongException;
import cc.pogoda.backend.types.model.StationData;
import cc.pogoda.backend.types.view.ListOfStationData;

@RestController
public class DataController{

	final static int maxLenght = 3600 * 24 * 7;		// 7 days
	
	@Autowired
	StationDataDao dataDao;
	
	@RequestMapping(value = "/stationData", produces = "application/json;charset=UTF-8")
	public ListOfStationData getStationDataPerName(
													@RequestParam(value="station")String name, 
													@RequestParam(value="from")long from,
													@RequestParam(value="to")long to) throws TooLongException 
	{
		
		if (to - from > maxLenght) {
			throw new TooLongException();
		}
	
		ListOfStationData out = new ListOfStationData();
		
		out.list_of_station_data = dataDao.getStationData(name, from, to).toArray(new StationData[0]);
		
		return out;
		
	}
}
