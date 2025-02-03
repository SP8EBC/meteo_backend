package cc.pogoda.backend.types.model.parameteo;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "data_parameteo_status")
public class PmeteoStatusModel {
	
	@Id
	@Basic
    //@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	
	@Basic
	public String station;
	
	@Basic
	public long timestamp;
	
	@Basic
	public String mainConfigDataBasicCallsign;
	
	@Basic
	public int mainConfigDataBasicSsid;
	
	@Basic
	public long masterTime;
	
	@Basic
	public int mainCpuLoad;
	
	@Basic
	public int rx10m;
	
	@Basic
	public int tx10m;
	
	@Basic
	public int digi10m;
	
	@Basic
	public int digidrop10m;
	
	@Basic
	public int kiss10m;
	
	@Basic
	public int rxtotal;
	
	@Basic
	public int txtotal;
	
	@Basic
	public int rteMainAverageBatteryVoltage;
	
	@Basic
	public int rteMainWakeupCount;
	
	@Basic
	public int rteMainGoingSleepCount;
	
	@Basic
	public int rteMainLastSleepMasterTime;
	
}
