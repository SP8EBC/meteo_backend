package cc.pogoda.backend.types.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cc.pogoda.backend.types.GenericMeteoData;
import cc.pogoda.backend.types.MeteoData;

@Entity
@Table(name = "data_station")
public class StationData extends MeteoData {

	@Id
	@Basic
	public int id;
	
	@Basic
	public long epoch;
	
	@Basic
	public LocalDateTime datetime;
	
	@Basic
	public String station;
	
	@Basic
	public float temperature;
	
	@Basic
	public short humidity;
	
	@Basic
	public float pressure;
	
	@Basic
	public short winddir;
	
	@Basic
	public float windspeed;
	
	@Basic
	public float windgusts;
	
	@Basic
	public String tsource;
	
	@Basic
	public String wsource;
	
	@Basic
	public String psource;
	
	@Basic
	public String hsource;
	
	@Basic
	public String rsource;

	@Override
	public GenericMeteoData convertToGeneric() {
		GenericMeteoData out = new GenericMeteoData();
		
		out.id = this.id;
		out.timestampEpoch = this.datetime;
		out.Temp = this.temperature;
		out.WindSpeed = this.windspeed;
		out.WindGusts = this.windgusts;
		out.WindDir = this.winddir;
		out.QNH = (int)this.pressure;
		out.humidity = this.humidity;
		
		return out;
	}
	
	public static StationData averageFromList(List<StationData> data) {
		StationData out = new StationData();
		
		if (data != null && data.size() > 1) {
			
			int dataPoints = 0;
			
			// the returned value of wind gusts is the maximum found in the input set
			float gusts = 0.0f;
			
			for (StationData d : data) {
				out.temperature += d.temperature;
				out.pressure += d.pressure;
				out.humidity += d.humidity;
				out.windspeed += d.windspeed;
				
				dataPoints++;
				
				// look fo the maximum value of wind gusts 
				if (d.windgusts > gusts) 
					gusts = d.windgusts;
			}
			
			out.temperature /= dataPoints;
			out.pressure /= dataPoints;
			out.humidity /= dataPoints;
			out.windspeed /= dataPoints;
			
			out.windgusts = gusts;
		}
		
		return out;
	}
}
