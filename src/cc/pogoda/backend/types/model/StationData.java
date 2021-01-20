package cc.pogoda.backend.types.model;

import java.time.LocalDateTime;

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
}
