package cc.pogoda.backend.dao;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import cc.pogoda.backend.dao.repository.BezmiechSummaryRepo;
import cc.pogoda.backend.dao.repository.SkrzyczneSummaryRepo;
import cc.pogoda.backend.dao.repository.StationDataRepo;
import cc.pogoda.backend.dao.repository.SummaryRepo;
import cc.pogoda.backend.types.GenericMeteoData;
import cc.pogoda.backend.types.MeteoData;
import cc.pogoda.backend.types.model.BezmiechMeteoDataModel;
import cc.pogoda.backend.types.model.SkrzyczneMeteoDataModel;
import cc.pogoda.backend.types.model.StationDataModel;
import cc.pogoda.backend.types.model.SummaryModel;
import cc.pogoda.backend.types.view.Summary;

@Repository
public class SummaryDao {


    @Autowired
    private WebApplicationContext context;
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	SkrzyczneSummaryRepo skrzyczneSummaryRepo;

	@Autowired
	BezmiechSummaryRepo bezmiechSummaryRepo;
	
	@Autowired
	StationDataRepo stationDataRepo;
	
	@Autowired
	SummaryRepo summaryRepo;
	
    private static Logger logger = LogManager.getLogger();
	
    @Transactional
    public Summary getNewSummaryPerStationName(String call) {
    	Summary out = null;
    	
    	final SummaryModel summary = summaryRepo.findFirstByStation(call);
    	
    	if (summary != null) {
    		
    		out = new Summary();
    		
	    	out.average_speed = summary.average;
	    	out.avg_temperature = summary.temperature;
	    	out.direction = (short) summary.direction;
	    	out.gusts = summary.gusts;
	    	out.hour_gusts = summary.hourgusts;
	    	out.hour_max_average_speed = summary.hourmaxavg;
	    	out.hour_min_average_speed = summary.hourminavg;
	    	out.humidity = (byte) summary.humidity;
	    	out.last_timestamp = summary.lasttimestamp;
	    	out.number_of_measurements = summary.numberofmeasurements;
	    	out.qnh = (short) summary.qnh;
    	}
    	
    	return out;
    }
    
	@Transactional
	public Summary getSummaryPerStationName(String call) {
		
		int counter = 0;
		
		Summary out = new Summary();
		
		float temperature = (float) 0.0;
		float minimum_average = (float) 99.0;
		float maximum_average = (float) 0.0;
		float maximum_gusts = (float) 0.0;
		
		List<GenericMeteoData> genericData = new ArrayList<GenericMeteoData>();
			
		switch (call) {
		case "skrzyczne":
			List<SkrzyczneMeteoDataModel> l = skrzyczneSummaryRepo.findFirst50ByOrderByTimestampEpochDesc();
			for (SkrzyczneMeteoDataModel m : l)
				genericData.add(m.convertToGeneric());
			break;
		case "bezmiechowa":
		case "bezmiech":
			List<BezmiechMeteoDataModel> lb = bezmiechSummaryRepo.findFirst50ByOrderByTimestampEpochDesc();
			for (BezmiechMeteoDataModel m : lb)
				genericData.add(m.convertToGeneric());
			break;
		default:
			List<StationDataModel> ll = stationDataRepo.findFirst50ByStationOrderByEpochDesc(call);
			for (StationDataModel d : ll) {
				genericData.add(d.convertToGeneric());
			}
			break;
				
		}
		
		logger.info("[SummaryDao][getSummaryPerStationName][genericData.size() = " + genericData.size() +"]");
		
		if (genericData.size() > 2) {
		
			GenericMeteoData newest = genericData.get(0);
			LocalDateTime minush = newest.timestampEpoch.minusHours(1);
			
			logger.info("[SummaryDao][getSummaryPerStationName][minush = " + minush.format(DateTimeFormatter.ISO_DATE) +"][out.last_timestamp = " + out.last_timestamp +"][newest = " + newest.toString() +"]");
			
			out.last_timestamp = newest.timestampEpoch.toEpochSecond(OffsetDateTime.now().getOffset());
			
			for (GenericMeteoData data : genericData) {
				
				if (data.timestampEpoch.isBefore(minush))
					break;
	
				counter++;
				
				temperature += data.Temp;
				
				if (minimum_average > data.WindSpeed)
					minimum_average = data.WindSpeed;
				
				if (maximum_average < data.WindSpeed)
					maximum_average = data.WindSpeed;
				
				if (maximum_gusts < data.WindGusts)
					maximum_gusts = data.WindGusts;
				
				out.qnh = (short) data.QNH;
				out.humidity = (byte) data.humidity;
				out.direction = data.WindDir;
				out.average_speed = data.WindSpeed;
				out.gusts = data.WindGusts;
				
				
			}
			
			out.hour_gusts = maximum_gusts;
			out.hour_max_average_speed = maximum_average;
			out.hour_min_average_speed = minimum_average;
			out.avg_temperature = temperature / counter;
			out.number_of_measurements = counter;
		}
		else {
			out = null;
		}

		return out;
		
	}
}
