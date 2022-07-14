package cc.pogoda.backend.types.model.parameteo;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_parameteo_measurements")
public class PmeteoMeasurementsModel {
	
	@Id
	@Basic
	public int id;
	
	@Basic
	public long epoch;
	
	@Basic
	public String stationName;

	@Basic
	public short temperatureDallas;
	
	@Basic
	public short temperaturePt;
	
	@Basic
	public short temperatureInternal;
	
	@Basic
	public short temperatureModbus;
	
	@Basic
	public short pressure;
	
	@Basic
	public short humidity;
	
	@Basic
	public short windDirection;
	
	@Basic
	public short windAverage;
	
	@Basic
	public short windGust;
	
	@Basic
	public short windCurrent;
	
	@Basic
	public short windMinimal;
	
	@Basic
	public short pm2a5;
	
	@Basic
	public short pm10;
	
	@Basic
	public int masterTime;
	
	@Basic
	public String apiMac;
}
