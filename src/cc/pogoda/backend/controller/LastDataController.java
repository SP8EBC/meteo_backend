package cc.pogoda.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.pogoda.backend.dao.StationDataDao;
import cc.pogoda.backend.types.model.StationData;
import cc.pogoda.backend.types.view.ListOfStationData;

@RestController
public class LastDataController {

	@Autowired
	StationDataDao dataDao;
	
	@RequestMapping(value = "/lastStationData", produces = "application/json;charset=UTF-8")
	public ListOfStationData getLastStationDataPerName(@RequestParam(value="station")String name) {
		int i = 0;
		
		ListOfStationData out = new ListOfStationData();
		
		List<StationData> data;
		
		data = dataDao.getStationDataPerName(name);
		
		if (data != null) {
			
			out.listOfStationData = new StationData[data.size()];
			
			for (StationData d : data) {
				out.listOfStationData[i] = d;
				
				i++;
			}
		}
		
		return out;
	}
}
