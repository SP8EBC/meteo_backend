package cc.pogoda.backend.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import cc.pogoda.backend.dao.repository.SkrzyczneSummaryRepo;
import cc.pogoda.backend.dao.repository.TelemetryRepo;
import cc.pogoda.backend.types.GenericMeteoData;
import cc.pogoda.backend.types.model.SkrzyczneMeteoData;
import cc.pogoda.backend.types.model.Telemetry;
import cc.pogoda.backend.types.view.Summary;

@Repository
public class SummaryDao {


    @Autowired
    private WebApplicationContext context;
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	SkrzyczneSummaryRepo skrzyczneSummaryRepo;
	
	
	
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
			List<SkrzyczneMeteoData> l = skrzyczneSummaryRepo.findFirst50ByOrderByTimestampEpochDesc();
			for (SkrzyczneMeteoData m : l)
				genericData.add(m.convertToGeneric());
			break;
		}
		
		GenericMeteoData newest = genericData.get(0);
		LocalDateTime minush = newest.timestampEpoch.minusHours(1);
		
		out.last_timestamp = newest.timestampEpoch.toEpochSecond(ZoneOffset.ofTotalSeconds(0));
		
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
		
		//first.timestampEpoch.
		return out;
		
	}
}
